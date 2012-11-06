package br.com.geraldoferraz.scanner.searches.filters.arguments.operators;

public class Or implements  LogicalOperator{

	@Override
	public boolean validate(boolean validationResult, boolean nextArgumentValidationResult) {
		return validationResult || nextArgumentValidationResult;
	}

}
