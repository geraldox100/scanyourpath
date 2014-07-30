package br.com.geraldoferraz.scanyourpath.searches.filters.arguments;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.geraldoferraz.scanyourpath.TestBase;
import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.MethodAnnotationArgument;
import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.examples.MethodLevelAnnotation;
import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.examples.MethodLevelAnnoted;

public class MethodAnnotationArgumentTest extends TestBase {

	private MethodAnnotationArgument methodAnnotationArgument;

	@Before
	public void before() {
		methodAnnotationArgument = new MethodAnnotationArgument(MethodLevelAnnotation.class);
	}
	
	@Test
	public void whenMethodIsAnnotedWithMethodLevelAnnotation(){
		assertTrue(methodAnnotationArgument.validate(MethodLevelAnnoted.class));
	}
	
	@Test
	public void whenMethdoIsNotAnnotedWithMethodLevelAnnotation(){
		assertFalse(methodAnnotationArgument.validate(String.class));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void whenNullIsPassedToTheConstructor(){
		methodAnnotationArgument = new MethodAnnotationArgument(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void whenNullIsPassedToTheValidate(){
		assertTrue(methodAnnotationArgument.validate(null));
	}

}
