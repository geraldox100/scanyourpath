package br.com.geraldoferraz.scanyourpath.searches.loaders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import br.com.geraldoferraz.scanyourpath.TestBase;
import br.com.geraldoferraz.scanyourpath.searches.loaders.JarLoader;

public class JarLoaderTest extends TestBase {

	private JarLoader loader;
	private LoaderUtil util;

	@Before
	public void before() {
		loader = new JarLoader();
		util = new LoaderUtil();
	}
	@Test(expected = IllegalArgumentException.class)
	public void whenResolvingJarPassingNullArgument() {
		loader.resolveJar(null);
	}
	
	@Test
	public void whenResolveJarWithEmptyJar() {
		Set<String> classes = loader.resolveJar(util.getEmptyJarFile());
		
		assertNotNull(classes);
		assertEquals(0, classes.size());
	}
	
	@Test
	public void whenResolveJarWithNotEmptyJar() {
		Set<String> classes = loader.resolveJar(util.getNotEmptyJarFile());
		
		assertNotNull(classes);
		assertEquals(1, classes.size());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void whenResolvingClassNameNullArgument() {
		loader.resolveClassName(null);
	}

	@Test
	public void whenResolveClassNamePassingAnEmptyList() {
		Set<String> classes = loader.resolveClassName(new ArrayList<File>());
		
		assertNotNull(classes);
		assertEquals(0, classes.size());
	}
	
	@Test
	public void whenResolveClassNameWithEmptyJar() {
		Set<String> classes = loader.resolveClassName(Arrays.asList(util.getEmptyJarFile()));
		
		assertNotNull(classes);
		assertEquals(0, classes.size());
	}
	
	@Test
	public void whenResolveClassNameWithNotEmptyJar() {
		Set<String> classes = loader.resolveClassName(Arrays.asList(util.getNotEmptyJarFile()));
		
		assertNotNull(classes);
		assertEquals(1, classes.size());
	}
	
	@Test
	public void whenResolveClassName() {
		Set<String> classes = loader.resolveClassName(Arrays.asList(util.getNotEmptyJarFile(),util.getEmptyJarFile()));
		
		assertNotNull(classes);
		assertEquals(1, classes.size());
	}

}
