package br.com.geraldoferraz.scanyourpath.searches.filters;

import java.util.Set;

/**
 * Defines a search type
 * @author Geraldo Ferraz
 *
 */
public interface SearchType {

	/**
	 * defines the searching method
	 * @param classes
	 * @return
	 */
	public Set<Class<?>> search(Set<Class<?>> classes);

}
