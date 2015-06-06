package br.com.geraldoferraz.scanyourpath.searches.filters.arguments;

import static br.com.geraldoferraz.scanyourpath.searches.filters.arguments.HasMethodArgument.Where.LEFT;
import static br.com.geraldoferraz.scanyourpath.searches.filters.arguments.HasMethodArgument.Where.RIGHT;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.examples.ClassWithMethods;
import br.com.geraldoferraz.scanyourpath.util.EmptyStringException;

public class HasMethodArgumentTest {
	
	private HasMethodArgument hasMethodArgument;
	
	@Before
	public void before(){
		hasMethodArgument = new HasMethodArgument("equals");
	}
	
	@Test(expected=EmptyStringException.class)
	public void passingAnNullArgumentToHasMethod() {
		new HasMethodArgument(null);
	}
	
	@Test(expected=EmptyStringException.class)
	public void passingAnEmptyStringToHasMethod() {
		new HasMethodArgument("");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void whenPassingNullArgument(){
		hasMethodArgument.validate(null);
	}
	
	@Test
	public void whenAskingForMethodThatDontExist(){
		assertThat(new HasMethodArgument("equals").validate(ClassWithMethods.class), equalTo(false));
	}
	
	@Test
	public void whenAskingForMethodWithNoArgumentsThatExists(){
		assertThat(new HasMethodArgument("methodWithNoArgument").validate(ClassWithMethods.class), equalTo(true));
	}
	
	@Test
	public void whenAskingForMethodWithOneArgumentThatExists(){
		assertThat(new HasMethodArgument("methodWithMoreThenOneArgument").validate(ClassWithMethods.class), equalTo(true));
	}
	
	@Test
	public void whenAskingForMethodStartingWithNameThatDontExist(){
		assertThat(new HasMethodArgument("equals",LEFT).validate(ClassWithMethods.class), equalTo(false));
	}
	
	@Test
	public void whenAskingForMethodStartingWithNameThatEndsWith(){
		assertThat(new HasMethodArgument("WithNoArgument",LEFT).validate(ClassWithMethods.class), equalTo(false));
	}
	
	@Test
	public void whenAskingForMethodStartingWithNameWithNoArgumentsThatExists(){
		assertThat(new HasMethodArgument("methodWithNo",LEFT).validate(ClassWithMethods.class), equalTo(true));
	}
	
	@Test
	public void whenAskingForMethodStartingWithNameWithOneArgumentThatExists(){
		assertThat(new HasMethodArgument("methodWithMore",LEFT).validate(ClassWithMethods.class), equalTo(true));
	}
	
	@Test
	public void whenAskingForMethodEndingWithNameThatDontExist(){
		assertThat(new HasMethodArgument("equals",RIGHT).validate(ClassWithMethods.class), equalTo(false));
	}
	
	@Test
	public void whenAskingForMethodEndingWithNameThatEndsWith(){
		assertThat(new HasMethodArgument("methodWithNo",RIGHT).validate(ClassWithMethods.class), equalTo(false));
	}
	
	@Test
	public void whenAskingForMethodEndingWithNameWithNoArgumentsThatExists(){
		assertThat(new HasMethodArgument("WithNoArgument",RIGHT).validate(ClassWithMethods.class), equalTo(true));
	}
	
	@Test
	public void whenAskingForMethodEndingWithNameWithOneArgumentThatExists(){
		assertThat(new HasMethodArgument("WithNoArgument",RIGHT).validate(ClassWithMethods.class), equalTo(true));
	}

}
