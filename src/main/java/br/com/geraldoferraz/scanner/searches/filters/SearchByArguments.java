package br.com.geraldoferraz.scanner.searches.filters;


import java.util.HashSet;
import java.util.Set;

import br.com.geraldoferraz.scanner.searches.filters.arguments.Argument;
import static br.com.geraldoferraz.scanner.util.ValidationUtil.*;

public final class SearchByArguments implements SearchType {

	private final Argument argument;

	public SearchByArguments(Argument argument) {
		this.argument = argument;
	}

	@Override
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

