package com.learning.junit;

public class GreetingImpl implements Greeting{

	private GreetingService service;
	
	@Override
	public String greet(String name) {
		return service.greet(name);	  	
	}

}
