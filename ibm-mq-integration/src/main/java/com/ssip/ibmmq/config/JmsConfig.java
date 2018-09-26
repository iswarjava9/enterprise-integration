package com.ssip.ibmmq.config;

import javax.jms.ConnectionFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class JmsConfig {

	@Bean
	public JmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory connFactory) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(connFactory);
		factory.setSessionTransacted(false); // auto acknowledge mode is off
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
