package org.wusay.software.owlengine.core;

import org.elasticsearch.common.Strings;
import org.elasticsearch.common.xcontent.ToXContentObject;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;


/**
 * 存储的数据基础类,包含最基础的存储信息
 */
public abstract class BaseStoreDataSource implements ToXContentObject {

    private String indexName;
    private String id;
    private long timestamp;


    public BaseStoreDataSource(String indexName, String id, long timestamp) {
        this.indexName = indexName;
        this.id = id;
        this.timestamp = timestamp;
    }

    public XContentBuilder toXContent(XContentBuilder xContentBuilder, Params params) throws IOException {
        xContentBuilder.startObject();
        doXContent(xContentBuilder,params);
        return xContentBuilder.endObject();
    }


    protected abstract void doXContent(XContentBuilder builder, Params params) throws IOException;


    @Override
    public String toString(){
        return Strings.toString(this);
    }

}
