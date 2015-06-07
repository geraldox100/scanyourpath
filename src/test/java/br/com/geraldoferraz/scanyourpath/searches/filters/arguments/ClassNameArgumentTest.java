package br.com.geraldoferraz.scanyourpath.searches.filters.arguments;

import static br.com.geraldoferraz.scanyourpath.util.enums.StringComparator.ENDS_WITH;
import static br.com.geraldoferraz.scanyourpath.util.enums.StringComparator.STARTS_WITH;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.examples.ClassWithMethods;
import br.com.geraldoferraz.scanyourpath.util.EmptyStringException;

public class ClassNameArgumentTest {

	private ClassNameArgument classNameArgument;

	@Before
	public void before() {
		classNameArgument = new ClassNameArgument("ClassNameArgument");
	}

	@Test(expected = EmptyStringException.class)
	public void passingAnNullArgumentToClassNameArgumentWithOneArgument() {
		new ClassNameArgument(null);
	}
	
	@Test(expected = EmptyStringException.class)
	public void passingAnNullArgumentToClassNameArgumentWithTwoArgumentsOneFirstArgument() {
		new ClassNameArgument(null,ENDS_WITH);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void passingAnNullArgumentToClassNameArgumentWithTwoArgumentsOneSecondArgument() {
		new ClassNameArgument("JustAClassName",null);
	}

	@Test(expected = EmptyStringException.class)
	public void passingAnEmptyStringToClassNameArgumentOFirstConstructor() {
		new ClassNameArgument("");
	}
	
	@Test(expected = EmptyStringException.class)
	public void passingAnEmptyStringToClassNameArgumentOSecondConstructor() {
		new ClassNameArgument("",ENDS_WITH);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void whenPassingNullArgument() {
		classNameArgument.validate(null);
	}

	@Test
	public void whenAskingIfClassNamesArgumentWhenItsNot() {
		assertThat(new ClassNameArgument("JustAClassName").validate(ClassWithMethods.class), equalTo(false));
	}

	@Test
	public void whenAskingIfClassNameArgumentWhenItIs() {
		assertThat(new ClassNameArgument("ClassWithMethods").validate(ClassWithMethods.class), equalTo(true));
	}
	
	@Test
	public void whenAskingIfClassNameArgumentStartsWithAndNamesAreEqual(){
		assertThat(new ClassNameArgument("ClassWithMethods",STARTS_WITH).validate(ClassWithMethods.class), equalTo(true));
	}
	
	@Test
	public void whenAskingIfClassNameArgumentStartsWithAndNamesAreNotEqual(){
		assertThat(new ClassNameArgument("NOT_EQUAL_CLASS_NAME",STARTS_WITH).validate(ClassWithMethods.class), equalTo(false));
	}
	
	@Test
	public void whenAskingIfClassNameArgumentStartsWithAndNameDoesStartWith(){
		assertThat(new ClassNameArgument("ClassWith",STARTS_WITH).validate(ClassWithMethods.class), equalTo(true));
	}
	
	@Test
	public void whenAskingIfClassNameArgumentStartsWithAndNameDoesentStartWith(){
		assertThat(new ClassNameArgument("WithMethods",STARTS_WITH).validate(ClassWithMethods.class), equalTo(false));
	}
	
	@Test
	public void whenAskingIfClassNameArgumentEndsWithAndNamesAreEqual(){
		assertThat(new ClassNameArgument("ClassWithMethods",ENDS_WITH).validate(ClassWithMethods.class), equalTo(true));
	}
	
	@Test
	public void whenAskingIfClassNameArgumentEndsWithAndNamesAreNotEqual(){
		assertThat(new ClassNameArgument("NOT_EQUAL_CLASS_NAME",ENDS_WITH).validate(ClassWithMethods.class), equalTo(false));
	}
	
	@Test
	public void whenAskingIfClassNameArgumentEndsWithAndNameDoesStartWith(){
		assertThat(new ClassNameArgument("WithMethods",ENDS_WITH).validate(ClassWithMethods.class), equalTo(true));
	}
	
	@Test
	public void whenAskingIfClassNameArgumentEndsWithAndNameDoesentStartWith(){
		assertThat(new ClassNameArgument("ClassWith",ENDS_WITH).validate(ClassWithMethods.class), equalTo(false));
	}
}
