package top.shahow.deliverymailservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @author shahow
 * @date 2022-05-11
 */
@Slf4j
@Configuration
public class DefaultThreadPoolConfig {

    private final static int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    private final static BlockingDeque<Runnable> TASK_QUEUE = new LinkedBlockingDeque<>();

    private final static ThreadGroup THREAD_GROUP = new ThreadGroup("delivery-thread");

    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(AVAILABLE_PROCESSORS,
                AVAILABLE_PROCESSORS * 2,
                1,
                TimeUnit.HOURS,
                TASK_QUEUE,
                r -> new Thread(THREAD_GROUP, r)
        );
        log.info("默认线程池初始化完毕!");
        return executor;
    }

}
