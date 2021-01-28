package com.spring.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志打印
 * @author Chiang
 */
@RestController
@SpringBootApplication
public class ApplicationLogback implements CommandLineRunner {
    static final Logger logger = LoggerFactory.getLogger(ApplicationLogback.class);

    public void doIt() {
        logger.debug("Did it debug!");
        logger.info("Info myinfo--");
        logger.error("error --");
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ApplicationLogback.class, args);
        run.close();
    }

    @Override
    public void run(String... args) throws Exception {
        doIt();
    }
}