package org.wusay.software.owlengine.core;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 爬虫选择器，通过url选择出对应的自定义实现的爬虫
 */
public class SpiderSelcetor {

    private static final Logger logger = LogManager.getLogger(SpiderSelcetor.class);


    Collection<Spider> spiders;

    public SpiderSelcetor() {

        spiders = new ArrayList<Spider>();
    }


    /**
     * 新增一个爬虫类型
     * @param spider
     */
    public void addSpider(Spider spider) {
        spiders.add(spider);
    }


    /**
     * 爬虫选择器，选择对应的爬虫
     * @param input
     * @return
     */
    public Spider selectSpider(InputDataSource input) {


        for (Spider spider : spiders) {
            if (spider.match(input)) {
                return spider;
            }
        }
        return null;

    }



}
