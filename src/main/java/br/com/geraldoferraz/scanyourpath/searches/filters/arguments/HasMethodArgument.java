package br.com.geraldoferraz.scanyourpath.searches.filters.arguments;

import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.argumentValidation;
import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.emptyStringValidation;

import java.lang.reflect.Method;

;

public class HasMethodArgument implements Argument {

	private final String methodName;

	public HasMethodArgument(String methodName) {
		emptyStringValidation(methodName);
		this.methodName = methodName;
	}

	public boolean validate(Class<?> clazz) {
		argumentValidation(clazz);

		try {
			Method[] declaredMethods = clazz.getDeclaredMethods();
			for (Method method : declaredMethods) {
				if (method.getName().equals(methodName)) {
					return true;
				}
			}
		} catch (Exception e) {

		}
		return false;

	}

}
