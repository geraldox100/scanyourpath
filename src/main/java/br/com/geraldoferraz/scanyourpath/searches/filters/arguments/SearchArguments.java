package br.com.geraldoferraz.scanyourpath.searches.filters.arguments;

import static br.com.geraldoferraz.scanyourpath.util.enums.ParameterComparator.EXACTLY;
import static br.com.geraldoferraz.scanyourpath.util.enums.StringComparator.ENDS_WITH;
import static br.com.geraldoferraz.scanyourpath.util.enums.StringComparator.STARTS_WITH;

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
		return new CombinableArgument(new NoDontDoesnttArgument(argument));
	}

	public static final CombinableArgument dont(Argument argument) {
		return new CombinableArgument(new NoDontDoesnttArgument(argument));
	}

	public static final CombinableArgument doesnt(Argument argument) {
		return new CombinableArgument(new NoDontDoesnttArgument(argument));
	}

	public static final CombinableArgument havingMethodWithName(String methodName) {
		return new CombinableArgument(new MethodNameArgument(methodName));
	}

	public static final CombinableArgument havingMethodNameStartingWith(String methodName) {
		return new CombinableArgument(new MethodNameArgument(methodName, STARTS_WITH));
	}

	public static final CombinableArgument havingMethodNameEndingWith(String methodName) {
		return new CombinableArgument(new MethodNameArgument(methodName, ENDS_WITH));
	}

	public static final CombinableArgument havingFieldWithName(String fieldName) {
		return new CombinableArgument(new FieldNameArgument(fieldName));
	}

	public static final CombinableArgument havingFieldNameStartingWith(String fieldName) {
		return new CombinableArgument(new FieldNameArgument(fieldName, STARTS_WITH));
	}

	public static final CombinableArgument havingFieldNameEndingWith(String fieldName) {
		return new CombinableArgument(new FieldNameArgument(fieldName, ENDS_WITH));
	}

	public static final CombinableArgument namedWith(String className) {
		return new CombinableArgument(new ClassNameArgument(className));
	}

	public static final CombinableArgument thatNameStartsWith(String className) {
		return new CombinableArgument(new ClassNameArgument(className, STARTS_WITH));
	}

	public static final CombinableArgument thatNameEndsWith(String className) {
		return new CombinableArgument(new ClassNameArgument(className, ENDS_WITH));
	}

	public static final CombinableArgument havingConstructorReceivingAtLeast(Class<?>... parameter) {
		return new CombinableArgument(new ConstructorArgument(parameter));
	}

	public static final CombinableArgument havingConstructorReceivingExactly(Class<?>... parameter) {
		return new CombinableArgument(new ConstructorArgument(parameter, EXACTLY));
	}

}
