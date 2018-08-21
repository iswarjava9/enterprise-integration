package com.ssip.ibmmq;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

	@JmsListener(destination = "myqueue1")
	// @SendTo("outbound.queue")
	public void receiveMessage(final Message jsonMessage) throws JMSException {
		String messageData = null;
		System.out.println("Received message " + jsonMessage);
		String response = null;
		
	}
}
