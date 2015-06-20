package br.com.geraldoferraz.scanyourpath.searches.filters.arguments.operators;

/**
 * This class represents a LogicalOperator Or
 * 
 * @author Geraldo ferraz
 *
 */
public class Or implements LogicalOperator {

	public boolean validate(boolean validationResult, boolean nextArgumentValidationResult) {
		return validationResult || nextArgumentValidationResult;
	}

}
