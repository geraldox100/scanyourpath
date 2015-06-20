package br.com.geraldoferraz.scanyourpath.searches.filters;


import java.util.HashSet;
import java.util.Set;

import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.Argument;
import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.*;

/**
 * This class tells is used by the scanner to filter classes by the given argument
 * @author Geraldo Ferraz
 *
 */
public final class SearchByArguments implements SearchType {

	private final Argument argument;

	/**
	 * @param argument argument to match the classes
	 */
	public SearchByArguments(Argument argument) {
		this.argument = argument;
	}

	/**
	 * This methods filter the given glasses by the arguments
	 * @param classes classes to be filtered
	 * @throws IllegalArgumentException if the set of classes is null
	 */
	public Set<Class<?>> search(Set<Class<?>> classes) {
		argumentValidation(classes);
		Set<Class<?>> retorno = new HashSet<Class<?>>();
		for (Class<?> clazz : classes) {
			if (argument.validate(clazz)) {
				retorno.add(clazz);
			}
		}
		return retorno;
	}

}

