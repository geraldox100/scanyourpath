package br.com.geraldoferraz.scanyourpath.exception;

public class NoPackageException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public NoPackageException(String className) {
		super("Class "+className+" has no package");
	}
	
	

}
