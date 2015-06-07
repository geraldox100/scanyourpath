package br.com.geraldoferraz.scanyourpath.searches.filters.arguments;

import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.argumentValidation;
import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.emptyStringValidation;

import java.lang.reflect.Method;

import br.com.geraldoferraz.scanyourpath.util.strings.StringComparator;

public class MethodNameArgument implements Argument {

	private final String methodName;
	private final StringComparator stringComparator;

	MethodNameArgument(String methodName) {
		this(methodName, StringComparator.EXACTLY);
	}

	MethodNameArgument(String methodName, StringComparator stringComparator) {
		emptyStringValidation(methodName);
		this.stringComparator = stringComparator;
		this.methodName = methodName;
	}

	public boolean validate(Class<?> clazz) {
		argumentValidation(clazz);

		try {
			Method[] declaredMethods = clazz.getDeclaredMethods();
			for (Method method : declaredMethods) {
				if (stringComparator.compare(method.getName(), methodName)) {
					return true;
				}
			}
		} catch (Exception e) {

		}
		return false;

	}

}
