package br.com.geraldoferraz.scanner.util;

import org.junit.Test;

import br.com.geraldoferraz.scanner.TestBase;
import br.com.geraldoferraz.scanner.util.EmptyStringException;
import br.com.geraldoferraz.scanner.util.ValidationUtil;

public class ValidationUtilTest extends TestBase {


	@Test(expected=IllegalArgumentException.class)
	public void whenUseArgumentValidationPassingNullArgument(){
		ValidationUtil.argumentValidation(null);
	}
	
	@Test(expected=IllegalStateException.class)
	public void whenUseStateValidationPassingNullArgument(){
		ValidationUtil.stateValidation(null);
	}
	
	@Test(expected=EmptyStringException.class)
	public void whenUseEmptyStaringValidationPassingNullArgument(){
		ValidationUtil.emptyStringValidation(null);
	}
	
	@Test(expected=EmptyStringException.class)
	public void whenUseEmptyStaringValidationPassingEmptyStringArgument(){
		ValidationUtil.emptyStringValidation("");
	}
	
	
	@Test(expected=EmptyStringException.class)
	public void whenUseEmptyStaringValidationPassingWhiteSpaceStringArgument(){
		ValidationUtil.emptyStringValidation("   ");
	}

}
