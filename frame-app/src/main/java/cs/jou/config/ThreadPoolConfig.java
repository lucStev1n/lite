package cs.jou.config;

import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class ThreadPoolConfig {

    @Bean
    public Executor executor(TaskExecutionProperties properties) {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();

        taskExecutor.setCorePoolSize(properties.getPool().getCoreSize());
        taskExecutor.setMaxPoolSize(properties.getPool().getMaxSize());
        taskExecutor.setKeepAliveSeconds((int) properties.getPool().getKeepAlive().getSeconds());
        taskExecutor.setQueueCapacity(properties.getPool().getQueueCapacity());
        taskExecutor.setThreadNamePrefix(properties.getThreadNamePrefix());

        taskExecutor.initialize();
        return taskExecutor;
    }

    @Bean
    public SimpleApplicationEventMulticaster applicationEventMulticaster(Executor executor) {
        SimpleApplicationEventMulticaster multicaster = new SimpleApplicationEventMulticaster();

        multicaster.setTaskExecutor(executor);

        return multicaster;
    }
}
