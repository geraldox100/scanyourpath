package br.com.geraldoferraz.scanyourpath.searches.loaders.stream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import org.junit.Test;
import org.mockito.Mock;

import br.com.geraldoferraz.scanyourpath.TestBase;
import br.com.geraldoferraz.scanyourpath.searches.loaders.stream.MyJarIterator;

public class MyJarIteratorTest extends TestBase {

	private MyJarIterator myJarIterator;

	@Mock
	private JarInputStream jarInputStream;

	@Test(expected = IllegalArgumentException.class)
	public void whenCreatingAMyJarIteratorPassingNullArgument() {
		myJarIterator = new MyJarIterator(null);
	}

	@Test(expected = RuntimeException.class)
	public void whenJarInputStreamThrowsIOException() throws IOException {
		when(jarInputStream.getNextJarEntry()).thenThrow(new IOException());
		myJarIterator = new MyJarIterator(jarInputStream);
	}

	@Test(expected = RuntimeException.class)
	public void whenAskForNextElementAndThereIsNoElement() throws IOException {
		when(jarInputStream.getNextJarEntry()).thenReturn(null);
		myJarIterator = new MyJarIterator(jarInputStream);
		myJarIterator.next();
	}

	@Test
	public void whenAskIfHasNextAndThereIsNoElement() throws IOException {
		when(jarInputStream.getNextJarEntry()).thenReturn(null);
		myJarIterator = new MyJarIterator(jarInputStream);

		assertFalse(myJarIterator.hasNext());
	}

	@Test
	public void whenAskIfHasNextAndThereIsOneElement() throws IOException {
		when(jarInputStream.getNextJarEntry()).thenReturn(mock(JarEntry.class));

		myJarIterator = new MyJarIterator(jarInputStream);

		assertTrue(myJarIterator.hasNext());
		assertNotNull(myJarIterator.next());
	}

	@Test(expected = RuntimeException.class)
	public void whenRemoveAElement() {

		new MyJarIterator(jarInputStream).remove();

	}

}
