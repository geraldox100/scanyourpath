package br.com.geraldoferraz.scanyourpath.searches.filters.arguments;

import static junit.framework.Assert.assertSame;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import br.com.geraldoferraz.scanyourpath.TestBase;
import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.Argument;
import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.CombinableArgument;

public class CombinableArgumentTest extends TestBase {

	private CombinableArgument combinableArgument;
	@Mock
	private Argument firstArgument;
	@Mock
	private Argument nextArgument;

	private Class<?> clazz = String.class;

	@Before
	public void before() {

		combinableArgument = new CombinableArgument(firstArgument);
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenUseOrMethodPassingNullArgument() {
		combinableArgument.or(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenUseAndMethodPassingNullArgument() {
		combinableArgument.and(null);
	}

	@Test
	public void whenUseAndMethod() {
		assertSame(combinableArgument, combinableArgument.and(nextArgument));
	}

	@Test
	public void whenUseOrMethod() {
		assertSame(combinableArgument, combinableArgument.or(nextArgument));
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenValidateWithNull() {

		combinableArgument.validate(null);

	}

	@Test
	public void whenValidateWithOutNextArgument() {

		combinableArgument.validate(clazz);

		verify(nextArgument, never()).validate(clazz);
		verify(firstArgument, times(1)).validate(clazz);

	}

	@Test
	public void whenValidateWithOrNextArgument() {

		combinableArgument.or(nextArgument);

		combinableArgument.validate(clazz);

		verify(nextArgument, times(1)).validate(clazz);
		verify(firstArgument, times(1)).validate(clazz);

	}

	@Test
	public void whenValidateWithAndNextArgument() {

		combinableArgument.and(nextArgument);

		combinableArgument.validate(clazz);

		verify(nextArgument, times(1)).validate(clazz);
		verify(firstArgument, times(1)).validate(clazz);

	}

	@Test
	public void whenValidateAndBothArgumentsArTrue_And() {

		when(firstArgument.validate(clazz)).thenReturn(true);
		when(nextArgument.validate(clazz)).thenReturn(true);

		boolean result = combinableArgument.and(nextArgument).validate(clazz);

		assertTrue(result);

	}

	@Test
	public void whenValidateAndBothArgumentsArFalseAnd() {

		when(firstArgument.validate(clazz)).thenReturn(false);
		when(nextArgument.validate(clazz)).thenReturn(false);

		boolean result = combinableArgument.and(nextArgument).validate(clazz);

		assertFalse(result);

	}

	@Test
	public void whenValidateAndOnlyFirstArgumentsIsTrue_And() {

		when(firstArgument.validate(clazz)).thenReturn(true);
		when(nextArgument.validate(clazz)).thenReturn(false);

		boolean result = combinableArgument.and(nextArgument).validate(clazz);

		assertFalse(result);

	}

	@Test
	public void whenValidateAndOnlyNextArgumentsIsTrue_And() {

		when(firstArgument.validate(clazz)).thenReturn(false);
		when(nextArgument.validate(clazz)).thenReturn(true);

		boolean result = combinableArgument.and(nextArgument).validate(clazz);

		assertFalse(result);

	}

	@Test
	public void whenValidateAndBothArgumentsArTrue_Or() {

		when(firstArgument.validate(clazz)).thenReturn(true);
		when(nextArgument.validate(clazz)).thenReturn(true);

		boolean result = combinableArgument.or(nextArgument).validate(clazz);

		assertTrue(result);

	}

	@Test
	public void whenValidateAndBothArgumentsArFalse_Or() {

		when(firstArgument.validate(clazz)).thenReturn(false);
		when(nextArgument.validate(clazz)).thenReturn(false);

		boolean result = combinableArgument.or(nextArgument).validate(clazz);

		assertFalse(result);

	}

	@Test
	public void whenValidateAndOnlyeNextArgumentIsTrue_Or() {

		when(firstArgument.validate(clazz)).thenReturn(false);
		when(nextArgument.validate(clazz)).thenReturn(true);

		boolean result = combinableArgument.or(nextArgument).validate(clazz);

		assertTrue(result);

	}

	@Test
	public void whenValidateAndOnlyeFirstArgumentIsTrue_Or() {

		when(firstArgument.validate(clazz)).thenReturn(true);
		when(nextArgument.validate(clazz)).thenReturn(false);

		boolean result = combinableArgument.or(nextArgument).validate(clazz);

		assertTrue(result);

	}

}
