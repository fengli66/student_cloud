package com.lifeng.stu_ckient_hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class StuCkientHystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(StuCkientHystrixApplication.class, args);
    }

    /**
     * 实例化RestTemplate
     * RestTempl是spring提供的用于访问Rest服务的客户端。
     * 它提供了多种便捷访问远程Http服务的方法，能够大大提高客户的编写效率
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
