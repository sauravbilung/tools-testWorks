package com.learning.junit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class GreetingImplTest {

	private Greeting greeting;

	@BeforeEach
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
		Assertions.assertNotNull(result);
		Assertions.assertEquals("Hello JUnit", result);
	}

	@Test
	public void greetShouldThrowAnException_For_NameIsNull() {
		System.out.println("greetShouldThrowAnException_For_NameIsNull");		
		Assertions.assertThrows(IllegalArgumentException.class,()->{
			greeting.greet(null);	
		});
	}

	@Test
	public void greetShouldThrowANException_For_NameISBlank() {
		System.out.println("greetShouldThrowANException_For_NameISBlank");
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			greeting.greet("");	
		});		
	}

	@AfterEach
	public void cleanup() {
		// this will be called after the test method has executed.
		System.out.println("Cleanup");
		greeting = null;
	}

}
