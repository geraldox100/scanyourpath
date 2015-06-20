package br.com.geraldoferraz.scanyourpath.util;

/**
 * This class contains sorts of validatiosn
 * @author Geraldo Ferraz
 *
 */
public class ValidationUtil {

	/**
	 * This methods is used to check if a parameter is null
	 * @param o the parameter to be checked
	 * @throws IllegalArgumentException if the parameter is null
	 */
	public static void argumentValidation(Object o) {
		if (o == null) {
			throw new IllegalArgumentException("Argument cant be null");
		}
	}

	/**
	 * This methods is used to check if a parameter is null
	 * @param o the parameter to be checked
	 * @throws IllegalStateException if the parameter is null
	 */
	public static void stateValidation(Object o) {
		if (o == null) {
			throw new IllegalStateException("Invalid State");
		}
	}

	/**
	 * This methods is used to check if a string is null or empty
	 * @param o the string to be checked
	 * @throws br.com.geraldoferraz.scanyourpath.util.EmptyStringException if the parameter is null
	 */
	public static void emptyStringValidation(String value) {
		if (value == null || value.isEmpty()) {
			throw new EmptyStringException(value);
		}
	}
	
	/**
	 * This methods is used to check if a set of parameters is null
	 * @param objects the set of parameters to be checked
	 * @throws IllegalArgumentException if any of the parameteres is null
	 */
	public static void argumentsValidation(Object... objects) {
		for (Object object : objects) {
			argumentValidation(object);
		}
	}
	
	/**
	 * This method is used to check if the array is null or empty
	 * @param array the array to be checked
	 * @throws br.com.geraldoferraz.scanyourpath.util.EmptyArrayException if the array is null or empty
	 */
	public static void emptyArrayValidation(Object[] array){
		if(array == null || array.length == 0){
			throw new EmptyArrayException();
		}
	}

}
