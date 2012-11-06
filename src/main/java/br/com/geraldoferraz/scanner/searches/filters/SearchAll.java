package br.com.geraldoferraz.scanner.searches.filters;

import java.util.Set;

import static br.com.geraldoferraz.scanner.util.ValidationUtil.*;

public final class SearchAll implements SearchType {

	@Override
	public Set<Class<?>> search(Set<Class<?>> classes) {
		argumentValidation(classes);
		return classes;
	}

}
