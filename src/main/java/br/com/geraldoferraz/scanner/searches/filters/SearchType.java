package br.com.geraldoferraz.scanner.searches.filters;

import java.util.Set;

public interface SearchType {

	public Set<Class<?>> search(Set<Class<?>> classes);

}
