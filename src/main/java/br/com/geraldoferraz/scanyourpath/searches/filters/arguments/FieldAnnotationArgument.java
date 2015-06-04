package br.com.geraldoferraz.scanyourpath.searches.filters.arguments;

import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.argumentValidation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public final class FieldAnnotationArgument implements Argument {

	private final Class<? extends Annotation> annotation;

	FieldAnnotationArgument(Class<? extends Annotation> annotation) {
		argumentValidation(annotation);
		this.annotation = annotation;
	}

	public boolean validate(Class<?> clazz) {
		argumentValidation(clazz);
		Field[] declaredFields = clazz.getDeclaredFields();
		for (Field field : declaredFields) {
			if (field.isAnnotationPresent(annotation)) {
				return true;
			}
		}

		return false;
	}
	
	@Override
	public String toString() {
		return "Field annoted with: "+annotation.getSimpleName();
	}
	

}
