package br.com.geraldoferraz.scanyourpath.searches.filters.arguments;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.examples.ClassWithMethods;
import br.com.geraldoferraz.scanyourpath.util.EmptyStringException;

public class ClassNameIsTest {

	private ClassNameIs classNameIs;

	@Before
	public void before() {
		classNameIs = new ClassNameIs("ClassNameIs");
	}

	@Test(expected = EmptyStringException.class)
	public void passingAnNullArgumentToClassNameIs() {
		new ClassNameIs(null);
	}

	@Test(expected = EmptyStringException.class)
	public void passingAnEmptyStringToClassNameIs() {
		new ClassNameIs("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenPassingNullArgument() {
		classNameIs.validate(null);
	}

	@Test
	public void whenAskingIfClassNamesIsWhenItsNot() {
		assertThat(new ClassNameIs("JustAClassName").validate(ClassWithMethods.class), equalTo(false));
	}

	@Test
	public void whenAskingIfClassNameIsWhenItIs() {
		assertThat(new ClassNameIs("ClassWithMethods").validate(ClassWithMethods.class), equalTo(true));
	}

}
