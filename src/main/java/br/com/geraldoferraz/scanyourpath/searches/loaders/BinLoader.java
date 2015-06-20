package br.com.geraldoferraz.scanyourpath.searches.loaders;

import static br.com.geraldoferraz.scanyourpath.util.ClassUtil.getFullQualifiedName;
import static br.com.geraldoferraz.scanyourpath.util.ClassUtil.isClass;
import static br.com.geraldoferraz.scanyourpath.util.ClassUtil.removeDirectory;
import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.argumentsValidation;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.argumentValidation;

/**
 * BinLoader represents a ClassPathLoader that loads classes from your own classes
 * @author Geraldo Ferraz
 *
 */
class BinLoader implements ClassPathLoader {

	public Set<String> resolveClassName(List<File> classPath) {
		argumentValidation(classPath);
		Set<String> classes = new HashSet<String>();
		for (File file : classPath) {
			if (file.isDirectory()) {
				classes.addAll(getClassesInDirectory(file, file.getPath()));
			}
		}
		return classes;
	}

	final Set<String> getClassesInDirectory(File location, String folderToRemove) {
		argumentsValidation(location, folderToRemove);
		Set<String> classes = new HashSet<String>();
		File[] files = location.listFiles();

		for (File file : files) {
			if (file.isDirectory()) {
				classes.addAll(getClassesInDirectory(file, folderToRemove));
			} else if (isClass(file)) {
				classes.add(removeDirectory(getFullQualifiedName(file), folderToRemove));
			}
		}

		return classes;
	}

	

}
