package br.com.geraldoferraz.scanyourpath.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.geraldoferraz.scanyourpath.util.EmptyStringException;

public class EmptyStringExceptionTest {

	private EmptyStringException emptyStringExeception;

	@Test
	public void whenCreatingAnEmptyStringExceptio() {
		emptyStringExeception = new EmptyStringException("Hello");
		assertEquals(EmptyStringException.PREFIX+"Hello", emptyStringExeception.getMessage());
	}

}
