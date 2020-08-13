package org.wusay.software.owlengine.core.worker;

import lombok.Setter;
import org.wusay.software.owlengine.core.datasource.InputDataSource;
import org.wusay.software.owlengine.core.datasource.OutputDataSource;

import java.io.IOException;

/**
 * 责任链模式基础类
 */
public abstract class BaseSpider {

    @Setter
    private BaseSpider nextSpider = null;

    private String name;


    public BaseSpider(String name) {
        this.name = name;
    }

    public void setNextSpider(BaseSpider nextSpider){
        this.nextSpider = nextSpider;
    }

    public OutputDataSource getContent(InputDataSource inputDataSource) throws IOException {



        if (match(inputDataSource)){
           return doContent(inputDataSource);
        }

        if (nextSpider == null) {
            throw new IOException("no spider supports this input");
        }


        return nextSpider.getContent(inputDataSource);

    }

    protected abstract OutputDataSource doContent(InputDataSource inputDataSource);

    protected abstract boolean match(InputDataSource inputDataSource);


}
