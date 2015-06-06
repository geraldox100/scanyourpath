package br.com.geraldoferraz.scanyourpath.searches.filters.arguments;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.geraldoferraz.scanyourpath.TestBase;
import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.FieldAnnotationArgument;
import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.examples.FieldLevelAnnotation;
import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.examples.FieldLevelAnnoted;

public class FieldAnnotationArgumentTest extends TestBase {
	
private FieldAnnotationArgument fieldAnnotationArgument;
	
	@Before
	public void before(){
		fieldAnnotationArgument = new FieldAnnotationArgument(FieldLevelAnnotation.class);
	}
	
	@Test
	public void whenFieldIsAnnotedWithFieldLevelAnnotation(){
		assertTrue(fieldAnnotationArgument.validate(FieldLevelAnnoted.class));
	}
	
	@Test
	public void whenFieldIsNotAnnotedWithFieldLevelAnnotation(){
		assertFalse(fieldAnnotationArgument.validate(String.class));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void whenNullIsPassedToTheConstructor(){
		new FieldAnnotationArgument(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void whenNullIsPassedToValidate(){
		assertTrue(fieldAnnotationArgument.validate(null));
	}

}
