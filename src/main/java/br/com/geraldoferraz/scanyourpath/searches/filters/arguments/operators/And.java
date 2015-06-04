package br.com.geraldoferraz.scanyourpath.searches.filters.arguments.operators;


public class And implements LogicalOperator {

	public boolean validate(boolean firstArgument, boolean secondArgument) {
		return firstArgument && secondArgument;
	}


}
