package br.com.geraldoferraz.scanner.searches.loaders.stream;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.jar.JarEntry;

import org.junit.Before;
import org.junit.Test;

import br.com.geraldoferraz.scanner.TestBase;
import br.com.geraldoferraz.scanner.searches.loaders.LoaderUtil;
import br.com.geraldoferraz.scanner.searches.loaders.stream.MyJarInputStream;

public class MyJarInputStreamTest extends TestBase {

	private LoaderUtil util;

	private MyJarInputStream is;

	@Before
	public void before() {
		is = new MyJarInputStream();
		util = new LoaderUtil();
	}

	@Test(expected = IllegalStateException.class)
	public void whenForFileIsNull() {
		is.iterator();
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenPassingNullForFile() {
		is.forFile(null);
	}

	@Test
	public void whenJarHasNoClass() {

		Iterator<JarEntry> iterator = is.forFile(util.getEmptyJarFile()).iterator();
		int cont = 0;
		while (iterator.hasNext()) {
			cont++;
			iterator.next();
		}
		// Theres a text file that dosent count
		assertEquals(1, cont);
	}

	@Test
	public void whenJarHasClass() {

		Iterator<JarEntry> iterator = is.forFile(util.getNotEmptyJarFile()).iterator();
		int cont = 0;
		while (iterator.hasNext()) {
			cont++;
			iterator.next();
		}
		assertEquals(1, cont);
	}

}
