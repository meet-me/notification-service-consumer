package com.microservice.notificationserviceconsumer;

public interface MessageRepository {

	Ack sendNotificationToAdmins();

	MessageWrapper getNotificationStatus();
}
