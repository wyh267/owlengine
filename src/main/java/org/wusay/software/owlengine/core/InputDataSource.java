package org.wusay.software.owlengine.core;


import lombok.Getter;

/**
 * 输入数据源
 */
@Getter
public class InputDataSource {


    private String url;


    public InputDataSource(String url) {
        this.url = url;
    }
}
