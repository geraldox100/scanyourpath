package br.com.geraldoferraz.scanyourpath.searches.filters.arguments;

import java.lang.annotation.Annotation;

import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.*;

public final class ClassAnnotationArgument implements Argument {

	private final Class<? extends Annotation> annotation;

	ClassAnnotationArgument(Class<? extends Annotation> annotation) {
		argumentValidation(annotation);
		this.annotation = annotation;
	}

	public boolean validate(Class<?> clazz) {
		argumentValidation(clazz);
		return clazz.isAnnotationPresent(this.annotation);
	}

	@Override
	public String toString() {
		return "Class annoted with: " + annotation.getSimpleName();
	}

}