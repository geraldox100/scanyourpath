package br.com.geraldoferraz.scanyourpath.util.enums;

import static br.com.geraldoferraz.scanyourpath.util.enums.ParameterComparator.AT_LEAST;
import static br.com.geraldoferraz.scanyourpath.util.enums.ParameterComparator.EXACTLY;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;

import org.junit.Test;

import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.examples.ClassWithConstructors;

public class ParameterComparatorTest {

	@Test(expected = IllegalArgumentException.class)
	public void passingNullArgumentToExactlyOnFirstArgument() {
		EXACTLY.compare(null, String.class);
	}

	@Test(expected = IllegalArgumentException.class)
	public void passingNullArgumentToExactlyOnSecondArgument() {
		Class<?>[] classes = null;
		EXACTLY.compare(new Parameter[] {}, classes);
	}

	@Test(expected = IllegalArgumentException.class)
	public void passingNullArgumentToAtLeastOnFirstArgument() {
		AT_LEAST.compare(null, String.class);
	}

	@Test(expected = IllegalArgumentException.class)
	public void passingNullArgumentToAtLeastOnSecondArgument() {
		Class<?>[] classes = null;
		AT_LEAST.compare(new Parameter[] {}, classes);
	}

	@Test
	public void whenAskingToCompareExactlyAndThereIsTheParameter() throws Exception {
		Constructor<ClassWithConstructors> constructor = ClassWithConstructors.class.getConstructor(String.class);
		Parameter[] parameters = constructor.getParameters();
		assertThat(EXACTLY.compare(parameters, String.class), equalTo(true));
	}
	
	@Test
	public void whenAskingToCompareExactlyAndThereIsTheParameterWithMultipleParameters() throws Exception {
		Constructor<ClassWithConstructors> constructor = ClassWithConstructors.class.getConstructor(String.class,Integer.class);
		Parameter[] parameters = constructor.getParameters();
		assertThat(EXACTLY.compare(parameters, String.class, Integer.class), equalTo(true));
	}

	@Test
	public void whenAskingToCompareExactlyAndThereIsntTheParameter() throws Exception {
		Constructor<ClassWithConstructors> constructor = ClassWithConstructors.class.getConstructor(String.class);
		Parameter[] parameters = constructor.getParameters();
		assertThat(EXACTLY.compare(parameters, Integer.class), equalTo(false));
	}
	
	@Test
	public void whenAskingToCompareExactlyAndThereIsntTheParameterForMultipleParameters() throws Exception {
		Constructor<ClassWithConstructors> constructor = ClassWithConstructors.class.getConstructor(String.class,Integer.class);
		Parameter[] parameters = constructor.getParameters();
		assertThat(EXACTLY.compare(parameters, Integer.class,String.class), equalTo(false));
	}

	@Test
	public void whenAskingToCompareExactlyAndTheElementHasNoParameter() throws Exception {
		Constructor<ClassWithConstructors> constructor = ClassWithConstructors.class.getConstructor();
		Parameter[] parameters = constructor.getParameters();
		assertThat(EXACTLY.compare(parameters, Integer.class), equalTo(false));
	}

	@Test
	public void whenAskingToCompareAtLeastAndThereIsTheOneParameter() throws Exception {
		Constructor<ClassWithConstructors> constructor = ClassWithConstructors.class.getConstructor(String.class);
		Parameter[] parameters = constructor.getParameters();
		assertThat(AT_LEAST.compare(parameters, String.class), equalTo(true));
	}
	
	@Test
	public void whenAskingToCompareAtLeastAndThereIsTheParameterWithMultipleParameters() throws Exception {
		Constructor<ClassWithConstructors> constructor = ClassWithConstructors.class.getConstructor(String.class,Integer.class,Boolean.class);
		Parameter[] parameters = constructor.getParameters();
		assertThat(AT_LEAST.compare(parameters, String.class,Boolean.class), equalTo(true));
	}

	@Test
	public void whenAskingToCompareAtLeastAndThereIsTheParameter() throws Exception {
		Constructor<ClassWithConstructors> constructor = ClassWithConstructors.class.getConstructor(String.class, Integer.class);
		Parameter[] parameters = constructor.getParameters();
		assertThat(AT_LEAST.compare(parameters, Integer.class), equalTo(true));
	}

	@Test
	public void whenAskingToCompareAtLeastAndThereIsAVarArgsOfTheParameter() throws Exception {
		Constructor<ClassWithConstructors> constructor = ClassWithConstructors.class.getConstructor(Short[].class);
		Parameter[] parameters = constructor.getParameters();
		assertThat(AT_LEAST.compare(parameters, Short.class), equalTo(true));
	}

	@Test
	public void whenAskingToCompareAtLeastAndThereIsntTheParameter() throws Exception {
		Constructor<ClassWithConstructors> constructor = ClassWithConstructors.class.getConstructor(String.class);
		Parameter[] parameters = constructor.getParameters();
		assertThat(AT_LEAST.compare(parameters, Integer.class), equalTo(false));
	}

	@Test
	public void whenAskingToCompareAtLeastAndTheElementHasNoParameter() throws Exception {
		Constructor<ClassWithConstructors> constructor = ClassWithConstructors.class.getConstructor();
		Parameter[] parameters = constructor.getParameters();
		assertThat(AT_LEAST.compare(parameters, Integer.class), equalTo(false));
	}

}
