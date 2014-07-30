package br.com.geraldoferraz.scanyourpath.searches.filters;

import java.util.Set;

public interface SearchType {

	public Set<Class<?>> search(Set<Class<?>> classes);

}
