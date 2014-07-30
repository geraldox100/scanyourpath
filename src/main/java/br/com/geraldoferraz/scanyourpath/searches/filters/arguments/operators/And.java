package br.com.geraldoferraz.scanyourpath.searches.filters.arguments.operators;


public class And implements LogicalOperator {

	@Override
	public boolean validate(boolean firstArgument, boolean secondArgument) {
		return firstArgument && secondArgument;
	}


}
