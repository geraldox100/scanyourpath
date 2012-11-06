package br.com.geraldoferraz.scanner.searches.loaders;

import java.io.File;
import java.util.List;
import java.util.Set;

public interface ClassPathLoader {
	
	public Set<String> resolveClassName(List<File> classPath);
	

}
