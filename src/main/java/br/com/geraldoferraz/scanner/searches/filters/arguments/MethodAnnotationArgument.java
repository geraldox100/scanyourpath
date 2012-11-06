package br.com.geraldoferraz.scanner.searches.filters.arguments;

import static br.com.geraldoferraz.scanner.util.ValidationUtil.argumentValidation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public final class MethodAnnotationArgument implements Argument {

	private Class<? extends Annotation> annotation;

	MethodAnnotationArgument(Class<? extends Annotation> annotation) {
		argumentValidation(annotation);
		this.annotation = annotation;
	}

	@Override
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
