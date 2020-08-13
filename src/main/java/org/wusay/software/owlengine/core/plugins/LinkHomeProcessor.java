package org.wusay.software.owlengine.core.plugins;

import org.wusay.software.owlengine.core.datasource.BaseStoreDataSource;
import org.wusay.software.owlengine.core.datasource.InputDataSource;
import org.wusay.software.owlengine.core.datasource.OutputDataSource;
import org.wusay.software.owlengine.core.worker.BaseContentProcessor;

import java.util.List;

public class LinkHomeProcessor extends BaseContentProcessor {



    public LinkHomeProcessor(String name) {
        super(name);
    }

    @Override
    protected List<BaseStoreDataSource> doProcess(OutputDataSource outputDataSource) {


        return null;
    }

    @Override
    protected List<InputDataSource> doProcessNextInput(OutputDataSource outputDataSource) {

        return null;
    }
}
