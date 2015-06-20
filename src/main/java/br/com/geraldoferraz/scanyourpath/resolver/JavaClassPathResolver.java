package br.com.geraldoferraz.scanyourpath.resolver;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * This class loads all classes from the classloader
 * @author Geraldo Ferraz
 *
 */
public class JavaClassPathResolver {
	
	private URL[] URLs;

	public JavaClassPathResolver() {
		ClassLoader contextClassLoader = this.getClass().getClassLoader();
		URLs = ((URLClassLoader) contextClassLoader).getURLs();
	}

	/**
	 * @return This method returns all URL from classes in the classloader 
	 */
	public URL[] getClassPathUrl(){
		return URLs; 
	}
}	