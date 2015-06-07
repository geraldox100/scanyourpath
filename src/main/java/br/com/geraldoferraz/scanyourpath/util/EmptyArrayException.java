package br.com.geraldoferraz.scanyourpath.util;

public class EmptyArrayException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	static final String PREFIX = "Array is null or empty";

	public EmptyArrayException() {
		super(PREFIX);
	}

}
