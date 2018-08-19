package com.ssip.camelrest;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class BookingRoute extends RouteBuilder{
	
	 @Override
	    public void configure() throws Exception {
	      
	        restConfiguration().host("localhost").port(8001);

	        from("timer:hello?period={{timer.period}}")
	            .setHeader("id", simple("${random(1,3)}"))
	            .to("rest:get:bookings/{id}")
	            .log("${body}");
	    }

}
