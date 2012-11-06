package br.com.geraldoferraz.scanner.searches.filters.arguments.operators;

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
