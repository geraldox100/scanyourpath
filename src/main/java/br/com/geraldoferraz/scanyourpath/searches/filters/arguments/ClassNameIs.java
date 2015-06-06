package br.com.geraldoferraz.scanyourpath.searches.filters.arguments;

import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.argumentValidation;
import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.emptyStringValidation;

public class ClassNameIs implements Argument {

	private String className;

	ClassNameIs(String className) {
		emptyStringValidation(className);
		this.className = className;
	}

	public boolean validate(Class<?> clazz) {
		argumentValidation(clazz);
		return clazz.getSimpleName().equals(className);
	}

}
