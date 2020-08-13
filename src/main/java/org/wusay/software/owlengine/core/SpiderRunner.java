package org.wusay.software.owlengine.core;

import org.wusay.software.owlengine.core.datasource.BaseStoreDataSource;
import org.wusay.software.owlengine.core.datasource.InputDataSource;
import org.wusay.software.owlengine.core.datasource.OutputDataSource;
import org.wusay.software.owlengine.core.plugins.LinkHomeSpider;
import org.wusay.software.owlengine.core.worker.*;

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

    private BaseSpider spider;

    private BaseContentProcessor contentProcessor;


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
    public boolean crawling(InputDataSource inputDataSource) {

//        LinkHomeSpider likeHomeSpider = new LinkHomeSpider("linkHome");
//        likeHomeSpider.setNextSpider(null);

        OutputDataSource outputDataSource = spider.getContent(inputDataSource);

        List<BaseStoreDataSource> content =  contentProcessor.process(outputDataSource);
        contents.addAll(content);

        List<InputDataSource> nexts = contentProcessor.processNextInput(outputDataSource);
        resultInputs.addAll(nexts);

        return true;

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
