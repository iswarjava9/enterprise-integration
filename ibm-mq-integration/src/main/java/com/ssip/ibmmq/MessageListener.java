package com.ssip.ibmmq;

import java.util.Enumeration;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.QueueBrowser;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.JmsListenerEndpointRegistry;
import org.springframework.jms.core.BrowserCallback;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

	@Autowired
	JmsTemplate jmsTemplate;
	
	@Autowired
	JmsListenerEndpointRegistry registry;

	@JmsListener(destination = "testqueue1", containerFactory = "jmsSignalListenerContainerFactory")
	// @SendTo("outbound.queue")
	public void receiveMessage(final Message jsonMessage) throws JMSException {
		try {

			
			System.out.println("Received message " + jsonMessage);
			
			// This is to simulate Rollback on Any Exception Thrown
			if (jsonMessage.getBody(String.class).equals("hello")) {
				throw new JMSException("DATA FAILED");
			}
			System.out.println("Processing message start ..........");
			Thread.sleep(10000);
			// This call is to start the JMS Listener explicitly
			registry.getListenerContainer("dataq").start();
			System.out.println("Processing message end .............");

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(jsonMessage.getBody(String.class));
		
		// logic to stop the dataq listener if the queue is empty
		while(isStillMessagePresentInTheQueue()){
			
		}
		// This call is to stop the JMS Listener explicitly whcn no message is present in the queue
		registry.getListenerContainer("dataq").stop();
		

	}
	
	// This method is to check if still any message is present in the queue
	private boolean isStillMessagePresentInTheQueue(){
		boolean flag  = jmsTemplate.browse("data.queue", new BrowserCallback<Boolean>() {
			public Boolean doInJms(final Session session, final QueueBrowser browser){
				Enumeration enms;
				boolean flg = false;
				try {
					enms = browser.getEnumeration();
					flg = enms.hasMoreElements();;
					 System.out.println("flag --- "+flg);
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}					
					
				return flg;				
				
			}
		});
		
		return flag;
	}
	
	/**
	 * This listener is triggered explicitly/programaticaly based on id="dataq" 
	 */
	
	@JmsListener(destination = "data.queue",id="dataq", containerFactory = "jmsDataListenerContainerFactory")	
	public void receiveMessageData(final Message jsonMessage) throws JMSException {
		try {

			String messageData = null;
			System.out.println("Received message " + jsonMessage);
			if (jsonMessage.getBody(String.class).equals("hello")) {
				throw new JMSException("DATA FAILED");
			}
			System.out.println("Processing message start ..........");
			Thread.sleep(10000);
			System.out.println("Processing message end .............");
          /*  if(jsonMessage.getBody(String.class).equals("data2")){
            	registry.getListenerContainer("dataq").stop();
            }*/
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(jsonMessage.getBody(String.class));
		String response = null;

		//readDataQueueAndProcessMessage();
	}
	
	
	// This method is to read the message from the queue synchronously
	public void readDataQueueAndProcessMessage() {

		jmsTemplate.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
		// jmsTemplate.setSessionTransacted(true);
		Message message = jmsTemplate.receive("data.queue");
		System.out.println("Data -----" + message);
		try {
			System.out.println("Start data processing ----------------");
			Thread.sleep(10000);
			System.out.println("End data processing ----------------");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
}
