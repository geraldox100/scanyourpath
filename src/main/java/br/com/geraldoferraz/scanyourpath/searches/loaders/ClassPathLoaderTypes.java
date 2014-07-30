package br.com.geraldoferraz.scanyourpath.searches.loaders;

public final class ClassPathLoaderTypes {

	public static ClassPathLoader folder() {
		return new BinLoader();
	}

	public static ClassPathLoader jar() {
		return new JarLoader();
	}

	public static ClassPathLoader full() {
		return new FullLoader(new JarLoader(), new BinLoader());
	}

}
