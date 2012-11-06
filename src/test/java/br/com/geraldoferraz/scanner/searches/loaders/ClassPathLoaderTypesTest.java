package br.com.geraldoferraz.scanner.searches.loaders;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import br.com.geraldoferraz.scanner.TestBase;
import br.com.geraldoferraz.scanner.searches.loaders.BinLoader;
import br.com.geraldoferraz.scanner.searches.loaders.ClassPathLoader;
import br.com.geraldoferraz.scanner.searches.loaders.ClassPathLoaderTypes;
import br.com.geraldoferraz.scanner.searches.loaders.FullLoader;
import br.com.geraldoferraz.scanner.searches.loaders.JarLoader;

public class ClassPathLoaderTypesTest extends TestBase {

	@Test
	public void whenAskForFolderLoader() {
		ClassPathLoader folderLoader = ClassPathLoaderTypes.folder();
		assertNotNull(folderLoader);
		assertEquals(BinLoader.class, folderLoader.getClass());
		assertThat(folderLoader, instanceOf(BinLoader.class));
	}
	
	@Test
	public void whenAskForJarLoader() {
		ClassPathLoader jarLoader = ClassPathLoaderTypes.jar();
		assertNotNull(jarLoader);
		assertEquals(JarLoader.class, jarLoader.getClass());
		assertThat(jarLoader, instanceOf(JarLoader.class));
	}
	
	@Test
	public void whenAskForFullLoader() {
		ClassPathLoader fullLoader = ClassPathLoaderTypes.full();
		assertNotNull(fullLoader);
		assertEquals(FullLoader.class, fullLoader.getClass());
		assertThat(fullLoader, instanceOf(FullLoader.class));
	}

}
