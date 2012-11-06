package br.com.geraldoferraz.scanner.resolver;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static br.com.geraldoferraz.scanner.util.ValidationUtil.*;

import javassist.ClassPool;
import javassist.CtClass;

public class ClassGrouper {

	private String packageName;
	private Set<String> classesOnString = new HashSet<String>();
	private Set<Class<?>> classes = new HashSet<Class<?>>();

	public ClassGrouper(String packageName) {
		emptyStringValidation(packageName);
		this.packageName = packageName;
	}

	public String getPackageName() {
		return packageName;
	}

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
					classes.add(cc.toClass());
				} catch (Throwable e) {
					try {
						classes.add(Class.forName(className));
					} catch (ClassNotFoundException e1) {
						// e1.printStackTrace();
					}
					// e.printStackTrace();
				}
			}
		}
	}

	public void addClass(String clazz) {
		emptyStringValidation(clazz);
		verifyThatClassIsFromSamePackage(clazz);
		this.classesOnString.add(clazz);
	}

	private void verifyThatClassIsFromSamePackage(String clazz) {
		if (!clazz.startsWith(packageName)) {
			throw new IllegalArgumentException();
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
