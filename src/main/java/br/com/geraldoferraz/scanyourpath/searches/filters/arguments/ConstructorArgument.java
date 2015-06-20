package br.com.geraldoferraz.scanyourpath.searches.filters.arguments;

import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.argumentValidation;
import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.emptyArrayValidation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;

import br.com.geraldoferraz.scanyourpath.util.enums.ParameterComparator;

/**
 * This is a argument class that checks if a constructor has any of the given paramters
 * @author geraldo
 *
 */
public class ConstructorArgument implements Argument {

	private final Class<?>[] parameter;
	private final ParameterComparator parameterComparator;

	ConstructorArgument(Class<?> parameter) {
		this(parameter, ParameterComparator.EXACTLY);
	}
	
	ConstructorArgument(Class<?>... parameter) {
		this(parameter, ParameterComparator.EXACTLY);
	}

	ConstructorArgument(Class<?> parameter, ParameterComparator parameterComparator) {
		this(new Class<?>[] { parameter }, parameterComparator);
		argumentValidation(parameter);
	}

	ConstructorArgument(Class<?>[] parameter, ParameterComparator parameterComparator) {
		argumentValidation(parameter);
		emptyArrayValidation(parameter);
		argumentValidation(parameterComparator);
		this.parameterComparator = parameterComparator;
		this.parameter = parameter;
	}

	public boolean validate(Class<?> clazz) {
		argumentValidation(clazz);

		Constructor<?>[] constructors = clazz.getDeclaredConstructors();
		for (Constructor<?> constructor : constructors) {
			Parameter[] parameters = constructor.getParameters();
			if (parameterComparator.compare(parameters, parameter)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Has constructor with "+parameterComparator+" "+parameter;
	}

}
