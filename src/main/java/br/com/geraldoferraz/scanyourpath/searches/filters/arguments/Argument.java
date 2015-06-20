package br.com.geraldoferraz.scanyourpath.searches.filters.arguments;

/**
 * Defines a argumet
 * @author Geraldo Ferraz
 *
 */
public interface Argument{
	
	boolean validate(Class<?> clazz);
	
}
