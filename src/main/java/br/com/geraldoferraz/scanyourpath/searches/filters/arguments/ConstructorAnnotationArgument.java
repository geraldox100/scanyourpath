package br.com.geraldoferraz.scanyourpath.searches.filters.arguments;

import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.argumentValidation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

/**
 * This class is a argument that checks annotations on constructors
 * @author geraldo
 *
 */
public class ConstructorAnnotationArgument implements Argument {

	private final Class<? extends Annotation> annotation;

	ConstructorAnnotationArgument(Class<? extends Annotation> annotation) {
		argumentValidation(annotation);
		this.annotation = annotation;
	}

	public boolean validate(Class<?> clazz) {
		argumentValidation(clazz);
		Constructor<?>[] constructors = clazz.getConstructors();
		boolean answer = false;
		for (Constructor<?> constructor : constructors) {
			if (constructor.isAnnotationPresent(annotation)) {
				answer = true;
				break;
			}
		}
		return answer;
	}
	
	@Override
	public String toString() {
		return "Construcotr is annotated with: "+annotation.getSimpleName();
	}

}
