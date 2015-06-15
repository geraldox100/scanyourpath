package br.com.geraldoferraz.scanyourpath;

import br.com.geraldoferraz.scanyourpath.resolver.ClassPathResolver;
import br.com.geraldoferraz.scanyourpath.resolver.JavaClassPathResolver;
import br.com.geraldoferraz.scanyourpath.searches.filters.SearchAll;
import br.com.geraldoferraz.scanyourpath.searches.filters.SearchByArguments;
import br.com.geraldoferraz.scanyourpath.searches.filters.SearchType;
import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.Argument;
import br.com.geraldoferraz.scanyourpath.searches.loaders.ClassPathLoader;

/**
 * 
 * @author Geraldo Ferraz
 *
 */
public class Scanner {

	private SearchType searchType;

	private ClassPathResolver resolver = ClassPathResolver.newInstance(new JavaClassPathResolver());

	public Location allClasses(Argument argument) {
		searchType = new SearchByArguments(argument);
		return new Location(searchType,resolver);
	}

	public Location allClasses() {
		searchType = new SearchAll();
		return new Location(searchType,resolver);
	}

	public void limitSearchingPathTo(ClassPathLoader classPathSearchType) {
		resolver.limitSearchingPathTo(classPathSearchType);
	}

}