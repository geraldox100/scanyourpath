package br.com.geraldoferraz.scanyourpath.util.enums;

import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.emptyStringValidation;

/**
 * 
 * This class is for testing if the first string ends with, starts with or is exactly the same as the second string
 * @author Geraldo Ferraz
 *
 */
public enum StringComparator {
	ENDS_WITH {
		@Override
		public boolean compare(String o1, String o2) {
			emptyStringValidation(o1);
			emptyStringValidation(o2);
			return o1.endsWith(o2);
		}
	},
	STARTS_WITH {
		@Override
		public boolean compare(String o1, String o2) {
			emptyStringValidation(o1);
			emptyStringValidation(o2);
			return o1.startsWith(o2);
		}
	},
	EXACTLY {
		@Override
		public boolean compare(String o1, String o2) {
			emptyStringValidation(o1);
			emptyStringValidation(o2);
			return o1.equals(o2);
		}
	};

	public abstract boolean compare(String o1, String o2);
	
}