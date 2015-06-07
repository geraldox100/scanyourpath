package br.com.geraldoferraz.scanyourpath.searches.filters.arguments;

import static br.com.geraldoferraz.scanyourpath.util.enums.StringComparator.ENDS_WITH;
import static br.com.geraldoferraz.scanyourpath.util.enums.StringComparator.STARTS_WITH;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.examples.ClassWithFields;
import br.com.geraldoferraz.scanyourpath.util.EmptyStringException;

public class FieldNameArgumentTest {

	private FieldNameArgument fieldNameArgument;

	@Before
	public void before() {
		fieldNameArgument = new FieldNameArgument("stringField");
	}

	@Test(expected = EmptyStringException.class)
	public void passingAnNullArgumentToHasField() {
		new FieldNameArgument(null);
	}

	@Test(expected = EmptyStringException.class)
	public void passingAnEmptyStringToHasField() {
		new FieldNameArgument("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenPassingNullArgument() {
		fieldNameArgument.validate(null);
	}

	@Test
	public void whenAskingForFieldThatDontExist() {
		assertThat(new FieldNameArgument("anyField").validate(ClassWithFields.class), equalTo(false));
	}

	@Test
	public void whenAskingForFieldThatExists() {
		assertThat(new FieldNameArgument("stringField").validate(ClassWithFields.class), equalTo(true));
	}

	@Test
	public void whenAskingForFieldStartingWithNameThatDontExist() {
		assertThat(new FieldNameArgument("anyField", STARTS_WITH).validate(ClassWithFields.class), equalTo(false));
	}

	@Test
	public void whenAskingForFieldStartingWithNameThatEndsWith() {
		assertThat(new FieldNameArgument("Field", STARTS_WITH).validate(ClassWithFields.class), equalTo(false));
	}

	@Test
	public void whenAskingForFieldStartingWithNameThatExists() {
		assertThat(new FieldNameArgument("string", STARTS_WITH).validate(ClassWithFields.class), equalTo(true));
	}

	@Test
	public void whenAskingForFieldEndingWithNameThatDontExist() {
		assertThat(new FieldNameArgument("anyField", ENDS_WITH).validate(ClassWithFields.class), equalTo(false));
	}

	@Test
	public void whenAskingForFieldEndingWithNameThatEndsWith() {
		assertThat(new FieldNameArgument("string", ENDS_WITH).validate(ClassWithFields.class), equalTo(false));
	}

	@Test
	public void whenAskingForFieldEndingWithNameThatExists() {
		assertThat(new FieldNameArgument("Field", ENDS_WITH).validate(ClassWithFields.class), equalTo(true));
	}

}
