package br.com.geraldoferraz.scanyourpath.searches.filters.arguments.operators;


/**
 * This class represents a LogicalOperator And
 * 
 * @author Geraldo ferraz
 *
 */
public class And implements LogicalOperator {

	public boolean validate(boolean firstArgument, boolean secondArgument) {
		return firstArgument && secondArgument;
	}


}
