package br.com.geraldoferraz.scanyourpath.searches.filters.arguments;

import static br.com.geraldoferraz.scanyourpath.util.enums.ParameterComparator.EXACTLY;
import static br.com.geraldoferraz.scanyourpath.util.enums.StringComparator.ENDS_WITH;
import static br.com.geraldoferraz.scanyourpath.util.enums.StringComparator.STARTS_WITH;

import java.lang.annotation.Annotation;

/**
 * 
 * @author Geraldo Ferraz
 * This class provides a set of combinable arguments that can be used to refine searches on the Scanner class
 */
public final class SearchArguments {

	/**
	 * Provides a Argument that checks if the class has a method with the given annotation
	 * @param clazz to be verified
	 * @return a CombinableArgument that allows you to combine other arguments (or, and)
	 */
	public static final CombinableArgument havingMethodAnnotatedWith(Class<? extends Annotation> clazz) {
		return new CombinableArgument(new MethodAnnotationArgument(clazz));
	}

	/**
	 * Provides a Argument that checks if the class has a field with the given annotation
	 * @param clazz to be verified
	 * @return a CombinableArgument that allows you to combine other arguments (or, and)
	 */
	public static final CombinableArgument havingFieldAnnotateddWith(Class<? extends Annotation> clazz) {
		return new CombinableArgument(new FieldAnnotationArgument(clazz));
	}

	/**
	 * Provides a Argument that checks if the class has a constructor with the given annotation
	 * @param clazz to be verified
	 * @return a CombinableArgument that allows you to combine other arguments (or, and)
	 */
	public static final CombinableArgument havingConstructorAnnotateddWith(Class<? extends Annotation> clazz) {
		return new CombinableArgument(new ConstructorAnnotationArgument(clazz));
	}

	/**
	 * Provides a Argument that checks if the class is annotated with the given annotation
	 * @param clazz to be verified
	 * @return a CombinableArgument that allows you to combine other arguments (or, and)
	 */
	public static final CombinableArgument annotatedWith(Class<? extends Annotation> clazz) {
		return new CombinableArgument(new ClassAnnotationArgument(clazz));
	}

	/**
	 * Provides a Argument that checks if the class extends the given class
	 * @param clazz to be verified
	 * @return a CombinableArgument that allows you to combine other arguments (or, and)
	 */
	public static final CombinableArgument thatExtends(Class<? extends Object> clazz) {
		return new CombinableArgument(new ExtendsArgument(clazz));
	}

	/**
	 * Provides a Argument that checks if the class implements the given interface
	 * @param clazz to be verified
	 * @return a CombinableArgument that allows you to combine other arguments (or, and)
	 */
	public static final CombinableArgument thatImplements(Class<?> clazz) {
		return new CombinableArgument(new ImplementsArgument(clazz));
	}

	/**
	 * Provides a Argument that denies a given argument
	 * @param clazz to be verified
	 * @return a CombinableArgument that allows you to combine other arguments (or, and)
	 */
	public static final CombinableArgument not(Argument argument) {
		return new CombinableArgument(new NoDontDoesnttArgument(argument));
	}

	/**
	 * Provides a Argument that denies a given argument
	 * @param clazz to be verified
	 * @return a CombinableArgument that allows you to combine other arguments (or, and)
	 */
	public static final CombinableArgument dont(Argument argument) {
		return new CombinableArgument(new NoDontDoesnttArgument(argument));
	}

	/**
	 * Provides a Argument that denies a given argument
	 * @param clazz to be verified
	 * @return a CombinableArgument that allows you to combine other arguments (or, and)
	 */
	public static final CombinableArgument doesnt(Argument argument) {
		return new CombinableArgument(new NoDontDoesnttArgument(argument));
	}

	/**
	 * Provides a Argument that checks if the class has a method with the given name
	 * @param clazz to be verified
	 * @return a CombinableArgument that allows you to combine other arguments (or, and)
	 */
	public static final CombinableArgument havingMethodWithName(String methodName) {
		return new CombinableArgument(new MethodNameArgument(methodName));
	}

	/**
	 * Provides a Argument that checks if the class has a method with the name starting like the given name
	 * @param clazz to be verified
	 * @return a CombinableArgument that allows you to combine other arguments (or, and)
	 */
	public static final CombinableArgument havingMethodNameStartingWith(String methodName) {
		return new CombinableArgument(new MethodNameArgument(methodName, STARTS_WITH));
	}

	/**
	 * Provides a Argument that checks if the class has a method with the name ending like the given name
	 * @param clazz to be verified
	 * @return a CombinableArgument that allows you to combine other arguments (or, and)
	 */
	public static final CombinableArgument havingMethodNameEndingWith(String methodName) {
		return new CombinableArgument(new MethodNameArgument(methodName, ENDS_WITH));
	}

	/**
	 * Provides a Argument that checks if the class has a field with the given name
	 * @param clazz to be verified
	 * @return a CombinableArgument that allows you to combine other arguments (or, and)
	 */
	public static final CombinableArgument havingFieldWithName(String fieldName) {
		return new CombinableArgument(new FieldNameArgument(fieldName));
	}

	/**
	 * Provides a Argument that checks if the class has a field with the name starting like the given name
	 * @param clazz to be verified
	 * @return a CombinableArgument that allows you to combine other arguments (or, and)
	 */
	public static final CombinableArgument havingFieldNameStartingWith(String fieldName) {
		return new CombinableArgument(new FieldNameArgument(fieldName, STARTS_WITH));
	}

	/**
	 * Provides a Argument that checks if the class has a field with the name ending like the given name
	 * @param clazz to be verified
	 * @return a CombinableArgument that allows you to combine other arguments (or, and)
	 */
	public static final CombinableArgument havingFieldNameEndingWith(String fieldName) {
		return new CombinableArgument(new FieldNameArgument(fieldName, ENDS_WITH));
	}

	/**
	 * Provides a Argument that checks if the class has the same name as the given class
	 * @param clazz to be verified
	 * @return a CombinableArgument that allows you to combine other arguments (or, and)
	 */
	public static final CombinableArgument namedWith(String className) {
		return new CombinableArgument(new ClassNameArgument(className));
	}

	/**
	 * Provides a Argument that checks if the class name starts with the given name
	 * @param clazz to be verified
	 * @return a CombinableArgument that allows you to combine other arguments (or, and)
	 */
	public static final CombinableArgument thatNameStartsWith(String className) {
		return new CombinableArgument(new ClassNameArgument(className, STARTS_WITH));
	}

	/**
	 * Provides a Argument that checks if the class name ends with the given name
	 * @param clazz to be verified
	 * @return a CombinableArgument that allows you to combine other arguments (or, and)
	 */
	public static final CombinableArgument thatNameEndsWith(String className) {
		return new CombinableArgument(new ClassNameArgument(className, ENDS_WITH));
	}

	/**
	 * Provides a Argument that checks if the class has a constructor with at least the given parameters
	 * @param clazz to be verified
	 * @return a CombinableArgument that allows you to combine other arguments (or, and)
	 */
	public static final CombinableArgument havingConstructorReceivingAtLeast(Class<?>... parameter) {
		return new CombinableArgument(new ConstructorArgument(parameter));
	}

	/**
	 * Provides a Argument that checks if the class has a constructor with exacts same parameters
	 * @param clazz to be verified
	 * @return a CombinableArgument that allows you to combine other arguments (or, and)
	 */
	public static final CombinableArgument havingConstructorReceivingExactly(Class<?>... parameter) {
		return new CombinableArgument(new ConstructorArgument(parameter, EXACTLY));
	}

}
