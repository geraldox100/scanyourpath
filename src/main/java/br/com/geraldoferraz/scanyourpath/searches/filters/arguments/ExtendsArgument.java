package br.com.geraldoferraz.scanyourpath.searches.filters.arguments;

import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.argumentValidation;

/**
 * This class checks if a class extends another
 * @author Geraldo Ferraz
 *
 */
public class ExtendsArgument implements Argument {

	private final Class<?> clazz;

	public ExtendsArgument(Class<?> clazz) {
		argumentValidation(clazz);
		this.clazz = clazz;
	}

	public boolean validate(Class<?> clazz) {
		argumentValidation(clazz);
		if (!clazz.isAnnotation()){
			return clazz.getSuperclass().equals(this.clazz);
		}
		else{
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "Class extends: "+clazz.getSimpleName();
	}

}
