package br.com.geraldoferraz.scanyourpath.searches.filters.arguments.operators;

/**
 * LogicalOperator interface
 * @author Geraldo Ferraz
 *
 */
public interface LogicalOperator {

	boolean validate(boolean validationResult, boolean nextArgumentValidationResult);

	

}
