package com.ben.examples2;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class ProdAndComs {
	private static CamelContext context;

	public static void main(String[] args) throws Exception {

		context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				from("direct:start").process(new Processor() {

					public void process(Exchange exchange) throws Exception {
						// System.out.println("Inside processor");
						String message = exchange.getMessage().getBody(String.class);
						message = message + " By Ben";
						exchange.getMessage().setBody(message, String.class);
						
					}
				}).to("seda:end");

			}
		});

		context.start();

		ProducerTemplate producerTemplate = context.createProducerTemplate();
		producerTemplate.sendBody("direct:start", "Hello");

		ConsumerTemplate consumerTemplate = context.createConsumerTemplate();
		String str = consumerTemplate.receiveBody("seda:end", String.class);
		System.out.println(str);

	}

}
