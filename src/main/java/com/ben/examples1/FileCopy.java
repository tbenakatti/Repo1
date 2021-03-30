package com.ben.examples1;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class FileCopy {
	private static CamelContext context;

	public static void main(String[] args) throws Exception {
		context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {
				from("file:input_box?noop=true").
				to("file:output_box");
				
			}
		});
		
		while(true)
			context.start();
		
	}

}
