package com.ssip.ibmmq;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

	@JmsListener(destination = "testqueue1")
	// @SendTo("outbound.queue")
	public void receiveMessage(final Message jsonMessage) throws JMSException {
		String messageData = null;
		System.out.println("Received message " + jsonMessage);
		if(jsonMessage.getBody(String.class).equals("hello")){
			throw new JMSException("DATA FAILED");
		}
		System.out.println(jsonMessage.getBody(String.class));
		String response = null;
		
	}
}
