package br.com.geraldoferraz.scanyourpath.resolver;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import net.vidageek.mirror.dsl.Mirror;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

import br.com.geraldoferraz.scanyourpath.TestBase;
import br.com.geraldoferraz.scanyourpath.searches.loaders.ClassPathLoader;
import br.com.geraldoferraz.scanyourpath.searches.loaders.ClassPathLoaderTypes;
import br.com.geraldoferraz.scanyourpath.util.EmptyStringException;

public class ClassPathResolverTest extends TestBase{


	private ClassPathResolver classPathResolver;
	
	@Mock
	private ClassPathLoader classPathLoader;
	
	@Before
	public void before() {
		classPathResolver = ClassPathResolver.newInstance(new JavaClassPathResolver());
		classPathResolver.limitSearchingPathTo(ClassPathLoaderTypes.folder());
	}

	@Test(expected = EmptyStringException.class)
	public void whenGetClassesExactlyInPassingNullArgument() {
		classPathResolver.getClassesExactlyIn(null);
	}

	@Test(expected = EmptyStringException.class)
	public void whenGetClassesExactlyInPassingEmptyString() {
		classPathResolver.getClassesExactlyIn("");
	}
	
	@Test
	public void whenGetClassesExactlyInAndTheClassIsNotOnPackage() {
		Set<Class<?>> classes = classPathResolver.getClassesExactlyIn("br.com.geraldoferraz");
		assertEquals(0, classes.size());
	}
	
	@Test
	public void whenGetClassesExactlyInAndTheClassIsOnPackage() {
		Set<Class<?>> classes = classPathResolver.getClassesExactlyIn("br.com.geraldoferraz.scanyourpath.util");
		assertEquals(8, classes.size());
	}
	
	@Test
	public void whenGetClassesStartingInAndTheClassIs01() {
		Set<Class<?>> classes = classPathResolver.getClassesStartingIn("br.com.geraldoferraz.scanyourpath.resolver");
		assertEquals(11, classes.size());
	}
	
	@Test
	public void whenGetClassesStartingInAndTheClassIs02() {
		Set<Class<?>> classes = classPathResolver.getClassesStartingIn("br.com.geraldoferraz.scanyourpath.resolver.example");
		assertEquals(1, classes.size());
	}
	
	@Test
	@PrepareForTest(value={ClassPathResolver.class})
	public void whenGetClassesAndTheresAClassWithNoPackage() {
		ensureClassPathLoderLoadsAClassThatHasNoPackage();
		classPathResolver.limitSearchingPathTo(classPathLoader);
		Set<Class<?>> classes = classPathResolver.getClassesStartingIn("br.com.geraldoferraz.scanyourpath.resolver.example");
		assertEquals(0, classes.size());
	}

	private void ensureClassPathLoderLoadsAClassThatHasNoPackage() {
		Set<String> retorno = new HashSet<String>();
		retorno.add("InstallCert$SavingTrustManager");
		when(classPathLoader.resolveClassName(Mockito.anyListOf(File.class))).thenReturn(retorno);
		Mirror mirror = new Mirror();
		mirror.on(classPathResolver).set().field("classesPerPackage").withValue(new HashMap<String, ClassGrouper>());
	}

}