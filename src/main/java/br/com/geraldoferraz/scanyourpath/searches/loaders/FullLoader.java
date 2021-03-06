package br.com.geraldoferraz.scanyourpath.searches.loaders;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.*;

/**
 * This class is a ClassPathLoader that load classes from both Jars and classpath
 * @author Geraldo Ferraz
 *
 */
class FullLoader implements ClassPathLoader {

	private JarLoader jarLoader;
	private BinLoader binLoader;

	public FullLoader(JarLoader jarLoader, BinLoader binLoader) {
		argumentValidation(jarLoader);
		argumentValidation(binLoader);
		this.jarLoader = jarLoader;
		this.binLoader = binLoader;
	}

	/**
	 * Loads all classes
	 */
	public final Set<String> resolveClassName(List<File> classPath) {
		argumentValidation(classPath);
		Set<String> classes = new HashSet<String>();
		for (File file : classPath) {
			if (file.isFile()) {
				classes.addAll(jarLoader.resolveJar(file));
			} else {
				classes.addAll(binLoader.getClassesInDirectory(file, file.getPath()));
			}
		}
		return classes;
	}

}
