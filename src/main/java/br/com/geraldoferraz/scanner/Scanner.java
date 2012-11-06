package br.com.geraldoferraz.scanner;

import java.util.Set;

import br.com.geraldoferraz.scanner.resolver.ClassPathResolver;
import br.com.geraldoferraz.scanner.resolver.JavaClassPathResolver;
import br.com.geraldoferraz.scanner.searches.filters.SearchAll;
import br.com.geraldoferraz.scanner.searches.filters.SearchByArguments;
import br.com.geraldoferraz.scanner.searches.filters.SearchType;
import br.com.geraldoferraz.scanner.searches.filters.arguments.Argument;
import br.com.geraldoferraz.scanner.searches.loaders.ClassPathLoader;

/**
 * 
 * @author Geraldo Ferraz
 * 
 *
 */
public class Scanner {

	private SearchType searchType;

	private ClassPathResolver resolver = ClassPathResolver.getInstance(new JavaClassPathResolver());

	public Scanner allClasses(Argument argument) {
		searchType = new SearchByArguments(argument);
		return this;
	}

	public Scanner allClasses() {
		searchType = new SearchAll();
		return this;
	}

	public Set<Class<?>> exactlyIn(String packageName) {
		return searchType.search(resolver.getClassesExactlyIn(packageName));
	}

	public Set<Class<?>> startingIn(String packageName) {
		return searchType.search(resolver.getClassesStartingIn(packageName));
	}

	public Scanner limitSearchingPathTo(ClassPathLoader classPathSearchType) {
		resolver.limitSearchingPathTo(classPathSearchType);
		return this;
	}

}