package br.com.geraldoferraz.scanyourpath.searches.filters.arguments.operators;

public class Or implements  LogicalOperator{

	public boolean validate(boolean validationResult, boolean nextArgumentValidationResult) {
		return validationResult || nextArgumentValidationResult;
	}

}
