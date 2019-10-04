package com.microservice.notificationserviceconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class RemoteMessageRepository implements MessageRepository {
	
	@Autowired
	protected RestTemplate restTemplate;
	
	protected String remoteServiceUrl;
	
	public RemoteMessageRepository(String serviceUrl) {
		this.remoteServiceUrl = serviceUrl.startsWith("http") ? serviceUrl
				: "http://" + serviceUrl;
	}

	@Override
	public Ack sendNotificationToAdmins() {
		Ack ack = restTemplate.getForObject(this.remoteServiceUrl+"notify", Ack.class);
		return ack;
	}

	@Override
	public MessageWrapper getNotificationStatus() {
		MessageWrapper messageWrapper = restTemplate.getForObject(this.remoteServiceUrl+"status", MessageWrapper.class);
		return messageWrapper;
	}
	
}
