package org.wusay.software.owlengine.core;


import lombok.Getter;

/**
 * 基础数据类，保存爬虫爬取的基本信息,基础信息包含两部分,一部分是url,一部分是原始数据,可以使用自定义数据覆盖
 */
@Getter
public class OutputDataSource {

    /**
     * 地址
     */
    private String url;
    /**
     * 原始内容
     */
    private String originalContent;


    public OutputDataSource(String url, String originalContent) {
        this.url = url;
        this.originalContent = originalContent;
    }



    @Override
    public String toString() {
        return String.format("URL [%s] ,Content [ %s ]",url,originalContent);
    }

}
