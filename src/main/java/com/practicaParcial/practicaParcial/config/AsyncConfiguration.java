package com.practicaParcial.practicaParcial.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfiguration {

    /*
    @Value("#{executor.CORE_POOL_SIZE: 50}")
    private Integer CORE_POOL_SIZE;
    @Value("#{executor.MAX_POOL_SIZE: 50}")
    private Integer MAX_POOL_SIZE;
    @Value("#{executor.QUEUE_CAPACITY: 50}")
    private Integer QUEUE_CAPACITY;
*/
    @Bean("threadPoolTaskExecutor")
    public Executor asyncConfiguration(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(100);
        executor.setMaxPoolSize(200);
        executor.setQueueCapacity(50);
        executor.initialize();
        return executor;

    }
}
