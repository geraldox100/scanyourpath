package br.com.geraldoferraz.scanyourpath.resolver;

import java.net.URL;
import java.net.URLClassLoader;

public class JavaClassPathResolver {
	
	private URL[] URLs;

	public JavaClassPathResolver() {
		ClassLoader contextClassLoader = this.getClass().getClassLoader();
		URLs = ((URLClassLoader) contextClassLoader).getURLs();
	}
	
	public URL[] getClassPathUrl(){
		return URLs; 
	}
}	