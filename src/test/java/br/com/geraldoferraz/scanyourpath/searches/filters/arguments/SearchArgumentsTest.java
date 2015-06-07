package br.com.geraldoferraz.scanyourpath.searches.filters.arguments;

import static br.com.geraldoferraz.scanyourpath.searches.filters.arguments.SearchArguments.annotedWith;
import static br.com.geraldoferraz.scanyourpath.searches.filters.arguments.SearchArguments.doesnt;
import static br.com.geraldoferraz.scanyourpath.searches.filters.arguments.SearchArguments.dont;
import static br.com.geraldoferraz.scanyourpath.searches.filters.arguments.SearchArguments.havingConstructorAnnotedWith;
import static br.com.geraldoferraz.scanyourpath.searches.filters.arguments.SearchArguments.havingConstructorReceivingAtLeast;
import static br.com.geraldoferraz.scanyourpath.searches.filters.arguments.SearchArguments.havingConstructorReceivingExactly;
import static br.com.geraldoferraz.scanyourpath.searches.filters.arguments.SearchArguments.havingFieldAnnotedWith;
import static br.com.geraldoferraz.scanyourpath.searches.filters.arguments.SearchArguments.havingFieldNameEndingWith;
import static br.com.geraldoferraz.scanyourpath.searches.filters.arguments.SearchArguments.havingFieldNameStartingWith;
import static br.com.geraldoferraz.scanyourpath.searches.filters.arguments.SearchArguments.havingFieldWithName;
import static br.com.geraldoferraz.scanyourpath.searches.filters.arguments.SearchArguments.havingMethodAnnotedWith;
import static br.com.geraldoferraz.scanyourpath.searches.filters.arguments.SearchArguments.havingMethodNameEndingWith;
import static br.com.geraldoferraz.scanyourpath.searches.filters.arguments.SearchArguments.havingMethodNameStartingWith;
import static br.com.geraldoferraz.scanyourpath.searches.filters.arguments.SearchArguments.havingMethodWithName;
import static br.com.geraldoferraz.scanyourpath.searches.filters.arguments.SearchArguments.namedWith;
import static br.com.geraldoferraz.scanyourpath.searches.filters.arguments.SearchArguments.not;
import static br.com.geraldoferraz.scanyourpath.searches.filters.arguments.SearchArguments.thatExtends;
import static br.com.geraldoferraz.scanyourpath.searches.filters.arguments.SearchArguments.thatImplements;
import static br.com.geraldoferraz.scanyourpath.searches.filters.arguments.SearchArguments.thatNameEndsWith;
import static br.com.geraldoferraz.scanyourpath.searches.filters.arguments.SearchArguments.thatNameStartsWith;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import br.com.geraldoferraz.scanyourpath.TestBase;
import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.examples.ClassLevelAnnotation;
import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.examples.ConstructorLevelAnnotation;
import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.examples.FieldLevelAnnotation;
import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.examples.MethodLevelAnnotation;
import br.com.geraldoferraz.scanyourpath.util.EmptyStringException;

public class SearchArgumentsTest extends TestBase {

	@Test
	public void nullableAssertions() {
		assertThat(annotedWith(ClassLevelAnnotation.class), notNullValue());
		assertThat(havingMethodAnnotedWith(MethodLevelAnnotation.class), notNullValue());
		assertThat(havingFieldAnnotedWith(FieldLevelAnnotation.class), notNullValue());
		assertThat(havingConstructorAnnotedWith(ConstructorLevelAnnotation.class), notNullValue());
		assertThat(thatExtends(Object.class), notNullValue());
		assertThat(thatImplements(Argument.class), notNullValue());
		assertThat(not(havingFieldAnnotedWith(FieldLevelAnnotation.class)), notNullValue());
		assertThat(dont(havingFieldAnnotedWith(FieldLevelAnnotation.class)), notNullValue());
		assertThat(doesnt(havingFieldAnnotedWith(FieldLevelAnnotation.class)), notNullValue());
		assertThat(havingMethodWithName("ANY"), notNullValue());
		assertThat(havingMethodNameStartingWith("ANY"), notNullValue());
		assertThat(havingMethodNameEndingWith("ANY"), notNullValue());
		assertThat(havingFieldWithName("ANY"), notNullValue());
		assertThat(havingFieldNameStartingWith("ANY"), notNullValue());
		assertThat(havingFieldNameEndingWith("ANY"), notNullValue());
		assertThat(namedWith("ANY"), notNullValue());
		assertThat(thatNameStartsWith("ANY"), notNullValue());
		assertThat(thatNameEndsWith("ANY"), notNullValue());
		assertThat(havingConstructorReceivingAtLeast(String.class), notNullValue());
		assertThat(havingConstructorReceivingExactly(String.class), notNullValue());
	}

	@Test
	public void combinableArgumentAssertions() {
		assertThat(annotedWith(ClassLevelAnnotation.class), instanceOf(CombinableArgument.class));
		assertThat(havingMethodAnnotedWith(MethodLevelAnnotation.class), instanceOf(CombinableArgument.class));
		assertThat(havingFieldAnnotedWith(FieldLevelAnnotation.class), instanceOf(CombinableArgument.class));
		assertThat(havingConstructorAnnotedWith(ConstructorLevelAnnotation.class), instanceOf(CombinableArgument.class));
		assertThat(not(havingFieldAnnotedWith(FieldLevelAnnotation.class)), instanceOf(CombinableArgument.class));
		assertThat(dont(havingFieldAnnotedWith(FieldLevelAnnotation.class)), instanceOf(CombinableArgument.class));
		assertThat(doesnt(havingFieldAnnotedWith(FieldLevelAnnotation.class)), instanceOf(CombinableArgument.class));
		assertThat(thatExtends(Object.class), instanceOf(CombinableArgument.class));
		assertThat(thatImplements(Argument.class), instanceOf(CombinableArgument.class));
		assertThat(havingMethodWithName("ANY"), instanceOf(CombinableArgument.class));
		assertThat(havingMethodNameStartingWith("ANY"), instanceOf(CombinableArgument.class));
		assertThat(havingMethodNameEndingWith("ANY"), instanceOf(CombinableArgument.class));
		assertThat(havingFieldWithName("ANY"), instanceOf(CombinableArgument.class));
		assertThat(havingFieldNameStartingWith("ANY"), instanceOf(CombinableArgument.class));
		assertThat(havingFieldNameEndingWith("ANY"), instanceOf(CombinableArgument.class));
		assertThat(namedWith("ANY"), instanceOf(CombinableArgument.class));
		assertThat(thatNameStartsWith("ANY"), instanceOf(CombinableArgument.class));
		assertThat(thatNameEndsWith("ANY"), instanceOf(CombinableArgument.class));
		assertThat(havingConstructorReceivingAtLeast(String.class), instanceOf(CombinableArgument.class));
		assertThat(havingConstructorReceivingExactly(String.class), instanceOf(CombinableArgument.class));
	}

	@Test
	public void argumentAssertions() {
		assertThat(annotedWith(ClassLevelAnnotation.class), instanceOf(Argument.class));
		assertThat(havingMethodAnnotedWith(MethodLevelAnnotation.class), instanceOf(Argument.class));
		assertThat(havingFieldAnnotedWith(FieldLevelAnnotation.class), instanceOf(Argument.class));
		assertThat(havingConstructorAnnotedWith(ConstructorLevelAnnotation.class), instanceOf(Argument.class));
		assertThat(thatExtends(Object.class), instanceOf(Argument.class));
		assertThat(thatImplements(Argument.class), instanceOf(Argument.class));
		assertThat(havingMethodWithName("ANY"), instanceOf(Argument.class));
		assertThat(havingMethodNameStartingWith("ANY"), instanceOf(Argument.class));
		assertThat(havingMethodNameEndingWith("ANY"), instanceOf(Argument.class));
		assertThat(namedWith("ANY"), instanceOf(Argument.class));
		assertThat(havingConstructorReceivingAtLeast(String.class), instanceOf(Argument.class));
		assertThat(havingConstructorReceivingExactly(String.class), instanceOf(Argument.class));
	}

	@Test(expected = IllegalArgumentException.class)
	public void passingAnNullArgumentToAnnotedWith() {
		annotedWith(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void passingAnNullArgumentToHavingMethodAnnotedWith() {
		havingMethodAnnotedWith(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void passingAnNullArgumentToHavingFieldAnnotedWith() {
		havingFieldAnnotedWith(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void passingAnNullArgumentToHavingConstructorAnnotedWith() {
		havingConstructorAnnotedWith(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void passingAnNullArgumentToThatExtends() {
		thatExtends(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void passingAnNullArgumentToThatImplements() {
		thatImplements(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void passingAnNullArgumentToNot() {
		not(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void passingAnNullArgumentToDont() {
		dont(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void passingAnNullArgumentToDoesent() {
		doesnt(null);
	}

	@Test(expected = EmptyStringException.class)
	public void passingAnNullArgumentToHavingMethodWithName() {
		havingMethodWithName(null);
	}

	@Test(expected = EmptyStringException.class)
	public void passingAnNullArgumentToHavingMethodNameStartingWith() {
		havingMethodNameStartingWith(null);
	}

	@Test(expected = EmptyStringException.class)
	public void passingAnNullArgumentToHavingMethodNameEndingWith() {
		havingMethodNameEndingWith(null);
	}

	@Test(expected = EmptyStringException.class)
	public void passingAnNullArgumentToHavingFieldWithName() {
		havingFieldWithName(null);
	}

	@Test(expected = EmptyStringException.class)
	public void passingAnNullArgumentToHavingFieldNameStartingWith() {
		havingFieldNameStartingWith(null);
	}

	@Test(expected = EmptyStringException.class)
	public void passingAnNullArgumentToHavingFieldNameEndingWith() {
		havingFieldNameEndingWith(null);
	}

	@Test(expected = EmptyStringException.class)
	public void passingAnEmptyStringToHavingMethodWithName() {
		havingMethodWithName("");
	}

	@Test(expected = EmptyStringException.class)
	public void passingAnEmptyStringToHavingMethodNameStartingWith() {
		havingMethodNameStartingWith("");
	}

	@Test(expected = EmptyStringException.class)
	public void passingAnEmptyStringToHavingMethodNameEndingWith() {
		havingMethodNameEndingWith("");
	}

	@Test(expected = EmptyStringException.class)
	public void passingAnEmptyStringToHavingFieldWithName() {
		havingFieldWithName("");
	}

	@Test(expected = EmptyStringException.class)
	public void passingAnEmptyStringToHavingMethodNameFieldWith() {
		havingFieldNameStartingWith("");
	}

	@Test(expected = EmptyStringException.class)
	public void passingAnEmptyStringToHavingFieldNameEndingWith() {
		havingFieldNameEndingWith("");
	}

	@Test(expected = EmptyStringException.class)
	public void passingAnNullArgumentToNamedWith() {
		namedWith(null);
	}

	@Test(expected = EmptyStringException.class)
	public void passingAnEmptyStringToNamedWith() {
		namedWith("");
	}

	@Test(expected = EmptyStringException.class)
	public void passingAnNullArgumentToThatNameStartsWith() {
		thatNameStartsWith(null);
	}

	@Test(expected = EmptyStringException.class)
	public void passingAnEmptyStringToThatNameStartsWith() {
		thatNameStartsWith("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void passingAnNullArgumentToHavingConstructorReceivingAtLeast() {
		Class<?>[] classes = null;
		havingConstructorReceivingAtLeast(classes);
	}

	@Test(expected = IllegalArgumentException.class)
	public void passingAnNullArgumentToHavingConstructorReceivingExactly() {
		Class<?>[] classes = null;
		havingConstructorReceivingExactly(classes);
	}
}
