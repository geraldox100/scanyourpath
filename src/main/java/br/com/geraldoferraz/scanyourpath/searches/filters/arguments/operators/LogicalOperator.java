package br.com.geraldoferraz.scanyourpath.searches.filters.arguments.operators;


public interface LogicalOperator {

	boolean validate(boolean validationResult, boolean nextArgumentValidationResult);

	

}
