package com.ben.examples2;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class FileToMQ {
	private static CamelContext context;

	public static void main(String[] args) throws Exception {
		context = new DefaultCamelContext();
		
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		context.addRoutes(new RouteBuilder() {

	
			@Override
			public void configure() throws Exception {
				from("file:input_box?noop=true").
				to("activemq:queue:PROCESSED");
				
				
			}
		});
		while(true)
			context.start();
		
	}

	}


