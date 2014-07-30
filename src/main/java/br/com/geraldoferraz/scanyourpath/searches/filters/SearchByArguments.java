package br.com.geraldoferraz.scanyourpath.searches.filters;


import java.util.HashSet;
import java.util.Set;

import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.Argument;
import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.*;

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

