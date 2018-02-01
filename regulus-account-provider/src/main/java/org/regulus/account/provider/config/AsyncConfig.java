package org.regulus.account.provider.config;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {
    private static final String THREAD_NAME = "account-provider-";
    @Bean
    public AsyncTaskExecutor getAsyncExecutor() {
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        threadPool.setCorePoolSize(2);//当前线程数
        threadPool.setMaxPoolSize(50);// 最大线程数
        threadPool.setQueueCapacity(100);//线程池所使用的缓冲队列
        threadPool.setWaitForTasksToCompleteOnShutdown(true);//等待任务在关机时完成--表明等待所有线程执行完
        threadPool.setThreadNamePrefix(THREAD_NAME);
        threadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//        threadPool.initialize(); // 初始化
        return threadPool;
    }
}
