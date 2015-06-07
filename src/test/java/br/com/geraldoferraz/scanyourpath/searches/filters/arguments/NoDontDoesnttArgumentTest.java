package br.com.geraldoferraz.scanyourpath.searches.filters.arguments;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import br.com.geraldoferraz.scanyourpath.TestBase;
import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.Argument;
import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.NoDontDoesnttArgument;

public class NoDontDoesnttArgumentTest extends TestBase {
	
	@Mock
	private Argument argument;
	
	private Class<?> clazz;
	
	@Before
	public void before(){
		clazz = String.class;
	}
	
	
	@Test
	public void whenPassAnArgumentThatReturnsTrueToNotArgument(){
		when(argument.validate(clazz)).thenReturn(true);
		assertFalse(new NoDontDoesnttArgument(argument).validate(clazz));
	}
	
	@Test
	public void whenPassAnArgumentThatReturnsFalseToNotArgument(){
		when(argument.validate(clazz)).thenReturn(false);
		assertTrue(new NoDontDoesnttArgument(argument).validate(clazz));
	}
	
	

}
