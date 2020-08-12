package org.wusay.software.owlengine.core;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.ToXContentObject;

import java.util.Collection;

/**
 * 本地存储服务
 */
public class StoreService {

    private static final Logger logger = LogManager.getLogger(StoreService.class);

    private Client client;


    public StoreService(Client client) {
        this.client = client;
    }




    public boolean storeContent(Collection<BaseStoreDataSource> objs) {

        BulkRequest bulkRequest = new BulkRequest();
        for (BaseStoreDataSource obj : objs) {
            IndexRequest indexRequest = new IndexRequest().index("ba").type("_doc").id("ss").source(obj);
            bulkRequest.add(indexRequest);
        }
        client.bulk(bulkRequest);
        return true;

    }



}
