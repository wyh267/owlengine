package org.wusay.software.owlengine.core;


import java.util.ArrayList;
import java.util.Collection;

/**
 * 处理器选择器
 */
public class ProcessorSelector {


    private Collection<Processor> processors;

    public ProcessorSelector() {
        processors = new ArrayList<Processor>();
    }


    public Processor selectProcessor(final BaseDataSource dataSource) {

        for (Processor processor :
                processors) {
            if (processor.match(dataSource)) {
                return processor;
            }
        }

        return null;

    }


}
