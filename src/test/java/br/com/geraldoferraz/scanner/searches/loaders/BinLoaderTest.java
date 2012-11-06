package br.com.geraldoferraz.scanner.searches.loaders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import br.com.geraldoferraz.scanner.TestBase;
import br.com.geraldoferraz.scanner.searches.loaders.BinLoader;

public class BinLoaderTest extends TestBase {

	private BinLoader loader;
	private LoaderUtil util;

	@Before
	public void before() {
		loader = new BinLoader();
		util = new LoaderUtil();
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenGetClassInDirectoryPassingNullFile() {
		loader.getClassesInDirectory(null, "hello");
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenGetClassInDirectoryPassingNullPackageToRemove() {
		loader.getClassesInDirectory(new File(""), null);
	}

	@Test
	public void whenGetClassInDirectory() {
		Set<String> classesInDirectory = loader.getClassesInDirectory(util.getRootFolder(), util.getRootPath());
		assertEquals(2, classesInDirectory.size());
		assertTrue(classesInDirectory.contains("br.com.geraldo.Classe"));
		assertTrue(classesInDirectory.contains("br.com.geraldo.package.Classe"));
	}

	@Test
	public void whenLocationHasNoFiles() {
		when(util.getRootFolder().listFiles()).thenReturn(new File[] {});
		Set<String> classesInDirectory = loader.getClassesInDirectory(util.getRootFolder(), util.getRootPath());
		assertEquals(0, classesInDirectory.size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenResolveClassNamePassingANullList() {
		loader.resolveClassName(null);
	}

	@Test
	public void whenResolveClassNamePassingEmptyList() {
		Set<String> resolveClassName = loader.resolveClassName(new ArrayList<File>());
		assertEquals(0, resolveClassName.size());
	}

	@Test
	public void whenResolveClassNamePassingFileThatsNotADirectory() {
		Set<String> resolveClassName = loader.resolveClassName(Arrays.asList(util.getClazz()));
		assertEquals(0, resolveClassName.size());
	}

	@Test
	public void whenResolveClassName() {
		Set<String> resolveClassName = loader.resolveClassName(Arrays.asList(util.getSourceFolderBin()));
		assertEquals(2, resolveClassName.size());
		assertTrue(resolveClassName.contains("br.com.geraldo.Classe"));
		assertTrue(resolveClassName.contains("br.com.geraldo.package.Classe"));
	}

	@Test
	public void whenResolveClassNameWithMoreThenOneFolder() {
		Set<String> resolveClassName = loader.resolveClassName(Arrays.asList(util.getSourceFolderBin(), util.getSourceFolderTest()));
		assertEquals(2, resolveClassName.size());
		assertTrue(resolveClassName.contains("br.com.geraldo.Classe"));
		assertTrue(resolveClassName.contains("br.com.geraldo.package.Classe"));
	}

}
