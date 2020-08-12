package org.wusay.software.owlengine.core.worker;


import org.wusay.software.owlengine.core.datasource.InputDataSource;
import org.wusay.software.owlengine.core.datasource.OutputDataSource;

/**
 * 爬虫接口,负责进行数据爬取
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
    OutputDataSource getContent(InputDataSource inputDataSource);


    /**
     * 是否match本爬虫
     * @param input
     * @return
     */
    boolean match(InputDataSource input);





}
