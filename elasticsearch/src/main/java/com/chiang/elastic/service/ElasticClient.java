package com.chiang.elastic.service;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import static org.elasticsearch.action.support.WriteRequest.RefreshPolicy.IMMEDIATE;

/**
 * 使用索引
 * @author jianghao
 */
@Component
public class ElasticClient {

    final
    RestHighLevelClient elasticsearchClient;

    public ElasticClient(RestHighLevelClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }

    public IndexResponse indexRequest() throws IOException {

        HashMap<String,Object> map = new HashMap<>(0);
        map.put("api","1101");
        map.put("clientIp","192.168.0.1");
        map.put("os","Chrome");
        map.put("url", "https://www.zhihu.com/");
        map.put("request","{}");
        map.put("response","{}");
        map.put("@timestamp",new Date());

        IndexRequest request = new IndexRequest("medicine-logs")
                .id(UUID.randomUUID().toString())
                .source(map)
                .setRefreshPolicy(IMMEDIATE);

        return elasticsearchClient.index(request, RequestOptions.DEFAULT);
    }

}
