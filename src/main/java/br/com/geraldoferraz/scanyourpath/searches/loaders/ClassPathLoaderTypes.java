package br.com.geraldoferraz.scanyourpath.searches.loaders;

/**
 * This class provides instance of ClassPathLoader
 * @author Geraldo Ferraz 
 *
 */
public final class ClassPathLoaderTypes {

	/**
	 * Provides an instance of BinLoader that loads classes from your own application
	 * @return ClassPathLoader
	 */
	public static ClassPathLoader folder() {
		return new BinLoader();
	}

	/**
	 * Provides an instance of JarLoader that loads classes from your Jars
	 * @return ClassPathLoader
	 */
	public static ClassPathLoader jar() {
		return new JarLoader();
	}

	/**
	 * Provides an instance of FullLoader that loads classes from both jars and your own classes
	 * @return ClassPathLoader
	 */
	public static ClassPathLoader full() {
		return new FullLoader(new JarLoader(), new BinLoader());
	}

}
