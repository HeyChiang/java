package com.chiang.elastic;

import com.chiang.elastic.service.ElasticClient;
import org.elasticsearch.action.index.IndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jianghao
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    final
    ElasticClient client;

    public Application(ElasticClient client) {
        this.client = client;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        IndexResponse indexResponse = client.indexRequest();
        System.out.println(indexResponse.toString());
    }
}
