package br.com.geraldoferraz.scanyourpath.util;

import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.argumentValidation;
import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.emptyStringValidation;

import java.io.File;
import java.util.jar.JarEntry;

import br.com.geraldoferraz.scanyourpath.exception.NoPackageException;
public final class ClassUtil {
	
	private static String separator = System.getProperty("file.separator");

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

		String absolutePath = file.getAbsolutePath().replace(separator, ".").substring(1);
		String result = removeClassFromString(absolutePath);

		return result;
	}

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
