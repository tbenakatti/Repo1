package com.ben.examples3;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class ActiveMQConsumer {

	private static CamelContext context;

	public static void main(String[] args) throws Exception {
		context = new DefaultCamelContext();
		
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		context.addRoutes(new RouteBuilder() {

	
			@Override
			public void configure() throws Exception {
				
				from("activemq:queue:PROCESSED").to("seda:end");
				
			}
		});
		
		
		
		ConsumerTemplate consumerTemplate = context.createConsumerTemplate();
		String message = consumerTemplate.receiveBody("seda:end",String.class);
		System.out.println(message);
		
		//while(true)
		context.start();
		
	}
}
