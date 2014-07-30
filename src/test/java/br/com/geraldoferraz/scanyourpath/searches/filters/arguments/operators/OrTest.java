package br.com.geraldoferraz.scanyourpath.searches.filters.arguments.operators;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.geraldoferraz.scanyourpath.TestBase;
import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.operators.Or;

public class OrTest extends TestBase {

	private Or or = new Or();

	@Before
	public void before() {
		or = new Or();
	}

	@Test
	public void whenArgumentsAreBothTrue() {
		assertTrue(or.validate(true, true));
	}

	@Test
	public void whenTheFirstArgumentIsTrue() {
		assertTrue(or.validate(true, false));
	}

	@Test
	public void whenTheSecondArgumentIsTrue() {
		assertTrue(or.validate(false, true));
	}

	@Test
	public void whenArgumentsAreBothFalse() {
		assertFalse(or.validate(false, false));
	}

}
