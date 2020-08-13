package org.wusay.software.owlengine.core.plugins;

import org.wusay.software.owlengine.core.datasource.InputDataSource;
import org.wusay.software.owlengine.core.datasource.OutputDataSource;
import org.wusay.software.owlengine.core.worker.BaseSpider;

public class LinkHomeSpider extends BaseSpider {


    public LinkHomeSpider(String name) {
        super(name);
    }

    @Override
    protected OutputDataSource doContent(InputDataSource inputDataSource) {
        return new OutputDataSource(inputDataSource.getUrl(),"");
    }

    @Override
    protected boolean match(InputDataSource inputDataSource) {
        return true;
    }
}
