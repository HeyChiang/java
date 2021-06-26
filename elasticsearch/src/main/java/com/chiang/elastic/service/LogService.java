package com.chiang.elastic.service;

import org.elasticsearch.action.bulk.BulkRequest;
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
 * 提交日志数据
 * @author jianghao
 */
@Component
public class LogService {

    final
    RestHighLevelClient elasticsearchClient;

    public LogService(RestHighLevelClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }

    /**
     * 发送日志到 ElasticSearch
     */
    public IndexResponse indexRequest() throws IOException {

        HashMap<String,Object> map = new HashMap<>(0);
        map.put("api","1101");
        map.put("url", "https://www.zhihu.com/");
        map.put("header","header");
        map.put("request","{}");
        map.put("response","{}");
        map.put("employeeId","123");
        map.put("storeId","");
        map.put("env","dev");
        map.put("@timestamp",new Date());

        IndexRequest request = new IndexRequest("medicine-logs")
                .id(UUID.randomUUID().toString())
                .source(map)
                .setRefreshPolicy(IMMEDIATE);

        return elasticsearchClient.index(request, RequestOptions.DEFAULT);
    }

}
