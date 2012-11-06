package br.com.geraldoferraz.scanner.util;

public class ValidationUtil {

	public static void argumentValidation(Object o) {
		if (o == null) {
			throw new IllegalArgumentException("Argument cant be null");
		}
	}

	public static void stateValidation(Object o) {
		if (o == null) {
			throw new IllegalStateException("Invalid State");
		}
	}

	public static void emptyStringValidation(String value) {
		if (value == null || value.trim().length() == 0) {
			throw new EmptyStringException(value);
		}
	}

}
