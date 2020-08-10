package org.wusay.software.owlengine.core;


import java.util.List;

/**
 * 爬虫接口
 */
public interface Spider {


    /**
     * 初始化爬虫
     * @return
     */
    boolean initSpider();

    /**
     * 爬取数据
     * @param inputDataSource
     * @return
     */
    BaseDataSource getContent(InputDataSource inputDataSource);


    /**
     * 是否match本爬虫
     * @param input
     * @return
     */
    boolean match(InputDataSource input);





}
