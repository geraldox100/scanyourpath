package br.com.geraldoferraz.scanyourpath.searches.filters.arguments.operators;

/**
 * This enum represents the logical operators Or and And
 * @author geraldo
 *
 */
public enum LogicalOperators {
	
	AND(new And()),OR(new Or());
	
	private final LogicalOperator operator;

	private LogicalOperators(LogicalOperator operator) {
		this.operator = operator;
	}
	
	public boolean validate(boolean validationResult, boolean nextArgumentValidationResult){
		return operator.validate(validationResult, nextArgumentValidationResult);
	}
	

}
