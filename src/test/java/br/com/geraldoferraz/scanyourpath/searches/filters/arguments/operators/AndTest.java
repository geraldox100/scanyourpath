package br.com.geraldoferraz.scanyourpath.searches.filters.arguments.operators;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.geraldoferraz.scanyourpath.TestBase;
import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.operators.And;

public class AndTest extends TestBase {

	private And and = new And();

	@Before
	public void befande() {
		and = new And();
	}

	@Test
	public void whenArgumentsAreBothTrue() {
		assertTrue(and.validate(true, true));
	}

	@Test
	public void whenTheFirstArgumentIsTrue() {
		assertFalse(and.validate(true, false));
	}

	@Test
	public void whenTheSecondArgumentIsTrue() {
		assertFalse(and.validate(false, true));
	}

	@Test
	public void whenArgumentsAreBothFalse() {
		assertFalse(and.validate(false, false));
	}
}
