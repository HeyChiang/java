package com.chiang.elastic;

import com.chiang.elastic.service.LogService;
import org.elasticsearch.action.index.IndexResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jianghao
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    final
    LogService logService;

    public Application(LogService logService) {
        this.logService = logService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        IndexResponse indexResponse = logService.indexRequest();
        System.out.println(indexResponse.toString());
    }
}
