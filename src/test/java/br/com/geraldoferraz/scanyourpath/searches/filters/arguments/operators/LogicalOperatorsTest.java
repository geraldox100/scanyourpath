package br.com.geraldoferraz.scanyourpath.searches.filters.arguments.operators;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;

import org.junit.Test;

import br.com.geraldoferraz.scanyourpath.TestBase;
import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.operators.LogicalOperators;

public class LogicalOperatorsTest extends TestBase {
	
	@Test
	public void assertThatIsSameInstance() {
		assertSame(LogicalOperators.OR,LogicalOperators.OR);
		assertSame(LogicalOperators.AND,LogicalOperators.AND);
	}
	
	@Test
	public void whenArgumentsAreBothTrueOr() {
		assertTrue(LogicalOperators.OR.validate(true, true));
	}

	@Test
	public void whenTheFirstArgumentIsTrueOr() {
		assertTrue(LogicalOperators.OR.validate(true, false));
	}

	@Test
	public void whenTheSecondArgumentIsTrueOr() {
		assertTrue(LogicalOperators.OR.validate(false, true));
	}

	@Test
	public void whenArgumentsAreBothFalseOr() {
		assertFalse(LogicalOperators.OR.validate(false, false));
	}
	
	@Test
	public void whenArgumentsAreBothTrueAnd() {
		assertTrue(LogicalOperators.AND.validate(true, true));
	}

	@Test
	public void whenTheFirstArgumentIsTrueAnd() {
		assertFalse(LogicalOperators.AND.validate(true, false));
	}

	@Test
	public void whenTheSecondArgumentIsTrueAnd() {
		assertFalse(LogicalOperators.AND.validate(false, true));
	}

	@Test
	public void whenArgumentsAreBothFalseAnd() {
		assertFalse(LogicalOperators.AND.validate(false, false));
	}
}
