package org.wusay.software.owlengine.core.datasource;


import lombok.Getter;

/**
 * 输入数据源,只包含一个url,可以使用自定义的input数据源覆盖
 */
@Getter
public class InputDataSource {


    private String url;


    public InputDataSource(String url) {
        this.url = url;
    }
}
