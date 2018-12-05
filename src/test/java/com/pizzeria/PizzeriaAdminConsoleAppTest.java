package com.pizzeria;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PizzeriaAdminConsoleAppTest {
	
	PizzeriaAdminConsoleApp app = new PizzeriaAdminConsoleApp();
	
	@Test (timeout = 1000)
	public void testMenu() {
		Pizza.menuConsole();
	}

	@Test
	public void userInput() {
		assertNotNull(app.sc);
	}
	

	
	
}
