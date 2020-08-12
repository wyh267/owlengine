package org.wusay.software.owlengine.core.worker;

import org.wusay.software.owlengine.core.datasource.InputDataSource;
import org.wusay.software.owlengine.core.datasource.OutputDataSource;

import java.util.List;


/**
 * 爬虫处理接口，负责进行爬取之后数据的处理，处理分为两部分：
 * 1.处理完成后生成新的需要爬取的网页链接
 * 2.处理本页面，生成结构化的数据存储到ES中
 */
public interface NextProcessor extends Matcher {


    /**
     * 处理数据
     * @param outputDataSource
     * @return
     */
    List<InputDataSource> processNextInput(OutputDataSource outputDataSource);






}
