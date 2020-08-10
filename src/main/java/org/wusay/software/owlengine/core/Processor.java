package org.wusay.software.owlengine.core;

import java.util.List;

public interface Processor {


    /**
     * 处理数据
     * @param baseDataSource
     * @return
     */
    List<InputDataSource> processContent(BaseDataSource baseDataSource);


    /**
     * 命中
     * @param baseDataSource
     * @return
     */
    boolean match(BaseDataSource baseDataSource);

}
