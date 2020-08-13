package org.wusay.software.owlengine.core.worker;

import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.wusay.software.owlengine.core.datasource.InputDataSource;
import org.wusay.software.owlengine.core.datasource.OutputDataSource;

import java.io.IOException;

/**
 * 责任链模式基础类
 */
public abstract class BaseSpider {

    protected final Logger logger;

    @Setter
    private BaseSpider nextSpider = null;

    private String name;


    public BaseSpider(String name) {
        logger = LogManager.getLogger(getClass());
        this.name = name;
    }

    public void setNextSpider(BaseSpider nextSpider) {
        this.nextSpider = nextSpider;
    }

    public OutputDataSource getContent(InputDataSource inputDataSource) throws IOException {


        if (match(inputDataSource)) {
            logger.info("spider [ {} ] can get this url [{}]", name, inputDataSource.getUrl());
            return doContent(inputDataSource);
        }

        if (nextSpider == null) {
            logger.error("no spider supports this url : {}", inputDataSource.getUrl());
            throw new IOException("no spider supports this input");
        }


        return nextSpider.getContent(inputDataSource);

    }

    protected abstract OutputDataSource doContent(InputDataSource inputDataSource);

    protected abstract boolean match(InputDataSource inputDataSource);


}
