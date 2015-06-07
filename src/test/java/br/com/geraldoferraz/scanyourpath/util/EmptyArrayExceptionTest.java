package br.com.geraldoferraz.scanyourpath.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EmptyArrayExceptionTest {
	
	private EmptyArrayException emptyArrayExeception;

	@Test
	public void whenCreatingAnEmptyStringExceptio() {
		emptyArrayExeception = new EmptyArrayException();
		assertEquals(EmptyArrayException.PREFIX, emptyArrayExeception.getMessage());
	}

}
