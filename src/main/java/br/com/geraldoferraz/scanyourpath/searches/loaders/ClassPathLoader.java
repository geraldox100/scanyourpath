package br.com.geraldoferraz.scanyourpath.searches.loaders;

import java.io.File;
import java.util.List;
import java.util.Set;

/**
 * ClassPathLoader interface defins a classPathLoader
 * @author Geraldo Ferraz
 *
 */
public interface ClassPathLoader {
	
	public Set<String> resolveClassName(List<File> classPath);
	

}
