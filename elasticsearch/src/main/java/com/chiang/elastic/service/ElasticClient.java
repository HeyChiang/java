package com.chiang.elastic.service;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

import static org.elasticsearch.action.support.WriteRequest.RefreshPolicy.IMMEDIATE;

/**
 * 使用索引
 * @author jianghao
 */
@Component
public class ElasticClient {

    final
    RestHighLevelClient highLevelClient;

    public ElasticClient(RestHighLevelClient highLevelClient) {
        this.highLevelClient = highLevelClient;
    }

    public IndexResponse indexRequest() throws IOException {

        HashMap<String,String> map = new HashMap<>(0);
        map.put("feature", "final test");
        map.put("name","kuaile");

        IndexRequest request = new IndexRequest("spring-data")
                .id("test")
                .source(map)
                .index("spring-data4")
                .setRefreshPolicy(IMMEDIATE);

        IndexResponse response = highLevelClient.index(request, RequestOptions.DEFAULT);
        return response;
    }

}
