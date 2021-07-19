package com.junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GreetingImplTest {

	private Greeting greeting;

	@Before
	public void setup() {
		// This will be called before every test method
		// Check the console. Output are like setup then sysout outputs
		System.out.println("setup");
		greeting = new GreetingImpl();
	}

	@Test
	public void greetShouldReturnAValidOutput() {
		System.out.println("greetShouldReturnAValidOutput");
		String result = greeting.greet("JUnit");
		assertNotNull(result);
		assertEquals("Hello JUnit", result);
	}

	@Test(expected = IllegalArgumentException.class)
	public void greetShouldThrowAnException_For_NameIsNull() {
		System.out.println("greetShouldThrowAnException_For_NameIsNull");
		greeting.greet(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void greetShouldThrowANException_For_NameISBlank() {
		System.out.println("greetShouldThrowANException_For_NameISBlank");
		greeting.greet("");
	}

	@After
	public void cleanup() {
		// this will be called after the test method has executed.
		System.out.println("Cleanup");
		greeting = null;
	}

}
