package br.com.geraldoferraz.scanyourpath;

import java.util.Set;

import br.com.geraldoferraz.scanyourpath.resolver.ClassPathResolver;
import br.com.geraldoferraz.scanyourpath.searches.filters.SearchType;
/**
 * This class allows you to define wich package and how to search. 
 * @author Geraldo Ferraz
 */
public class Location {
	private SearchType searchType;
	private ClassPathResolver resolver;

	public Location(SearchType searchType, ClassPathResolver resolver) {
		this.searchType = searchType;
		this.resolver = resolver;
	}

	/**
	 * Tells the scanner that the search should be exactly and only on the specified paramter.
	 * @param packageName The package name to be search
	 * @return A set of classes that matches the given package
	 */
	public Set<Class<?>> exactlyIn(String packageName) {
		return searchType.search(resolver.getClassesExactlyIn(packageName));
	}

	/**
	 * Tells the scanner that the search should start on the given package and sub-packages.
	 * @param packageName
	 * @return A set of classes that matches the given package and sub-packages
	 */
	public Set<Class<?>> startingIn(String packageName) {
		return searchType.search(resolver.getClassesStartingIn(packageName));
	}

	/**
	 * Tells the scanner to search on any package
	 * @return The set of classes found
	 */
	public Set<Class<?>> anyWhere() {
		return searchType.search(resolver.getClassesAnyWhere());
	}
}
