package com.ssip.ibmmq.config;

import javax.jms.ConnectionFactory;
import javax.jms.Session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class JmsConfig {

	@Bean
	public JmsListenerContainerFactory jmsSignalListenerContainerFactory(ConnectionFactory connectionFactory) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		//factory.setAutoStartup(true);
		factory.setConnectionFactory(connectionFactory);
		// below settings are to make sure if any Runtime exception occurs or Running instance is down,
		// the message is still be present in the Queue
		factory.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
		factory.setSessionTransacted(true); 
		return factory;
	}
	
	/**
	 * This b
	 * @param connectionFactory
	 * @return
	 */
	@Bean
	public JmsListenerContainerFactory jmsDataListenerContainerFactory(ConnectionFactory connectionFactory) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		// This settings is to make sure the listener is not getting triggered on application startup
		factory.setAutoStartup(false);
		factory.setConnectionFactory(connectionFactory);
		// below settings are to make sure if any Runtime exception occurs or Running instance is down,
		// the message is still be present in the Queue
		factory.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);  
		factory.setSessionTransacted(true); 
		return factory;
	}

	/*
	 * @Bean public MessageConverter jacksonJmsMessageConverter() {
	 * MappingJackson2MessageConverter converter = new
	 * MappingJackson2MessageConverter();
	 * converter.setTargetType(MessageType.TEXT);
	 * converter.setTypeIdPropertyName("_Class_"); return converter; }
	 */
}
