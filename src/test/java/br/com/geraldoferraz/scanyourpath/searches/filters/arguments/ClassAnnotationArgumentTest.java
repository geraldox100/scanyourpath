package br.com.geraldoferraz.scanyourpath.searches.filters.arguments;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.geraldoferraz.scanyourpath.TestBase;
import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.ClassAnnotationArgument;
import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.examples.ClassLevelAnnotation;
import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.examples.ClassLevelAnnoted;


public class ClassAnnotationArgumentTest extends TestBase {
	
	private ClassAnnotationArgument classAnnotationArgument;
	
	@Before
	public void before(){
		classAnnotationArgument = new ClassAnnotationArgument(ClassLevelAnnotation.class);
	}
	
	@Test
	public void whenClassIsAnnotedWithClassLevelAnnotation(){
		assertTrue(classAnnotationArgument.validate(ClassLevelAnnoted.class));
	}
	
	@Test
	public void whenClassIsNotAnnotedWithClassLevelAnnotation(){
		assertFalse(classAnnotationArgument.validate(String.class));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void whenNullIsPassedToTheConstructor(){
		classAnnotationArgument = new ClassAnnotationArgument(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void whenNullIsPassedToValidate(){
		assertTrue(classAnnotationArgument.validate(null));
	}

}
