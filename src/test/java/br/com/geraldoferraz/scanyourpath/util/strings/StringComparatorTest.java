package br.com.geraldoferraz.scanyourpath.util.strings;

import static br.com.geraldoferraz.scanyourpath.util.strings.StringComparator.ENDS_WITH;
import static br.com.geraldoferraz.scanyourpath.util.strings.StringComparator.EXACTLY;
import static br.com.geraldoferraz.scanyourpath.util.strings.StringComparator.STARTS_WITH;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import br.com.geraldoferraz.scanyourpath.util.EmptyStringException;

public class StringComparatorTest {
	
	@Test(expected=EmptyStringException.class)
	public void passingNullArgumentToExactlyOnFirstArgument(){
		EXACTLY.compare(null,"EQUAL_STRING");
	}
	
	@Test(expected=EmptyStringException.class)
	public void passingNullArgumentToExactlyOnSecondArgument(){
		EXACTLY.compare("EQUAL_STRING",null);
	}
	
	@Test
	public void whenAskingToCompareExactlyForEqualsStrings(){
		assertThat(EXACTLY.compare("EQUAL_STRING","EQUAL_STRING"), equalTo(true));
	}
	
	@Test
	public void whenAskingToCompareExactlyForNonEqualsStrings(){
		assertThat(EXACTLY.compare("EQUAL_STRING","NON_EQUALSTRING"), equalTo(false));
	}
	
	@Test(expected=EmptyStringException.class)
	public void passingNullArgumentToEndsWIthOnFirstArgument(){
		ENDS_WITH.compare(null,"EQUAL_STRING");
	}
	
	@Test(expected=EmptyStringException.class)
	public void passingNullArgumentToEndsWIthOnSecondArgument(){
		ENDS_WITH.compare("EQUAL_STRING",null);
	}
	
	@Test
	public void whenAskingToCompareEndsWIthForEqualsStrings(){
		assertThat(ENDS_WITH.compare("EQUAL_STRING","EQUAL_STRING"), equalTo(true));
	}
	
	@Test
	public void whenAskingToCompareEndsWIthForNonEqualsStrings(){
		assertThat(ENDS_WITH.compare("EQUAL_STRING","NON_EQUALSTRING"), equalTo(false));
	}
	
	@Test
	public void whenAskingToCompareEndsWIthForStringThatEndWith(){
		assertThat(ENDS_WITH.compare("EQUAL_STRING","STRING"), equalTo(true));
	}
	
	@Test
	public void whenAskingToCompareEndsWIthForStringThatStartsWith(){
		assertThat(ENDS_WITH.compare("EQUAL_STRING","EQUAL_"), equalTo(false));
	}
	
	@Test(expected=EmptyStringException.class)
	public void passingNullArgumentToEStartsWithOnFirstArgument(){
		STARTS_WITH.compare(null,"EQUAL_STRING");
	}
	
	@Test(expected=EmptyStringException.class)
	public void passingNullArgumentToStartsWithOnSecondArgument(){
		STARTS_WITH.compare("EQUAL_STRING",null);
	}
	
	@Test
	public void whenAskingToCompareStartsWithForEqualsStrings(){
		assertThat(STARTS_WITH.compare("EQUAL_STRING","EQUAL_STRING"), equalTo(true));
	}
	
	@Test
	public void whenAskingToCompareStartsWithForNonEqualsStrings(){
		assertThat(STARTS_WITH.compare("EQUAL_STRING","NON_EQUALSTRING"), equalTo(false));
	}
	
	@Test
	public void whenAskingToCompareStartsWithForStringThatEndWith(){
		assertThat(STARTS_WITH.compare("EQUAL_STRING","EQUAL_"), equalTo(true));
	}
	
	@Test
	public void whenAskingToCompareStartsWithForStringThatStartsWith(){
		assertThat(STARTS_WITH.compare("EQUAL_STRING","_STRING"), equalTo(false));
	}

}
