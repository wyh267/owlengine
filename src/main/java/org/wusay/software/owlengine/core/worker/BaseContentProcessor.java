package org.wusay.software.owlengine.core.worker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.wusay.software.owlengine.core.StoreService;
import org.wusay.software.owlengine.core.datasource.BaseStoreDataSource;
import org.wusay.software.owlengine.core.datasource.InputDataSource;
import org.wusay.software.owlengine.core.datasource.OutputDataSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 分析基础类
 */
public abstract class BaseContentProcessor {

    protected final Logger logger;


    private BaseContentProcessor nextContentProcessor;

    protected String name;

    public BaseContentProcessor(String name) {
        logger = LogManager.getLogger(getClass());
        this.name = name;
    }


    public void setNextContentProcessor(BaseContentProcessor nextContentProcessor) {
        this.nextContentProcessor = nextContentProcessor;
    }


    /**
     * 链式处理
     *
     * @param outputDataSource
     * @return
     */
    public List<BaseStoreDataSource> process(OutputDataSource outputDataSource) {

        List<BaseStoreDataSource> results = new ArrayList<>();

        List<BaseStoreDataSource> rs = doProcess(outputDataSource);
        if (rs != null) {
            logger.info("this processor [ {} ] get [ {} ] contents",name,rs.size());
            results.addAll(rs);
        }

        if (nextContentProcessor != null) {
            results.addAll(nextContentProcessor.process(outputDataSource));
        }
        return results;

    }

    /**
     * 实际处理流程
     *
     * @param outputDataSource
     * @return
     */
    protected abstract List<BaseStoreDataSource> doProcess(OutputDataSource outputDataSource);


    /**
     * 链式处理
     *
     * @param outputDataSource
     * @return
     */
    public List<InputDataSource> processNextInput(OutputDataSource outputDataSource) {

        List<InputDataSource> nextInputs = new ArrayList<>();

        List<InputDataSource> ni = doProcessNextInput(outputDataSource);
        if (ni != null) {
            logger.info("this processor [ {} ] get [ {} ] next input source",name,ni.size());
            nextInputs.addAll(ni);
        }

        if (nextContentProcessor != null) {
            nextInputs.addAll(nextContentProcessor.processNextInput(outputDataSource));
        }

        return nextInputs;

    }

    protected abstract List<InputDataSource> doProcessNextInput(OutputDataSource outputDataSource);


}
