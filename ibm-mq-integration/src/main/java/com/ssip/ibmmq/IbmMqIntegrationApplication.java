package com.ssip.ibmmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class IbmMqIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(IbmMqIntegrationApplication.class, args);
	}
}
