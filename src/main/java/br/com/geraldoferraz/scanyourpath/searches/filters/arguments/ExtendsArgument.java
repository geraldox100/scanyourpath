package br.com.geraldoferraz.scanyourpath.searches.filters.arguments;

import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.argumentValidation;

public class ExtendsArgument implements Argument {

	private final Class<?> clazz;

	public ExtendsArgument(Class<?> clazz) {
		argumentValidation(clazz);
		this.clazz = clazz;
	}

	@Override
	public boolean validate(Class<?> clazz) {
		argumentValidation(clazz);
		if (!clazz.isAnnotation())
			return clazz.getSuperclass().equals(this.clazz);
		else
			return false;
	}

}
