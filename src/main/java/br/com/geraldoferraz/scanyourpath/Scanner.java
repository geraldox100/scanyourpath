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
 * The Scanner class is the starting point for classpath scanning.
 * Example:
 * 		Scanner scan = new Scanner();
 * 		scan.allClasses().anyWhere();
 * 
 * For a more refined search try using a search argument and a limiting a path to search:
 * {@code Set<Class<?>> classes = scan.allClasses(annotedOnClassWith(Entity.class)).exactlyIn("br.com.beans");}
 *
 * @author Geraldo Ferraz
 */
public class Scanner {

	private SearchType searchType;

	private ClassPathResolver resolver = ClassPathResolver.getInstance(new JavaClassPathResolver());

	/**
	 * This method tells the scanner that all classes must match the given argument.
	 * @param argument The argument that all classes must match
	 * @return A object that allows you to tell the scanner where to search.
	 */
	public Location allClasses(Argument argument) {
		searchType = new SearchByArguments(argument);
		return new Location(searchType,resolver);
	}

	/**
	 * This method tells the scanner to search for all classes
	 * @return A object that allows you to tell the scanner where to search. 
	 */
	public Location allClasses() {
		searchType = new SearchAll();
		return new Location(searchType,resolver);
	}

	/**
	 * This method tells the scanner the classpath type (Jar, Folder, Full).
	 * @param classPathSearchType The classpath type to be used.
	 * See {@link br.com.geraldoferraz.scanyourpath.searches.loaders.ClassPathLoaderTypes} 
	 */
	public void limitSearchingPathTo(ClassPathLoader classPathSearchType) {
		resolver.limitSearchingPathTo(classPathSearchType);
	}

}