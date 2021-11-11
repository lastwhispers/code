package cn.cunchang.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.integration.config.annotation.EnableBatchIntegration;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

/**
 * @EnableBatchProcessing 自动注册springbatch的关键组件
 *  比如 JobBuilderFactory、StepBuilderFactory
 *
 */
@Configuration
@EnableBatchProcessing
@EnableBatchIntegration
@EnableBinding(BatchMessageChannel.class)
public class BatchConfiguration {



}