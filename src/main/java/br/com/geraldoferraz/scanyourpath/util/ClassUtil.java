package br.com.geraldoferraz.scanyourpath.util;

import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.argumentValidation;
import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.emptyStringValidation;

import java.io.File;
import java.util.jar.JarEntry;

import br.com.geraldoferraz.scanyourpath.exception.NoPackageException;

/**
 * The class is used for class validation purpose
 * @author Geraldo Ferraz
 *
 */
public final class ClassUtil {
	
	private static String separator = System.getProperty("file.separator");

	private ClassUtil() {
	}

	/**
	 * This method verifys if the given file is a class representation
	 * @param file the file to be verified
	 * @return true if the file represents a class and false otherwise
	 * @throws IllegalArgumentException if the file is null
	 */
	public static boolean isClass(File file) {
		argumentValidation(file);

		if (file.isFile()) {
			if (file.getName().endsWith(".class")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This method verifys if the given JarEntry is a class representation
	 * @param jarEntry the JarEntry to be verified
	 * @return true if the JarEntry represents a class and false otherwise
	 * @throws IllegalArgumentException if the jarEntry is null
	 */
	public static boolean isClass(JarEntry jarEntry) {
		argumentValidation(jarEntry);
		return jarEntry.getName().endsWith(".class");
	}

	/**
	 * Extracts the full qualified name of the given File
	 * @param file the file to extract the full qualifield name
	 * @return the full qualified name
	 * @throws IllegalArgumentException if the file is null
	 */
	public static String getFullQualifiedName(File file) {
		 argumentValidation(file);

		String absolutePath = file.getAbsolutePath().replace(separator, ".").substring(1);
		String result = removeClassFromString(absolutePath);

		return result;
	}

	/**
	 * Extracts the full qualified name of the given JarEntry
	 * @param jarEntry the JarEntry to extract the full qualifield name
	 * @return the full qualified name
	 * @throws IllegalArgumentException if the JarEntry is null
	 */
	public static String getFullQualifiedName(JarEntry jarEntry) {
		argumentValidation(jarEntry);

		String replace = replaceSeparatorByPoint(jarEntry.getName());
		String result = removeClassFromString(replace);
		return result;
	}

	private static String replaceSeparatorByPoint(String name) {
		name = name.replace(separator, ".");
		name = name.replace("/", ".");
		name = name.replace("\\", ".");
		return name;
	}

	/**
	 * Returns the package from a full qualified name
	 * @param className The class name to extract the package
	 * @return the package from the class
	 * @throws IllegalArgumentException if the class name is null
	 * @throws br.com.geraldoferraz.scanyourpath.exception.NoPackageException if the class has no package
	 */
	public static String extractPackageNameFromFullQualifiedName(String className) {
		argumentValidation(className);
		chckeThatClassContainsPackage(className);
		return className.substring(0, className.lastIndexOf("."));
	}

	private static void chckeThatClassContainsPackage(String className) {
		if(!className.contains(".")){
			throw new NoPackageException("Class "+className+" has no package");
		}
	}

	/**
	 * Removes the given folder from the given string
	 * @param fullName the string to remove the folder
	 * @param folderToRemove the folder to remove from string
	 * @return the string without the folder
	 * @throws br.com.geraldoferraz.scanyourpath.util.EmptyStringException if any parameters is null or empty
	 */
	public static String removeDirectory(String fullName, String folderToRemove) {
		emptyStringValidation(fullName);
		emptyStringValidation(folderToRemove);
		folderToRemove = folderToRemove.replace(separator, ".").substring(1) + ".";
		fullName = fullName.replace(folderToRemove, "");
		return fullName;
	}
	
	
	private static String removeClassFromString(String substring) {
		String result = substring.replace("classes", "SOMTHING_ONLY_I_SHOULD_NOW");
		result = result.replace(".class", "");
		result = result.replace("SOMTHING_ONLY_I_SHOULD_NOW", "classes");
		return result;
	}

}
