package com.microservice.notificationserviceconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*/*")
public class NotificationServiceConsumerController {
	private static Logger logger = LoggerFactory.getLogger(NotificationServiceConsumerController.class);
	
	@Autowired
	MessageRepository messageRepository;

	@GetMapping(path="/consumer/test/sendSms", produces="Application/json")
	public ResponseEntity<Ack> callNotificationServiceSendSms() {
		Ack ack = this.messageRepository.sendNotificationToAdmins();
		return new ResponseEntity<Ack>(ack, HttpStatus.OK);
	}
	
	@GetMapping(path="/consumer/test/status", produces="Application/json")
	public ResponseEntity<MessageWrapper> getNotificationStatus() {
		MessageWrapper messageWrapper = this.messageRepository.getNotificationStatus();		
		logger.info(messageWrapper.toString());
		return new ResponseEntity<MessageWrapper>(messageWrapper, HttpStatus.OK);
	}
	
	@GetMapping(path="/consumer/test", produces="application/json")
	@ResponseBody
	public ResponseEntity<MessageWrapper> getTest() {
		MessageWrapper messageWrapper = new MessageWrapper();		
		return new ResponseEntity<MessageWrapper>(messageWrapper, HttpStatus.OK);
	}
	
}
