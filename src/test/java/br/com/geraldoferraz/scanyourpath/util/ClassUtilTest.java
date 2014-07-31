package br.com.geraldoferraz.scanyourpath.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.jar.JarEntry;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import br.com.geraldoferraz.scanyourpath.TestBase;
import br.com.geraldoferraz.scanyourpath.util.ClassUtil;
import br.com.geraldoferraz.scanyourpath.util.EmptyStringException;

public class ClassUtilTest extends TestBase {

	private String separator = System.getProperty("file.separator");
	@Mock
	private File notFile;

	@Mock
	private File notClassFile;

	@Mock
	private File classFile;

	private File nullFile = null;

	@Mock
	private JarEntry notClassJarEntry;

	@Mock
	private JarEntry classJarEntry;

	private JarEntry nullJarEntry = null;

	@Before
	public void before() {
		when(notFile.isFile()).thenReturn(false);
		when(notFile.getAbsolutePath()).thenReturn(separator+"home"+separator+"classes"+separator+"geraldo");

		when(notClassFile.isFile()).thenReturn(true);
		when(notClassFile.getName()).thenReturn("File.txt");
		when(notClassFile.getAbsolutePath()).thenReturn(""+separator+"home"+separator+"classes"+separator+"geraldo"+separator+"File.txt");

		when(classFile.isFile()).thenReturn(true);
		when(classFile.getName()).thenReturn("File.class");
		when(classFile.getAbsolutePath()).thenReturn(separator+"home"+separator+"classes"+separator+"geraldo"+separator+"File.class");

		when(notClassJarEntry.getName()).thenReturn("home"+separator+"classes"+separator+"geraldo"+separator+"File.txt");
		when(classJarEntry.getName()).thenReturn("home"+separator+"classes"+separator+"geraldo"+separator+"File.class");
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenAskIfIsClassPassingNullArgument() {
		ClassUtil.isClass(nullFile);
	}

	@Test
	public void whenAskIfIsClassAndItsNotAFile() {
		assertFalse(ClassUtil.isClass(notFile));
	}

	@Test
	public void whenAskIfIsClassAndItsNotAClassFile() {
		assertFalse(ClassUtil.isClass(notClassFile));
	}

	@Test
	public void whenAskIfIsClassAndItsAClassFile() {
		assertTrue(ClassUtil.isClass(classFile));
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenAskIfIsClassPassingNullJarEntry() {
		ClassUtil.isClass(nullJarEntry);
	}

	@Test
	public void whenAskIfIsClassAndItsNotAClassJarEntry() {
		assertFalse(ClassUtil.isClass(notClassJarEntry));
	}

	@Test
	public void whenAskIfIsClassAndItsAClassJarEntry() {
		assertTrue(ClassUtil.isClass(classJarEntry));
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenGetFullQualifiedNamePassingNullArgument() {
		ClassUtil.getFullQualifiedName(nullFile);
	}

	@Test
	public void whenGetFullQualifiedNamePassingAFolder() {
		assertEquals("home.classes.geraldo",ClassUtil.getFullQualifiedName(notFile));
	}

	@Test
	public void whenGetFullQualifiedNamePassingANonClassFile() {
		assertEquals("home.classes.geraldo.File.txt",ClassUtil.getFullQualifiedName(notClassFile));
	}

	@Test
	public void whenGetFullQualifiedNamePassingAClassFile() {
		assertEquals("home.classes.geraldo.File",ClassUtil.getFullQualifiedName(classFile));
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenGetFullQualifiedNamePassingNullJarEntry() {
		ClassUtil.getFullQualifiedName(nullJarEntry);
	}

	@Test
	public void whenGetFullQualifiedNamePassingANonClassJarEntry() {
		assertEquals("home.classes.geraldo.File.txt",ClassUtil.getFullQualifiedName(notClassJarEntry));
	}

	@Test
	public void whenGetFullQualifiedNamePassingAClassJarEntry() {
		assertEquals("home.classes.geraldo.File",ClassUtil.getFullQualifiedName(classJarEntry));
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenEextractPackageNameFromFullQualifiedNamePassingNullArgument() {
		ClassUtil.extractPackageNameFromFullQualifiedName(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void whenExtractPackageNmaeFromClassThatDoesNotHavePackage(){
		ClassUtil.extractPackageNameFromFullQualifiedName("InstallCert$SavingTrustManager");
	}

	@Test
	public void whenEextractPackageNameFromFullQualifiedName() {
		assertEquals("home.classes.geraldo", ClassUtil.extractPackageNameFromFullQualifiedName("home.classes.geraldo.File"));
	}

	@Test(expected = EmptyStringException.class)
	public void whenRemoveDirectoryPassingNullName() {
		ClassUtil.removeDirectory(null, "bla");
	}

	@Test(expected = EmptyStringException.class)
	public void whenRemoveDirectoryPassingNullDirectoryToRemove() {
		ClassUtil.removeDirectory("bla", null);
	}

	@Test(expected = EmptyStringException.class)
	public void whenRemoveDirectoryPassingEmptyName() {
		ClassUtil.removeDirectory("", "bla");
	}

	@Test(expected = EmptyStringException.class)
	public void whenRemoveDirectoryPassingEmptyDirectoryToRemove() {
		ClassUtil.removeDirectory("bla", "");
	}

	@Test
	public void whenRemoveDirectory() {
		assertEquals("File", ClassUtil.removeDirectory("home.classes.geraldo.File", separator+"home"+separator+"classes"+separator+"geraldo"));
	}
	
	@Test
	public void whenClassDoesNotHavePackage(){
		
	}

}
