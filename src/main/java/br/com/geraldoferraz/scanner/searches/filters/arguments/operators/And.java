package br.com.geraldoferraz.scanner.searches.filters.arguments.operators;


public class And implements LogicalOperator {

	@Override
	public boolean validate(boolean firstArgument, boolean secondArgument) {
		return firstArgument && secondArgument;
	}


}
