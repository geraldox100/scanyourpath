package br.com.geraldoferraz.scanner.resolver;

import static br.com.geraldoferraz.scanner.util.ClassUtil.extractPackageNameFromFullQualifiedName;
import static br.com.geraldoferraz.scanner.util.ValidationUtil.emptyStringValidation;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.geraldoferraz.scanner.searches.loaders.ClassPathLoader;
import br.com.geraldoferraz.scanner.searches.loaders.ClassPathLoaderTypes;

public class ClassPathResolver {

	private static ClassPathResolver resolver = new ClassPathResolver();
	private Map<String, ClassGrouper> classesPerPackage;
	private ClassPathLoader classPathLoader = ClassPathLoaderTypes.folder();
	private JavaClassPathResolver javaClassPathResolver;

	public static ClassPathResolver getInstance(JavaClassPathResolver javaClassPathResolver) {
		resolver.setJavaClassPathResolver(javaClassPathResolver);
		return resolver;
	}

	private void setJavaClassPathResolver(JavaClassPathResolver javaClassPathResolver) {
		this.javaClassPathResolver = javaClassPathResolver;
	}

	private ClassPathResolver() {
	}

	public void limitSearchingPathTo(ClassPathLoader classPathLoader) {
		this.classPathLoader = classPathLoader;
	}

	public Set<Class<?>> getClassesExactlyIn(String packageName) {
		emptyStringValidation(packageName);
		initializeClassesPerPackage();
		ClassGrouper classGrouper = classesPerPackage.get(packageName);
		if (classGrouper == null) {
			return new HashSet<Class<?>>();
		}
		return classGrouper.getClassesOnString();
	}

	public Set<Class<?>> getClassesStartingIn(String packageName) {
		emptyStringValidation(packageName);
		initializeClassesPerPackage();
		Set<Class<?>> retorno = new HashSet<Class<?>>();
		for (String packageNameKey : classesPerPackage.keySet()) {
			if (packageNameKey.startsWith(packageName)) {
				retorno.addAll(classesPerPackage.get(packageNameKey).getClassesOnString());
			}
		}
		return retorno;
	}

	private void initializeClassesPerPackage() {
		
		if (classesPerPackage == null || classesPerPackage.size() == 0) {
			initializeClassesPerPackageMap();
			sortByPackage(loadClassesByName());
		}
	}

	private void initializeClassesPerPackageMap() {
		classesPerPackage = new HashMap<String, ClassGrouper>();
	}

	private Set<String> loadClassesByName() {
		Set<String> classeByName = getClassesNames(getClassesAsFile());
		return classeByName;
	}

	private List<File> getClassesAsFile() {
		List<File> classPath = new ArrayList<File>();

		mountURLsToFile(classPath, javaClassPathResolver.getClassPathUrl());

		return classPath;
	}

	private void mountURLsToFile(List<File> classPath, URL[] urls) {
		if (urls != null) {
			for (URL url : urls) {
				try {
					classPath.add(new File(url.toURI()));
				} catch (URISyntaxException e) {
					 e.printStackTrace();
				}
			}
		}
	}

	private Set<String> getClassesNames(List<File> classPath) {
		return classPathLoader.resolveClassName(classPath);
	}

	private void sortByPackage(Set<String> classeByName) {
		for (String className : classeByName) {
			String packageName = extractPackageNameFromFullQualifiedName(className);
			verifyThatPackageAlreadyExists(packageName);
			classesPerPackage.get(packageName).addClass(className);
		}
	}

	private void verifyThatPackageAlreadyExists(String packageName) {
		if (!classesPerPackage.containsKey(packageName)) {
			classesPerPackage.put(packageName, new ClassGrouper(packageName));
		}
	}

}