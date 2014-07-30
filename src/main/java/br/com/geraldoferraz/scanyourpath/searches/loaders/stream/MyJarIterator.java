package br.com.geraldoferraz.scanyourpath.searches.loaders.stream;


import java.io.IOException;
import java.util.Iterator;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.*;

class MyJarIterator implements Iterator<JarEntry> {

	private JarEntry next;
	private final JarInputStream jarIS;

	MyJarIterator(JarInputStream jarIS) {
		argumentValidation(jarIS);
		this.jarIS = jarIS;
		try {
			next = jarIS.getNextJarEntry();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean hasNext() {
		return next != null;
	}

	@Override
	public JarEntry next() {
		argumentValidation(next);
		try {
			JarEntry returnElement = next;
			next = jarIS.getNextJarEntry();
			return returnElement;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void remove() {
		throw new RuntimeException("Not to be used");
	}

}