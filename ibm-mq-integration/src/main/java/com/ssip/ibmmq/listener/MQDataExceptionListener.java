package com.ssip.ibmmq.listener;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;

import org.springframework.stereotype.Component;

@Component
public class MQDataExceptionListener implements ExceptionListener{

	@Override
	public void onException(JMSException arg0) {
		System.out.println("Error Caught ..............");
		arg0.printStackTrace();
		
	}

}
