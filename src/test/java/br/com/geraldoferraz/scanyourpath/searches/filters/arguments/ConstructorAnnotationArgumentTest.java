package br.com.geraldoferraz.scanyourpath.searches.filters.arguments;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.geraldoferraz.scanyourpath.TestBase;
import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.examples.ConstructorLevelAnnotation;
import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.examples.ConstructorLevelAnnoted;

public class ConstructorAnnotationArgumentTest extends TestBase {

	private ConstructorAnnotationArgument constructorAnnotationArgument;

	@Before
	public void before() {
		constructorAnnotationArgument = new ConstructorAnnotationArgument(ConstructorLevelAnnotation.class);
	}
	
	@Test
	public void whenConstructorIsAnnotedWithConstructorLevelAnnotation(){
		assertTrue(constructorAnnotationArgument.validate(ConstructorLevelAnnoted.class));
	}
	
	@Test
	public void whenConstructorIsNotAnnotedWithConstructorLevelAnnotation(){
		assertFalse(constructorAnnotationArgument.validate(String.class));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void whenNullIsPassedToTheConstructor(){
		constructorAnnotationArgument = new ConstructorAnnotationArgument(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void whenNullIsPassedToValidate(){
		assertTrue(constructorAnnotationArgument.validate(null));
	}

}
