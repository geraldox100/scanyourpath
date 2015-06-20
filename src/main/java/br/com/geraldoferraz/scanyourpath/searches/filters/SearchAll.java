package br.com.geraldoferraz.scanyourpath.searches.filters;

import java.util.Set;

import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.*;

/**
 * This class is used to tell the scanner that search should be on all classes
 * @author Geraldo Ferraz
 *
 */
public final class SearchAll implements SearchType {

	/**
	 * This method defines that the search should be on all classes
	 * @param classes the full set of classes to be filtered
	 * @throws IllegalArgumentException if classes are null
	 */
	public Set<Class<?>> search(Set<Class<?>> classes) {
		argumentValidation(classes);
		return classes;
	}

}
