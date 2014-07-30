package br.com.geraldoferraz.scanyourpath.searches.loaders.stream;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import static br.com.geraldoferraz.scanyourpath.util.ValidationUtil.*;

public class MyJarInputStream implements Iterable<JarEntry> {

	private JarInputStream jarInputStream;

	public MyJarInputStream forFile(File file) {
		argumentValidation(file);
		try {
			jarInputStream = new JarInputStream(new FileInputStream(file));
			return this;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Iterator<JarEntry> iterator() {
		stateValidation(jarInputStream);
		return new MyJarIterator(jarInputStream);
	}

}