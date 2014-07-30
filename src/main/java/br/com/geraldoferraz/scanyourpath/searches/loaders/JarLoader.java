package br.com.geraldoferraz.scanyourpath.searches.loaders;

import static br.com.geraldoferraz.scanyourpath.util.ClassUtil.getFullQualifiedName;
import static br.com.geraldoferraz.scanyourpath.util.ClassUtil.isClass;
import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.argumentValidation;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;

import br.com.geraldoferraz.scanyourpath.searches.loaders.stream.MyJarInputStream;

class JarLoader implements ClassPathLoader {
	
	@Override
	public Set<String> resolveClassName(List<File> classPath) {
		argumentValidation(classPath);
		Set<String> classes = new HashSet<String>();
		for (File file : classPath) {
			if (file.isFile()) {
				classes.addAll(resolveJar(file));
			}
		}
		return classes;
	}

	Set<String> resolveJar(File jarFile) {
		argumentValidation(jarFile);
		Set<String> classes = new HashSet<String>();
		
		
		Iterator<JarEntry> iterator = new MyJarInputStream().forFile(jarFile).iterator();
		while (iterator.hasNext()) {
			JarEntry jarEntry = iterator.next();
			if (isClass(jarEntry)) {
				classes.add(getFullQualifiedName(jarEntry));
			}
		}

		return classes;
	}
}

