package br.com.geraldoferraz.scanyourpath.searches.filters.arguments;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import br.com.geraldoferraz.scanyourpath.TestBase;
import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.ExtendsArgument;
import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.examples.MethodLevelAnnotation;

public class ExtendsArgumentTest extends TestBase{
	
	private ExtendsArgument extendsArgument;
	
	@Before
	public void before(){
		extendsArgument = new ExtendsArgument(TestBase.class);
	}
	
	@Test
	public void whenClassExtendsTestBase(){
		assertTrue(extendsArgument.validate(ExtendsArgumentTest.class));
	}
	
	@Test
	public void whenClassDoesNotExtendTestBase(){
		assertFalse(extendsArgument.validate(String.class));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void whenNullIsPassedToTheConstructor(){
		new ExtendsArgument(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void whenNullIsPassedToValidate(){
		assertTrue(extendsArgument.validate(null));
	}
	
	@Test
	public void whenAnnotationPassedToValidate(){
		assertFalse(extendsArgument.validate(MethodLevelAnnotation.class));
	}
	
	

}
