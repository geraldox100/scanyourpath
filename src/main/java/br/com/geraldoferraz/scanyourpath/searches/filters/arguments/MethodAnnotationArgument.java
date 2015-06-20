package br.com.geraldoferraz.scanyourpath.searches.filters.arguments;

import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.argumentValidation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * This class is a argument that checks annotations on methods
 * @author Geraldo Ferraz
 *
 */
public final class MethodAnnotationArgument implements Argument {

	private Class<? extends Annotation> annotation;

	MethodAnnotationArgument(Class<? extends Annotation> annotation) {
		argumentValidation(annotation);
		this.annotation = annotation;
	}

	public boolean validate(Class<?> clazz) {
		argumentValidation(clazz);
		try {
			Method[] declaredMethods = clazz.getDeclaredMethods();
			for (Method method : declaredMethods) {
				if (method.isAnnotationPresent(this.annotation)) {
					return true;
				}
			}
		} catch (Error e) {
//			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Method annoted with: "+annotation.getSimpleName();
	}
	

}
