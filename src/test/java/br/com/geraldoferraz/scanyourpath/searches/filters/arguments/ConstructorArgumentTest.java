package br.com.geraldoferraz.scanyourpath.searches.filters.arguments;

import static br.com.geraldoferraz.scanyourpath.util.enums.ParameterComparator.AT_LEAST;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.examples.ClassWithConstructors;

public class ConstructorArgumentTest {
	
	private ConstructorArgument constructorArgument;

	@Before
	public void before() {
		constructorArgument = new ConstructorArgument(String.class);
	}

	@Test(expected = IllegalArgumentException.class)
	public void passingAnNullArgumentToConstructorArgumentWithOneArgument01() {
		Class<?>[] clazzes = null;
		new ConstructorArgument(clazzes);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void passingAnNullArgumentToConstructorArgumentWithOneArgument02() {
		Class<?> clazz = null;
		new ConstructorArgument(clazz);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void passingAnNullArgumentToConstructorArgumentWithTwoArgumentSecond() {
		new ConstructorArgument(String.class,null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void whenPassingNullArgument() {
		constructorArgument.validate(null);
	}
	
	@Test
	public void whenAskingIfClassHasConstructorButDoesnt(){
		assertThat(new ConstructorArgument(Integer.class).validate(ClassWithConstructors.class), equalTo(false));
	}
	
	@Test
	public void whenAskingIfClassHasConstructorWithMultipleParametersButDoesnt(){
		assertThat(new ConstructorArgument(Integer.class,String.class).validate(ClassWithConstructors.class), equalTo(false));
	}
	
	@Test
	public void whenAskingIfClassHasConstructorAndItDoes(){
		assertThat(new ConstructorArgument(String.class).validate(ClassWithConstructors.class), equalTo(true));
	}
	
	@Test
	public void whenAskingIfClassHasConstructorWithMultipleParametersAndItDoes(){
		assertThat(new ConstructorArgument(String.class,Integer.class).validate(ClassWithConstructors.class), equalTo(true));
	}
	
	@Test
	public void whenAskingIfClassHasConstructorWithAtLeastButDoesnt(){
		assertThat(new ConstructorArgument(List.class,AT_LEAST).validate(ClassWithConstructors.class), equalTo(false));
	}
	
	@Test
	public void whenAskingIfClassHasConstructorWithAtLeastAndItDoes(){
		assertThat(new ConstructorArgument(Integer.class,AT_LEAST).validate(ClassWithConstructors.class), equalTo(true));
	}
	
	@Test
	public void whenAskingIfClassHasConstructorWithAtLeastAndItDoesForMultipleParameters(){
		assertThat(new ConstructorArgument(new Class<?>[]{Integer.class,String.class},AT_LEAST).validate(ClassWithConstructors.class), equalTo(true));
	}
	
	@Test
	public void whenAskingIfClassHasConstructorWithAtLeastAndItDoesButVarArgs(){
		assertThat(new ConstructorArgument(Short.class,AT_LEAST).validate(ClassWithConstructors.class), equalTo(true));
	}

}
