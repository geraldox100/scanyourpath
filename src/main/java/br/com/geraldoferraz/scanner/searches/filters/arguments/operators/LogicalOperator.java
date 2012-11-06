package br.com.geraldoferraz.scanner.searches.filters.arguments.operators;


public interface LogicalOperator {

	boolean validate(boolean validationResult, boolean nextArgumentValidationResult);

	

}
