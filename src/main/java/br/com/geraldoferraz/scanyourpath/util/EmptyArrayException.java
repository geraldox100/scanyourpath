package br.com.geraldoferraz.scanyourpath.util;

/**
 * Exception to be thrown when an array is null or empty
 * @author Geraldo Ferraz
 *
 */
//TODO move to exception package
public class EmptyArrayException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	static final String PREFIX = "Array is null or empty";

	public EmptyArrayException() {
		super(PREFIX);
	}

}
