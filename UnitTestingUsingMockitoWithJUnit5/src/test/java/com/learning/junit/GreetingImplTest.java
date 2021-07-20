package com.learning.junit;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GreetingImplTest {

	@Mock
	private GreetingService service;

	// Will initialize this object and will also inject other initialized
	// objects(mocks)
	@InjectMocks
	private GreetingImpl greeting;

	@Test
	public void greetShouldReturnAValidOutput() {
		System.out.println("greetShouldReturnAValidOutput");
		when(service.greet("JUnit")).thenReturn("Hello JUnit");
		String result = greeting.greet("JUnit");
		Assertions.assertNotNull(result);
		Assertions.assertEquals("Hello JUnit", result);
	}

	@Test
	public void greetShouldThrowAnException_For_NameIsNull() {
		System.out.println("greetShouldThrowAnException_For_NameIsNull");
		doThrow(IllegalArgumentException.class).when(service).greet(null);
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			greeting.greet(null);
		});
	}

	@Test
	public void greetShouldThrowANException_For_NameISBlank() {
		System.out.println("greetShouldThrowANException_For_NameISBlank");
		doThrow(IllegalArgumentException.class).when(service).greet("");
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			greeting.greet("");
		});
	}

}
