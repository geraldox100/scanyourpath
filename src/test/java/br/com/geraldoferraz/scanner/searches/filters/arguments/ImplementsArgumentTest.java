package br.com.geraldoferraz.scanner.searches.filters.arguments;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import br.com.geraldoferraz.scanner.TestBase;

public class ImplementsArgumentTest extends TestBase{
	
	private ImplementsArgument implementsArgument;
	
	@Before
	public void before(){
		implementsArgument = new ImplementsArgument(Argument.class);
	}
	
	@Test
	public void whenClassImplementsTestBase(){
		assertTrue(implementsArgument.validate(ImplementsArgument.class));
	}
	
	@Test
	public void whenClassDoesNotExtendTestBase(){
		assertFalse(implementsArgument.validate(String.class));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void whenNullIsPassedToTheConstructor(){
		new ImplementsArgument(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void whenNotInterfaceIsPassedToTheConstructor(){
		new ImplementsArgument(String.class);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void whenNullIsPassedToValidate(){
		assertTrue(implementsArgument.validate(null));
	}
	
	

}