package br.com.geraldoferraz.scanyourpath.resolver;

import static br.com.geraldoferraz.scanyourpath.util.ClassUtil.extractPackageNameFromFullQualifiedName;
import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.emptyStringValidation;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.geraldoferraz.scanyourpath.exception.NoPackageException;
import br.com.geraldoferraz.scanyourpath.searches.loaders.ClassPathLoader;
import br.com.geraldoferraz.scanyourpath.searches.loaders.ClassPathLoaderTypes;

/**
 * This class loads classes, sort by package and store them on a class grouper
 * @author Geraldo Ferraz
 */
public class ClassPathResolver {

	private static ClassPathResolver resolver = new ClassPathResolver();
	private Map<String, ClassGrouper> classesPerPackage;
	private ClassPathLoader classPathLoader = ClassPathLoaderTypes.folder();
	private JavaClassPathResolver javaClassPathResolver;

	/**
	 * This method gets the singleton instance of ClassPathResolver
	 * @param javaClassPathResolver
	 * @return the instance of ClassPathResolver
	 */
	public static ClassPathResolver getInstance(JavaClassPathResolver javaClassPathResolver) {
		resolver.setJavaClassPathResolver(javaClassPathResolver);
		return resolver;
	}

	private void setJavaClassPathResolver(JavaClassPathResolver javaClassPathResolver) {
		this.javaClassPathResolver = javaClassPathResolver;
	}

	private ClassPathResolver() {
	}

	/**
	 * This method limits the searching path to Jar, Folder or Full
	 * @param classPathLoader The class path loader 
	 * @see {@link br.com.geraldoferraz.scanyourpath.searches.loaders.ClassPathLoaderTypes}
	 */
	public void limitSearchingPathTo(ClassPathLoader classPathLoader) {
		this.classPathLoader = classPathLoader;
	}

	/**
	 * This method searches and returns the classes from every package found
	 * @return The set of classes loaded
	 */
	public Set<Class<?>> getClassesAnyWhere() {
		initializeClassesPerPackage();
		Set<Class<?>> retorno = new HashSet<Class<?>>();
		for (String packageNameKey : classesPerPackage.keySet()) {
			retorno.addAll(classesPerPackage.get(packageNameKey).getClassesOnString());
		}
		return retorno;
	}

	/**
	 * This method searches and returns all classes found exactly in the given package
	 * @param packageName the package to load
	 * @return The set of classes that where found on the given package
	 * @throws br.com.geraldoferraz.scanyourpath.util.EmptyStringException if the given package is null or empty
	 */
	public Set<Class<?>> getClassesExactlyIn(String packageName) {
		emptyStringValidation(packageName);
		initializeClassesPerPackage();
		ClassGrouper classGrouper = classesPerPackage.get(packageName);
		if (classGrouper == null) {
			return new HashSet<Class<?>>();
		}
		return classGrouper.getClassesOnString();
	}

	/**
	 * This method searches and returns all classes found on the given package and sub-packages
	 * @param packageName the package to load
	 * @return The set of classes that where found on the given package and sub-packages
	 * @throws br.com.geraldoferraz.scanyourpath.util.EmptyStringException if the given package is null or empty
	 */
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
			try {
				String packageName = extractPackageNameFromFullQualifiedName(className);
				verifyThatPackageAlreadyExists(packageName);
				classesPerPackage.get(packageName).addClass(className);
			} catch (NoPackageException e) {

			}
		}
	}

	private void verifyThatPackageAlreadyExists(String packageName) {
		if (!classesPerPackage.containsKey(packageName)) {
			classesPerPackage.put(packageName, new ClassGrouper(packageName));
		}
	}

}