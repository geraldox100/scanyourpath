package br.com.geraldoferraz.scanyourpath.util;

import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.argumentValidation;
import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.argumentsValidation;
import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.emptyArrayValidation;
import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.emptyStringValidation;
import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.stateValidation;

import org.junit.Test;

import br.com.geraldoferraz.scanyourpath.TestBase;

public class ValidationUtilTest extends TestBase {


	@Test(expected=IllegalArgumentException.class)
	public void whenUseArgumentValidationPassingNullArgument(){
		Object o = null;
		argumentValidation(o);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void whenUseArgumentValidationPassingNullArgumentVarAgrs(){
		Object o1 = null;
		Object o2 = null;
		argumentsValidation(o1,o2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void whenUseArgumentValidationPassingNullArgumentVarAgrs02(){
		Object o1 = new Object();
		Object o2 = null;
		argumentsValidation(o1,o2);
	}
	
	@Test(expected=IllegalStateException.class)
	public void whenUseStateValidationPassingNullArgument(){
		stateValidation(null);
	}
	
	@Test(expected=EmptyStringException.class)
	public void whenUseEmptyStaringValidationPassingNullArgument(){
		emptyStringValidation(null);
	}
	
	@Test(expected=EmptyStringException.class)
	public void whenUseEmptyStaringValidationPassingEmptyStringArgument(){
		emptyStringValidation("");
	}
	
	
	@Test(expected=EmptyStringException.class)
	public void whenUseEmptyStaringValidationPassingWhiteSpaceStringArgument(){
		emptyStringValidation("   ");
	}
	
	@Test(expected=EmptyArrayException.class)
	public void whenUseEmptyArrayValidationPassingEmptyArrayArgument(){
		emptyArrayValidation(new Object[]{});
	}
	
	@Test(expected=EmptyArrayException.class)
	public void whenUseEmptyArrayValidationPassingNullArrayArgument(){
		emptyArrayValidation(null);
	}

}
