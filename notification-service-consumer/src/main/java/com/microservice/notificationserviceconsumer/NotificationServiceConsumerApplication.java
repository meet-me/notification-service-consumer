package com.microservice.notificationserviceconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class NotificationServiceConsumerApplication {

//	@Value("${notification.service.url}")
	public static final String MESSAGE_SERVICE_URL= "http://notification-service/"; //"http://localhost:2222/";
	
	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceConsumerApplication.class, args);
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public MessageRepository messageRepository(){
		return new RemoteMessageRepository(MESSAGE_SERVICE_URL);
	}

}
