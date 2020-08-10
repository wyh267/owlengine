package org.wusay.software.owlengine.core;


import lombok.Getter;

/**
 * 基础数据类，保存爬虫爬取的基本信息
 */
@Getter
public abstract class BaseDataSource {

    /**
     * 地址
     */
    private String url;
    /**
     * 原始内容
     */
    private String originalContent;


    public BaseDataSource(String url, String originalContent) {
        this.url = url;
        this.originalContent = originalContent;
    }



    @Override
    public String toString() {
        return String.format("URL [%s] ,Content [ %s ]",url,originalContent);
    }

}
