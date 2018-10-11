package com.ssip.ibmmq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.backoff.ExponentialBackOff;
import org.springframework.util.backoff.FixedBackOff;

import com.ibm.mq.jms.MQConnectionFactory;
import com.ssip.ibmmq.listener.MQDataExceptionListener;
import com.ssip.ibmmq.listener.MQDataMessageListener;

@Configuration
@EnableTransactionManagement
public class JmsConfigForExceptionHandling {

	@Autowired
	MQDataMessageListener mqListener;
	@Value("${data.queue.name}")
	private String dataQueue;
	
	@Autowired
	MQDataExceptionListener exceptionListener;
	
	@Bean
	public DefaultMessageListenerContainer defaultMessageContainer(MQConnectionFactory mqConnectionFactory) {
		
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(mqConnectionFactory);
		container.setDestinationName(dataQueue);
		container.setupMessageListener(mqListener);
		container.setExceptionListener(exceptionListener);
		
		//container.start();
	
		// Setting of the retry for listener for 5 times if any JMSException caught by JmsListnerContainer
		FixedBackOff fixedBackOff = new FixedBackOff();
		fixedBackOff.setInterval(2000);
		fixedBackOff.setMaxAttempts(5);
		container.setBackOff(fixedBackOff);
		
		return container;
} 
}
