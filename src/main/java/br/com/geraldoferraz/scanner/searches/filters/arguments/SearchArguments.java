package br.com.geraldoferraz.scanner.searches.filters.arguments;

import java.lang.annotation.Annotation;
public final class SearchArguments {
	public static final CombinableArgument annotedOnClassWith(Class<? extends Annotation> clazz) {
		return new CombinableArgument(new ClassAnnotationArgument(clazz));
	}

	public static final CombinableArgument annotedOnMethodWith(Class<? extends Annotation> clazz) {
		return new CombinableArgument(new MethodAnnotationArgument(clazz));
	}
	
	public static final CombinableArgument annotedOnFieldWith(Class<? extends Annotation> clazz) {
		return new CombinableArgument(new FieldAnnotationArgument(clazz));
	}
	
	public static final CombinableArgument thatExtends(Class<? extends Object> clazz) {
		return new CombinableArgument(new ExtendsArgument(clazz));
	}
	
	public static final CombinableArgument thatImplements(Class<?> clazz) {
		return new CombinableArgument(new ImplementsArgument(clazz));
	}
	
	public static final CombinableArgument not(Argument argument) {
		return new CombinableArgument(new NotArgument(argument));
	}
}
