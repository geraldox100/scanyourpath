package br.com.geraldoferraz.scanyourpath.util;

public class EmptyStringException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	static final String PREFIX = "String is null or empty: ";

	public EmptyStringException(String value) {
		super(PREFIX + value);
	}

}
