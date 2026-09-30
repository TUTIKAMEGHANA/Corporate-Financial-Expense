package com.example.report;
import org.springframework.context.annotation.*;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.client.RestClient;
@Configuration
public class RestClientConfig {
 @Bean @LoadBalanced RestClient.Builder restClientBuilder(){return RestClient.builder();}
 @Bean RestClient restClient(RestClient.Builder b){return b.build();}
}
