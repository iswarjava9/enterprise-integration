package com.ssip.ibmmq.listener;

import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.stereotype.Component;



@Component
public class MQDataMessageListener implements MessageListener{

	@Override
	public void onMessage(Message arg0) {
		System.out.println(arg0);
		
	}

}
