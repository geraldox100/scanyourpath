package br.com.geraldoferraz.scanner.resolver;

import java.net.URL;
import java.net.URLClassLoader;

public class JavaClassPathResolver {
	
	private URL[] URLs;

	public JavaClassPathResolver() {
		URLs = ((URLClassLoader) Thread.currentThread().getContextClassLoader()).getURLs();
	}
	
	public URL[] getClassPathUrl(){
		return URLs; 
	}
}	