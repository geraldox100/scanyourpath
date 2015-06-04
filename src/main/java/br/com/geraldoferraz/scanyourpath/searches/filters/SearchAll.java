package br.com.geraldoferraz.scanyourpath.searches.filters;

import java.util.Set;

import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.*;

public final class SearchAll implements SearchType {

	public Set<Class<?>> search(Set<Class<?>> classes) {
		argumentValidation(classes);
		return classes;
	}

}
