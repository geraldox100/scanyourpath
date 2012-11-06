package br.com.geraldoferraz.scanner.searches.filters.arguments;

import java.lang.annotation.Annotation;

import static br.com.geraldoferraz.scanner.util.ValidationUtil.*;

public final class ClassAnnotationArgument implements Argument {

	private final Class<? extends Annotation> annotation;

	ClassAnnotationArgument(Class<? extends Annotation> annotation) {
		argumentValidation(annotation);
		this.annotation = annotation;
	}

	@Override
	public boolean validate(Class<?> clazz) {
		argumentValidation(clazz);
		return clazz.isAnnotationPresent(this.annotation);
	}

	@Override
	public String toString() {
		return "Class annoted with: " + annotation.getSimpleName();
	}

}