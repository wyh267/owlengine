package org.wusay.software.owlengine.core;

import org.wusay.software.owlengine.core.datasource.BaseStoreDataSource;
import org.wusay.software.owlengine.core.datasource.InputDataSource;
import org.wusay.software.owlengine.core.datasource.OutputDataSource;
import org.wusay.software.owlengine.core.worker.ContentProcessor;
import org.wusay.software.owlengine.core.worker.NextProcessor;
import org.wusay.software.owlengine.core.worker.Spider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 爬虫线程，启动一个线程以后进行一次爬取工作
 */
public class SpiderRunner implements Runnable {


    private Selector selector;

    private Collection<InputDataSource> inputDataSources;

    private Collection<InputDataSource> resultInputs;

    private Collection<BaseStoreDataSource> contents;

    private StoreService storeService;


    public SpiderRunner(Selector selector,
                        Collection<InputDataSource> inputDataSources,
                        StoreService storeService) {
        this.selector = selector;
        this.inputDataSources = inputDataSources;
        this.storeService = storeService;

        this.resultInputs = new ArrayList<>();
        this.contents = new ArrayList<>();
    }


    /**
     * 爬取一次
     * @param inputDataSource
     */
    public List<InputDataSource> crawling(InputDataSource inputDataSource) {


        // 爬取
        Spider spider = selector.selectSpider(inputDataSource);
        OutputDataSource outputDataSource = spider.getContent(inputDataSource);


        // 分析
        ContentProcessor contentProcessor = selector.selectContentProcessor(outputDataSource);
        List<BaseStoreDataSource> content = contentProcessor.processContent(outputDataSource);
        contents.addAll(content);

        // 下一阶段爬取
        NextProcessor nextProcessor = selector.selectNextProcessor(outputDataSource);
        return nextProcessor.processNextInput(outputDataSource);

    }


    public void run() {


        for (InputDataSource input : inputDataSources) {
            List<InputDataSource> subResult = crawling(input);
            resultInputs.addAll(subResult);
        }

        // 存储
        storeService.storeContent(contents);


    }
}
