package br.com.geraldoferraz.scanyourpath.searches.filters.arguments;

import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.argumentValidation;
import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.emptyStringValidation;
import br.com.geraldoferraz.scanyourpath.util.enums.StringComparator;

public class ClassNameArgument implements Argument {

	private String className;
	private final StringComparator stringComparator;

	ClassNameArgument(String className) {
		this(className, StringComparator.EXACTLY);
	}

	ClassNameArgument(String className, StringComparator stringComparator) {
		emptyStringValidation(className);
		argumentValidation(stringComparator);
		this.stringComparator = stringComparator;
		this.className = className;
	}

	public boolean validate(Class<?> clazz) {
		argumentValidation(clazz);
		return stringComparator.compare(clazz.getSimpleName(), className);
	}

}
