package br.com.geraldoferraz.scanyourpath.searches.filters.arguments;

import static br.com.geraldoferraz.scanyourpath.searches.filters.arguments.HasMethodArgument.Where.LEFT;
import static br.com.geraldoferraz.scanyourpath.searches.filters.arguments.HasMethodArgument.Where.RIGHT;

import java.lang.annotation.Annotation;

public final class SearchArguments {
	
	public static final CombinableArgument havingMethodAnnotedWith(Class<? extends Annotation> clazz) {
		return new CombinableArgument(new MethodAnnotationArgument(clazz));
	}
	
	public static final CombinableArgument havingFieldAnnotedWith(Class<? extends Annotation> clazz) {
		return new CombinableArgument(new FieldAnnotationArgument(clazz));
	}
	
	public static final CombinableArgument havingConstructorAnnotedWith(Class<? extends Annotation> clazz) {
		return new CombinableArgument(new ConstructorAnnotationArgument(clazz));
	}
	
	public static final CombinableArgument annotedWith(Class<? extends Annotation> clazz) {
		return new CombinableArgument(new ClassAnnotationArgument(clazz));
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
	
	public static final CombinableArgument havingMethodWithName(String method) {
		return new CombinableArgument(new HasMethodArgument(method));
	}
	
	public static final CombinableArgument havingMethodNameStartingWith(String method) {
		return new CombinableArgument(new HasMethodArgument(method,LEFT));
	}
	
	public static final CombinableArgument havingMethodNameEndingWith(String method) {
		return new CombinableArgument(new HasMethodArgument(method,RIGHT));
	}
	
	public static final CombinableArgument namedWith(String className) {
		return new CombinableArgument(new ClassNameIs(className));
	}
}
