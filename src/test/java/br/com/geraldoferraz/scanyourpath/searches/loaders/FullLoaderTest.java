package br.com.geraldoferraz.scanyourpath.searches.loaders;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Spy;

import br.com.geraldoferraz.scanyourpath.TestBase;
import br.com.geraldoferraz.scanyourpath.searches.loaders.BinLoader;
import br.com.geraldoferraz.scanyourpath.searches.loaders.FullLoader;
import br.com.geraldoferraz.scanyourpath.searches.loaders.JarLoader;

public class FullLoaderTest extends TestBase {

	private FullLoader fullLoader;

	@Spy
	private JarLoader jarLoader = new JarLoader();
	@Spy
	private BinLoader binLoader = new BinLoader();

	private LoaderUtil util;

	@Before
	public void before() {
		fullLoader = new FullLoader(jarLoader, binLoader);
		util = new LoaderUtil();
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenCreatinANewFullLoaderPassingNullJarLoader() {
		fullLoader = new FullLoader(null, binLoader);
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenCreatinANewFullLoaderPassingNullBiLoader() {
		fullLoader = new FullLoader(jarLoader, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenResolveClassNamePassingNullArgument() {
		fullLoader.resolveClassName(null);
	}

	@Test
	public void whenResolveClassNamePassingEmptyFileList() {
		Set<String> classes = fullLoader.resolveClassName(new ArrayList<File>());
		assertEquals(0, classes.size());
	}

	@Test
	public void whenResolveClassName() {
		List<File> classpath = new ArrayList<File>();

		classpath.add(util.getEmptyJarFile());
		classpath.add(util.getNotEmptyJarFile());
		classpath.add(util.getSourceFolderBin());

		Set<String> classes = fullLoader.resolveClassName(classpath);

		assertEquals(3, classes.size());
	}

}
