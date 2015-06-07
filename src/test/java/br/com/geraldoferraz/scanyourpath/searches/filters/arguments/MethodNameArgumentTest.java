package br.com.geraldoferraz.scanyourpath.searches.filters.arguments;

import static br.com.geraldoferraz.scanyourpath.util.strings.StringComparator.STARTS_WITH;
import static br.com.geraldoferraz.scanyourpath.util.strings.StringComparator.ENDS_WITH;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.examples.ClassWithMethods;
import br.com.geraldoferraz.scanyourpath.util.EmptyStringException;

public class MethodNameArgumentTest {
	
	private MethodNameArgument methodNameArgument;
	
	@Before
	public void before(){
		methodNameArgument = new MethodNameArgument("equals");
	}
	
	@Test(expected=EmptyStringException.class)
	public void passingAnNullArgumentToHasMethod() {
		new MethodNameArgument(null);
	}
	
	@Test(expected=EmptyStringException.class)
	public void passingAnEmptyStringToHasMethod() {
		new MethodNameArgument("");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void whenPassingNullArgument(){
		methodNameArgument.validate(null);
	}
	
	@Test
	public void whenAskingForMethodThatDontExist(){
		assertThat(new MethodNameArgument("equals").validate(ClassWithMethods.class), equalTo(false));
	}
	
	@Test
	public void whenAskingForMethodWithNoArgumentsThatExists(){
		assertThat(new MethodNameArgument("methodWithNoArgument").validate(ClassWithMethods.class), equalTo(true));
	}
	
	@Test
	public void whenAskingForMethodWithOneArgumentThatExists(){
		assertThat(new MethodNameArgument("methodWithMoreThenOneArgument").validate(ClassWithMethods.class), equalTo(true));
	}
	
	@Test
	public void whenAskingForMethodStartingWithNameThatDontExist(){
		assertThat(new MethodNameArgument("equals",STARTS_WITH).validate(ClassWithMethods.class), equalTo(false));
	}
	
	@Test
	public void whenAskingForMethodStartingWithNameThatEndsWith(){
		assertThat(new MethodNameArgument("WithNoArgument",STARTS_WITH).validate(ClassWithMethods.class), equalTo(false));
	}
	
	@Test
	public void whenAskingForMethodStartingWithNameWithNoArgumentsThatExists(){
		assertThat(new MethodNameArgument("methodWithNo",STARTS_WITH).validate(ClassWithMethods.class), equalTo(true));
	}
	
	@Test
	public void whenAskingForMethodStartingWithNameWithOneArgumentThatExists(){
		assertThat(new MethodNameArgument("methodWithMore",STARTS_WITH).validate(ClassWithMethods.class), equalTo(true));
	}
	
	@Test
	public void whenAskingForMethodEndingWithNameThatDontExist(){
		assertThat(new MethodNameArgument("equals",ENDS_WITH).validate(ClassWithMethods.class), equalTo(false));
	}
	
	@Test
	public void whenAskingForMethodEndingWithNameThatEndsWith(){
		assertThat(new MethodNameArgument("methodWithNo",ENDS_WITH).validate(ClassWithMethods.class), equalTo(false));
	}
	
	@Test
	public void whenAskingForMethodEndingWithNameWithNoArgumentsThatExists(){
		assertThat(new MethodNameArgument("WithNoArgument",ENDS_WITH).validate(ClassWithMethods.class), equalTo(true));
	}
	
	@Test
	public void whenAskingForMethodEndingWithNameWithOneArgumentThatExists(){
		assertThat(new MethodNameArgument("WithNoArgument",ENDS_WITH).validate(ClassWithMethods.class), equalTo(true));
	}

}
