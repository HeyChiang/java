package com.spring.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * 执行异步请求
 * @author JiangHao
 */
@Component
public class AppRunner implements CommandLineRunner {

  private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

  private final GitHubLookupService gitHubLookupService;

  public AppRunner(GitHubLookupService gitHubLookupService) {
    this.gitHubLookupService = gitHubLookupService;
  }

  @Override
  public void run(String... args) throws Exception {
    // Start the clock
    long start = System.currentTimeMillis();

    // Kick of multiple, asynchronous lookups
    // 如果不使用CompletableFuture<ThreadUser>，则返回null，但是异步线程还是会继续下去
    CompletableFuture<ThreadUser> page1 = gitHubLookupService.findUser("PivotalSoftware");
    CompletableFuture<ThreadUser> page2 = gitHubLookupService.findUser("CloudFoundry");
    CompletableFuture<ThreadUser> page3 = gitHubLookupService.findUser("Spring-Projects");

    // Wait until they are all done
    CompletableFuture.allOf(page1,page2,page3).join();

    // Print results, including elapsed time
    logger.info("ThreadName："+Thread.currentThread().getName());
    logger.info(" --> Elapsed time: " + (System.currentTimeMillis() - start));
    logger.info("--> " + page1.get());
    logger.info("--> " + page2.get());
    logger.info("--> " + page3.get());

  }

}
