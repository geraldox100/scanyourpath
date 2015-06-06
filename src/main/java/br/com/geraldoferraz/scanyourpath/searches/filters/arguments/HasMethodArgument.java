package br.com.geraldoferraz.scanyourpath.searches.filters.arguments;

import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.argumentValidation;
import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.emptyStringValidation;

import java.lang.reflect.Method;

public class HasMethodArgument implements Argument {

	private final String methodName;
	private final Where where;

	protected enum Where {
		RIGHT {
			@Override
			public boolean valida(Method method, String methodName) {
				return method.getName().endsWith(methodName);
			}
		},
		LEFT {
			@Override
			public boolean valida(Method method, String methodName) {
				return method.getName().startsWith(methodName);
			}
		},
		EXACTLY {
			@Override
			public boolean valida(Method method, String methodName) {
				return method.getName().equals(methodName);
			}
		};

		public abstract boolean valida(Method method, String methodName);
	}

	public HasMethodArgument(String methodName) {
		this(methodName, Where.EXACTLY);
	}

	public HasMethodArgument(String methodName, Where where) {
		emptyStringValidation(methodName);
		this.where = where;
		this.methodName = methodName;
	}

	public boolean validate(Class<?> clazz) {
		argumentValidation(clazz);

		try {
			Method[] declaredMethods = clazz.getDeclaredMethods();
			for (Method method : declaredMethods) {
				if (where.valida(method, methodName)) {
					return true;
				}
			}
		} catch (Exception e) {

		}
		return false;

	}

}
