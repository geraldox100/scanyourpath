package br.com.geraldoferraz.scanyourpath.resolver;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.*;
import javassist.ClassPool;
import javassist.CtClass;

/**
 * This class represents a group of classes by their package
 * Used by the framework to gain performance on searches
 * @author Geraldo Ferraz
 */
public class ClassGrouper {

	private String packageName;
	private Set<String> classesOnString = new HashSet<String>();
	private Set<Class<?>> classes = new HashSet<Class<?>>();

	public ClassGrouper(String packageName) {
		emptyStringValidation(packageName);
		this.packageName = packageName;
	}

	/**
	 * The packagename the classes this group belongs
	 * @return the package name
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * The classes this group holds
	 * @return The classes this group holds
	 */
	public Set<Class<?>> getClassesOnString() {
		initializeClasses();
		return Collections.unmodifiableSet(classes);
	}

	// TODO refatorar este metodo
	private void initializeClasses() {
		if (classes.size() == 0) {
			for (String className : classesOnString) {
				try {
					ClassPool pool = ClassPool.getDefault();
					CtClass cc = pool.get(className);
					Class<?> clazz = cc.toClass();
					clazz = replaceClazzForSuperClassIfClazzIsEnum(clazz);
					classes.add(clazz);
				} catch (Throwable e) {
					try {
						Class<?> clazz = Class.forName(className);
						clazz = replaceClazzForSuperClassIfClazzIsEnum(clazz);
						classes.add(clazz);
					} catch (Throwable e1) {
					}
				}
			}
		}
	}

	private Class<?> replaceClazzForSuperClassIfClazzIsEnum(Class<?> clazz) {
		if(clazz.getSimpleName().length() == 0){
			clazz = clazz.getSuperclass();
		}
		return clazz;
	}

	/**
	 * Adds a class to this group
	 * @param clazz the name of the class to be added to this group
	 * @throws br.com.geraldoferraz.scanyourpath.util.EmptyStringException if the name is null or empty
	 * @throws IllegalArgumentException if the clazz does not belog to the package this group holds
	 */
	public void addClass(String clazz) {
		emptyStringValidation(clazz);
		verifyThatClassIsFromSamePackage(clazz);
		this.classesOnString.add(clazz);
	}

	//TODO geraldoferraz mover este metodo para a classde ValidationUtil
	private void verifyThatClassIsFromSamePackage(String clazz) {
		if (!clazz.startsWith(packageName)) {
			throw new IllegalArgumentException("The class: "+clazz+" does not belong to the package: "+packageName);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((packageName == null) ? 0 : packageName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClassGrouper other = (ClassGrouper) obj;
		if (packageName == null) {
			if (other.packageName != null)
				return false;
		} else if (!packageName.equals(other.packageName))
			return false;
		return true;
	}

}
