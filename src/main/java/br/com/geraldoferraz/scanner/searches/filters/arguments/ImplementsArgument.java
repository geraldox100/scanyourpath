package br.com.geraldoferraz.scanner.searches.filters.arguments;

import static br.com.geraldoferraz.scanner.util.ValidationUtil.argumentValidation;

public class ImplementsArgument implements Argument {

	private final Class<?> interfacee;

	public ImplementsArgument(Class<?> interfacee) {
		argumentValidation(interfacee);
		if (!interfacee.isInterface()) {
			throw new IllegalArgumentException(interfacee.getSimpleName() + ": is not an interface type");
		}
		this.interfacee = interfacee;
	}

	@Override
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
