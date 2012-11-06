package br.com.geraldoferraz.scanner.searches.filters.arguments;



import static br.com.geraldoferraz.scanner.searches.filters.arguments.SearchArguments.annotedOnClassWith;
import static br.com.geraldoferraz.scanner.searches.filters.arguments.SearchArguments.annotedOnFieldWith;
import static br.com.geraldoferraz.scanner.searches.filters.arguments.SearchArguments.annotedOnMethodWith;
import static br.com.geraldoferraz.scanner.searches.filters.arguments.SearchArguments.not;
import static br.com.geraldoferraz.scanner.searches.filters.arguments.SearchArguments.thatExtends;
import static br.com.geraldoferraz.scanner.searches.filters.arguments.SearchArguments.thatImplements;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import br.com.geraldoferraz.scanner.TestBase;
import br.com.geraldoferraz.scanner.searches.filters.arguments.examples.ClassLevelAnnotation;
import br.com.geraldoferraz.scanner.searches.filters.arguments.examples.FieldLevelAnnotation;
import br.com.geraldoferraz.scanner.searches.filters.arguments.examples.MethodLevelAnnotation;

public class SearchArgumentsTest extends TestBase {

	@Test
	public void nullableAssertions() {
		assertThat(annotedOnClassWith(ClassLevelAnnotation.class), notNullValue());
		assertThat(annotedOnMethodWith(MethodLevelAnnotation.class), notNullValue());
		assertThat(annotedOnFieldWith(FieldLevelAnnotation.class), notNullValue());
		assertThat(thatExtends(Object.class), notNullValue());
		assertThat(thatImplements(Argument.class), notNullValue());
		assertThat(not(annotedOnFieldWith(FieldLevelAnnotation.class)), notNullValue());
	}

	@Test
	public void combinableArgumentAssertions() {
		assertThat(annotedOnClassWith(ClassLevelAnnotation.class), instanceOf(CombinableArgument.class));
		assertThat(annotedOnMethodWith(MethodLevelAnnotation.class), instanceOf(CombinableArgument.class));
		assertThat(annotedOnFieldWith(FieldLevelAnnotation.class), instanceOf(CombinableArgument.class));
		assertThat(not(annotedOnFieldWith(FieldLevelAnnotation.class)), instanceOf(CombinableArgument.class));
		assertThat(thatExtends(Object.class), instanceOf(CombinableArgument.class));
		assertThat(thatImplements(Argument.class), instanceOf(CombinableArgument.class));
	}

	@Test
	public void ArgumentAssertions() {
		assertThat(annotedOnClassWith(ClassLevelAnnotation.class), instanceOf(Argument.class));
		assertThat(annotedOnMethodWith(MethodLevelAnnotation.class), instanceOf(Argument.class));
		assertThat(annotedOnFieldWith(FieldLevelAnnotation.class), instanceOf(Argument.class));
		assertThat(thatExtends(Object.class), instanceOf(Argument.class));
		assertThat(thatImplements(Argument.class), instanceOf(Argument.class));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void passingAnNullArgumentToAnnotedOnClassWith() {
		annotedOnClassWith(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void passingAnNullArgumentToAnnotedOnMethodWith() {
		annotedOnMethodWith(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void passingAnNullArgumentToAnnotedOnFieldWith() {
		annotedOnFieldWith(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void passingAnNullArgumentToExtend() {
		thatExtends(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void passingAnNullArgumentToImplement() {
		thatImplements(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void passingAnNullArgumentToNot() {
		not(null);
	}
	

}