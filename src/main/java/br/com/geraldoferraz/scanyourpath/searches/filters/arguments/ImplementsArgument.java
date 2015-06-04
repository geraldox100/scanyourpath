package br.com.geraldoferraz.scanyourpath.searches.filters.arguments;

import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.argumentValidation;

public class ImplementsArgument implements Argument {

	private final Class<?> interfacee;

	public ImplementsArgument(Class<?> interfacee) {
		argumentValidation(interfacee);
		if (!interfacee.isInterface()) {
			throw new IllegalArgumentException(interfacee.getSimpleName() + ": is not an interface type");
		}
		this.interfacee = interfacee;
	}

	public boolean validate(Class<?> clazz) {
		argumentValidation(clazz);
		Class<?>[] interfaces = clazz.getInterfaces();
		for (Class<?> interfacee : interfaces) {
			if (interfacee.equals(this.interfacee)) {
				return true;
			}
		}
		return false;
	}

}
