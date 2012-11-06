package br.com.geraldoferraz.scanner.searches.filters.arguments;

import static br.com.geraldoferraz.scanner.util.ValidationUtil.argumentValidation;

public class NotArgument implements Argument {

	private final Argument argument;

	public NotArgument(Argument argument) {
		argumentValidation(argument);
		this.argument = argument;
	}

	@Override
	public boolean validate(Class<?> clazz) {
		return !argument.validate(clazz);
	}

}
