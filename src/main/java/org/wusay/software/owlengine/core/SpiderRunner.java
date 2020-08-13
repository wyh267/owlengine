package org.wusay.software.owlengine.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.wusay.software.owlengine.core.datasource.BaseStoreDataSource;
import org.wusay.software.owlengine.core.datasource.InputDataSource;
import org.wusay.software.owlengine.core.datasource.OutputDataSource;
import org.wusay.software.owlengine.core.worker.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 爬虫线程，启动一个线程以后进行一次爬取工作
 */
public class SpiderRunner implements Runnable {

    private static final Logger logger = LogManager.getLogger(SpiderRunner.class);


    private Collection<InputDataSource> inputDataSources;

    private Collection<InputDataSource> nextInputs;

    private Collection<BaseStoreDataSource> contents;

    private StoreService storeService;

    private BaseSpider spider;

    private BaseContentProcessor contentProcessor;


    public SpiderRunner(BaseSpider spider,
                        BaseContentProcessor processor,
                        StoreService storeService,
                        Collection<InputDataSource> inputDataSources) {
        this.spider = spider;
        this.contentProcessor = processor;
        this.storeService = storeService;
        this.inputDataSources = inputDataSources;

        this.nextInputs = new ArrayList<>();
        this.contents = new ArrayList<>();
    }


    /**
     * 爬取一次
     * @param inputDataSource
     */
    public boolean crawling(InputDataSource inputDataSource) {

//        LinkHomeSpider likeHomeSpider = new LinkHomeSpider("linkHome");
//        likeHomeSpider.setNextSpider(null);

        try {
            OutputDataSource outputDataSource = spider.getContent(inputDataSource);

            List<BaseStoreDataSource> content = contentProcessor.process(outputDataSource);
            contents.addAll(content);

            List<InputDataSource> subNextInputs = contentProcessor.processNextInput(outputDataSource);
            nextInputs.addAll(subNextInputs);

        }catch (IOException ex) {
            logger.error("crawling error {}",ex.getMessage());
            return false;
        }

        return true;

    }


    public void run() {


        for (InputDataSource input : inputDataSources) {
            crawling(input);
        }

        // 存储
        storeService.storeContent(contents);

        // 存储后续输入
        storeService.storeContent(nextInputs);


    }
}
