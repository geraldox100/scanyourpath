package br.com.geraldoferraz.scanyourpath;

import java.util.Set;

import br.com.geraldoferraz.scanyourpath.resolver.ClassPathResolver;
import br.com.geraldoferraz.scanyourpath.searches.filters.SearchType;

public class Location {
	private SearchType searchType;
	private ClassPathResolver resolver;

	public Location(SearchType searchType, ClassPathResolver resolver) {
		this.searchType = searchType;
		this.resolver = resolver;
	}

	public Set<Class<?>> exactlyIn(String packageName) {
		return searchType.search(resolver.getClassesExactlyIn(packageName));
	}

	public Set<Class<?>> startingIn(String packageName) {
		return searchType.search(resolver.getClassesStartingIn(packageName));
	}

	public Set<Class<?>> anyWhere() {
		return searchType.search(resolver.getClassesAnyWhere());
	}
}
