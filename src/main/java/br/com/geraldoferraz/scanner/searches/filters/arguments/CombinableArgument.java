package br.com.geraldoferraz.scanner.searches.filters.arguments;

import static br.com.geraldoferraz.scanner.util.ValidationUtil.argumentValidation;
import br.com.geraldoferraz.scanner.searches.filters.arguments.operators.LogicalOperators;

public final class CombinableArgument implements Argument {

	private LogicalOperators operator;
	private Argument nextArgument;
	private Argument firstArgument;

	public CombinableArgument(Argument argument) {
		this.firstArgument = argument;
	}

	@Override
	public final boolean validate(Class<?> clazz) {
		argumentValidation(clazz);
		if (nextArgument != null) {
			return operator.validate(firstArgument.validate(clazz), nextArgument.validate(clazz));
		} else {
			return firstArgument.validate(clazz);
		}
	}

	public CombinableArgument or(Argument orArgument) {
		combine(orArgument, LogicalOperators.OR);
		return this;
	}

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
