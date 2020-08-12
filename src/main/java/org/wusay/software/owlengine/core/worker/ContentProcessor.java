package org.wusay.software.owlengine.core.worker;


import org.wusay.software.owlengine.core.BaseStoreDataSource;
import org.wusay.software.owlengine.core.OutputDataSource;

import java.util.List;

/**
 * 内容解析器，负责解析网页内容
 */
public interface ContentProcessor extends Matcher{


    /**
     * 解析内容
     * @param outputDataSource
     * @return
     */
    List<BaseStoreDataSource> processContent(OutputDataSource outputDataSource);

}
