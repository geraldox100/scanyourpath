package br.com.geraldoferraz.scanyourpath.util.enums;

import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.argumentValidation;
import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.emptyArrayValidation;
import static java.util.Arrays.asList;

import java.util.HashSet;

/**
 * 
 * This class is for testing whether the first parameter group is contained in the second or the first is exactly equal to the second
 * @author Geraldo Ferraz
 *
 */
public enum ParameterComparator {
	AT_LEAST {
		@Override
		public boolean compare(Class<?>[] parameters, Class<?>... parameterToCompare) {
			argumentValidation(parameters);
			argumentValidation(parameterToCompare);
			emptyArrayValidation(parameterToCompare);

			HashSet<Class<?>> parametersToCompareSet = new HashSet<Class<?>>(asList(parameterToCompare));
			HashSet<Class<?>> parametersSet = new HashSet<Class<?>>();
			for (Class<?> parameter : parameters) {
				if (parameter.isArray()) {
					parametersSet.add(parameter.getComponentType());
				}
				parametersSet.add(parameter);
			}
			return parametersSet.containsAll(parametersToCompareSet);
		}

	},
	EXACTLY {
		@Override
		public boolean compare(Class<?>[] parameters, Class<?>... parameterToCompare) {
			argumentValidation(parameters);
			argumentValidation(parameterToCompare);
			emptyArrayValidation(parameterToCompare);
			
			if (parameters.length == parameterToCompare.length) {
				for (int i = 0; i < parameterToCompare.length; i++) {
					if (!parameters[i].equals(parameterToCompare[i])) {
						return false;
					}
				}
				return true;
			}
			return false;
		}
	};

	public abstract boolean compare(Class<?>[] type, Class<?>... parameterToCompare);
}
