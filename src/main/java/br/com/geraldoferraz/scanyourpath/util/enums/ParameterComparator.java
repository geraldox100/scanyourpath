package br.com.geraldoferraz.scanyourpath.util.enums;

import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.argumentValidation;
import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.emptyArrayValidation;
import static java.util.Arrays.asList;

import java.lang.reflect.Parameter;
import java.util.HashSet;

public enum ParameterComparator {
	AT_LEAST {
		@Override
		public boolean compare(Parameter[] parameters, Class<?>... parameterToCompare) {
			argumentValidation(parameters);
			argumentValidation(parameterToCompare);
			emptyArrayValidation(parameterToCompare);

			HashSet<Class<?>> parametersToCompareSet = new HashSet<Class<?>>(asList(parameterToCompare));
			HashSet<Class<?>> parametersSet = new HashSet<Class<?>>();
			for (Parameter parameter : parameters) {
				if (parameter.isVarArgs()) {
					parametersSet.add(parameter.getType().getComponentType());
				}
				parametersSet.add(parameter.getType());
			}
			return parametersSet.containsAll(parametersToCompareSet);
		}

	},
	EXACTLY {
		@Override
		public boolean compare(Parameter[] parameters, Class<?>... parameterToCompare) {
			argumentValidation(parameters);
			argumentValidation(parameterToCompare);
			emptyArrayValidation(parameterToCompare);
			
			if (parameters.length == parameterToCompare.length) {
				for (int i = 0; i < parameterToCompare.length; i++) {
					if (!parameters[i].getType().equals(parameterToCompare[i])) {
						return false;
					}
				}
				return true;
			}
			return false;
		}
	};

	public abstract boolean compare(Parameter[] type, Class<?>... parameterToCompare);
}
