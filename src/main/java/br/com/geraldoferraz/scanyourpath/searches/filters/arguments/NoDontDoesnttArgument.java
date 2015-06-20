package br.com.geraldoferraz.scanyourpath.searches.filters.arguments;

import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.argumentValidation;

/**
 * This class represent a argument that denies another argument
 * @author Geraldo Ferraz
 *
 */
public class NoDontDoesnttArgument implements Argument {

	private final Argument argument;

	public NoDontDoesnttArgument(Argument argument) {
		argumentValidation(argument);
		this.argument = argument;
	}

	/**
	 * Denies the other argument
	 */
	public boolean validate(Class<?> clazz) {
		return !argument.validate(clazz);
	}
	
	@Override
	public String toString() {
		return "not: "+argument;
	}

}
