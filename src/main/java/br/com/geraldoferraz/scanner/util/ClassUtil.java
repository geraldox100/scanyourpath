package br.com.geraldoferraz.scanner.util;

import static br.com.geraldoferraz.scanner.util.ValidationUtil.argumentValidation;
import static br.com.geraldoferraz.scanner.util.ValidationUtil.emptyStringValidation;

import java.io.File;
import java.util.jar.JarEntry;

public final class ClassUtil {

	private ClassUtil() {
	}

	public static boolean isClass(File file) {
		argumentValidation(file);

		if (file.isFile()) {
			if (file.getName().endsWith(".class")) {
				return true;
			}
		}
		return false;
	}

	public static boolean isClass(JarEntry jarEntry) {
		argumentValidation(jarEntry);

		return jarEntry.getName().endsWith(".class");
	}

	public static String getFullQualifiedName(File file) {
		argumentValidation(file);

		String absolutePath = file.getAbsolutePath().replace(System.getProperty("file.separator"), ".").substring(1);
		String result = removeClassFromString(absolutePath);

		return result;
	}

	public static String getFullQualifiedName(JarEntry jarEntry) {
		argumentValidation(jarEntry);

		String replace = jarEntry.getName().replace(System.getProperty("file.separator"), ".");
		String result = removeClassFromString(replace);
		return result;
	}

	public static String extractPackageNameFromFullQualifiedName(String className) {
		argumentValidation(className);
		return className.substring(0, className.lastIndexOf("."));
	}

	public static String removeDirectory(String fullName, String folderToRemove) {
		emptyStringValidation(fullName);
		emptyStringValidation(folderToRemove);
		folderToRemove = folderToRemove.replace(System.getProperty("file.separator"), ".").substring(1) + ".";
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
