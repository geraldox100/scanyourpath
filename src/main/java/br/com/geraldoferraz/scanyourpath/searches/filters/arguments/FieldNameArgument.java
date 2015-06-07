package br.com.geraldoferraz.scanyourpath.searches.filters.arguments;

import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.argumentValidation;
import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.emptyStringValidation;

import java.lang.reflect.Field;

import br.com.geraldoferraz.scanyourpath.util.enums.StringComparator;

public class FieldNameArgument implements Argument {

	private final String fieldName;
	private final StringComparator stringComparator;

	FieldNameArgument(String fieldName) {
		this(fieldName, StringComparator.EXACTLY);
	}

	FieldNameArgument(String fieldName, StringComparator stringComparator) {
		emptyStringValidation(fieldName);
		this.stringComparator = stringComparator;
		this.fieldName = fieldName;
	}

	public boolean validate(Class<?> clazz) {
		argumentValidation(clazz);

		try {
			Field[] declaredFields = clazz.getDeclaredFields();
			for (Field field : declaredFields) {
				if (stringComparator.compare(field.getName(), fieldName)) {
					return true;
				}
			}
		} catch (Exception e) {

		}
		return false;

	}

}
