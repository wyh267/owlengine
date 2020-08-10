package org.wusay.software.owlengine.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 爬虫线程，启动一个线程以后进行一次爬取工作
 */
public class SpiderRunner implements Runnable {


    private SpiderSelcetor spiderSelcetor;

    private ProcessorSelector processorSelector;

    private Collection<InputDataSource> inputDataSources;


    private Collection<InputDataSource> resultInputs;

    public SpiderRunner(SpiderSelcetor spiderSelcetor,
                        ProcessorSelector processorSelector,
                        Collection<InputDataSource> inputDataSources) {
        this.spiderSelcetor = spiderSelcetor;
        this.processorSelector = processorSelector;
        this.inputDataSources = inputDataSources;

        this.resultInputs = new ArrayList<InputDataSource>();
    }


    /**
     * 调度爬虫
     * @param inputDataSource
     */
    public List<InputDataSource> scheduler(InputDataSource inputDataSource) {


        Spider spider = spiderSelcetor.selectSpider(inputDataSource);

        BaseDataSource dataSource = spider.getContent(inputDataSource);

        Processor processor = processorSelector.selectProcessor(dataSource);

        return processor.processContent(dataSource);

    }


    public void run() {

        for (InputDataSource input : inputDataSources) {
            List<InputDataSource> subResult = scheduler(input);
            resultInputs.addAll(subResult);
        }


    }
}
