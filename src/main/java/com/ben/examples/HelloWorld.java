package com.ben.examples;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class HelloWorld {

	private static CamelContext context;

	public static void main(String[] args) throws Exception {
		context = new DefaultCamelContext();
		context.addRoutes(new HelloWorldRoute());
		context.start();
	}

}
