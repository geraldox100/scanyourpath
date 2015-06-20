package br.com.geraldoferraz.scanyourpath.searches.filters.arguments;

import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.argumentValidation;
import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.operators.LogicalOperators;

/**
 * This class allows you to combine arguments using 'and' and 'or'.
 * @author geraldo
 *
 */
public final class CombinableArgument implements Argument {

	private LogicalOperators operator;
	private Argument nextArgument;
	private Argument firstArgument;

	public CombinableArgument(Argument argument) {
		this.firstArgument = argument;
	}

	public final boolean validate(Class<?> clazz) {
		argumentValidation(clazz);
		if (nextArgument != null) {
			return operator.validate(firstArgument.validate(clazz), nextArgument.validate(clazz));
		} else {
			return firstArgument.validate(clazz);
		}
	}

	/**
	 * Combines the first argument to the next argument using the Or operator
	 * @param nextArgument the argument to be combined
	 * @return the same instance so you can continue to combine
	 */
	public CombinableArgument or(Argument nextArgument) {
		combine(nextArgument, LogicalOperators.OR);
		return this;
	}

	/**
	 * Combines the first argument to the next argument using the And operator
	 * @param nextArgument the argument to be combined
	 * @return the same instance so you can continue to combine
	 */
	public CombinableArgument and(Argument andArgument) {
		combine(andArgument, LogicalOperators.AND);
		return this;
	}

	private void combine(Argument argument, LogicalOperators operator) {
		argumentValidation(argument);
		this.nextArgument = argument;
		this.operator = operator;
	}

	@Override
	public String toString() {
		return (firstArgument != null ? firstArgument : "") + " " + (operator != null ? operator : "") + " "
				+ (nextArgument != null ? nextArgument : "");
	}
	
}
