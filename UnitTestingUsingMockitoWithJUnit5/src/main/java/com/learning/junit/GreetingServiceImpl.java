package com.learning.junit;

public class GreetingServiceImpl implements GreetingService {

	@Override
	public String greet(String name) {
		if (name == null || name.length() == 0) {
			throw new IllegalArgumentException();
		}
		return "Hello " + name;
	}

}
