package org.wusay.software.owlengine.core.worker;


import org.wusay.software.owlengine.core.datasource.OutputDataSource;

/**
 * 命中接口
 */
public interface Matcher {



    /**
     * 是否命中
     * @param outputDataSource
     * @return
     */
    boolean match(OutputDataSource outputDataSource);
}
