package org.wusay.software.owlengine.core;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.wusay.software.owlengine.core.datasource.InputDataSource;
import org.wusay.software.owlengine.core.datasource.OutputDataSource;
import org.wusay.software.owlengine.core.worker.ContentProcessor;
import org.wusay.software.owlengine.core.worker.NextProcessor;
import org.wusay.software.owlengine.core.worker.Spider;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择器,负责选择各类组件
 */
public class Selector {


    private static final Logger logger = LogManager.getLogger(Selector.class);

    private List<Spider> spiders;

    private List<ContentProcessor> contentProcessors;

    private List<NextProcessor> nextProcessors;


    public Selector() {
        spiders = new ArrayList<>();
        contentProcessors = new ArrayList<>();
        nextProcessors = new ArrayList<>();

    }

    public void addContentProcessor(ContentProcessor processor) {
        contentProcessors.add(processor);
    }


    public void addNextProcessor(NextProcessor processor) {
        nextProcessors.add(processor);
    }


    public void addSpider(Spider spider) {
        spiders.add(spider);
    }


    public NextProcessor selectNextProcessor(OutputDataSource outputDataSource) {

        for (NextProcessor processor : nextProcessors) {
            if (processor.match(outputDataSource)){
                return processor;
            }
        }
        return null;
    }


    public ContentProcessor selectContentProcessor(OutputDataSource outputDataSource) {

        for (ContentProcessor processor : contentProcessors) {
            if (processor.match(outputDataSource)) {
                return processor;
            }
        }
        return null;
    }




    public Spider selectSpider(InputDataSource inputDataSource) {

        for (Spider spider : spiders) {
            if (spider.match(inputDataSource)){
                return spider;
            }
        }

        return null;

    }




}
