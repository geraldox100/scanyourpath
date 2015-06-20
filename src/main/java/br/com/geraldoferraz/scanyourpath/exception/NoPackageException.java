package br.com.geraldoferraz.scanyourpath.exception;

/**
 *
 * Exception thrown when a class does not have package
 * @author Geraldo Ferraz
 */
public class NoPackageException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;
	
	
	public NoPackageException(String className) {
		super("Class "+className+" has no package");
	}
	
	

}
