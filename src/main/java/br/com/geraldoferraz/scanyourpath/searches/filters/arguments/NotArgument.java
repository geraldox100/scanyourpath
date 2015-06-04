package br.com.geraldoferraz.scanyourpath.searches.filters.arguments;

import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.argumentValidation;

public class NotArgument implements Argument {

	private final Argument argument;

	public NotArgument(Argument argument) {
		argumentValidation(argument);
		this.argument = argument;
	}

	public boolean validate(Class<?> clazz) {
		return !argument.validate(clazz);
	}

}
