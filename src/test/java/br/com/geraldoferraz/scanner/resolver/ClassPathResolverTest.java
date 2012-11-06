package br.com.geraldoferraz.scanner.resolver;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import br.com.geraldoferraz.scanner.TestBase;
import br.com.geraldoferraz.scanner.resolver.ClassPathResolver;
import br.com.geraldoferraz.scanner.resolver.JavaClassPathResolver;
import br.com.geraldoferraz.scanner.searches.loaders.ClassPathLoaderTypes;
import br.com.geraldoferraz.scanner.util.EmptyStringException;

public class ClassPathResolverTest extends TestBase{


	private ClassPathResolver classPathResolver;

	@Before
	public void before() {
		classPathResolver = ClassPathResolver.getInstance(new JavaClassPathResolver());
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
		Set<Class<?>> classes = classPathResolver.getClassesExactlyIn("br.com.geraldoferraz.scanner.util");
		assertEquals(6, classes.size());
	}
	
	@Test
	public void whenGetClassesStartingInAndTheClassIs01() {
		Set<Class<?>> classes = classPathResolver.getClassesStartingIn("br.com.geraldoferraz.scanner.resolver");
		assertEquals(11, classes.size());
	}
	
	@Test
	public void whenGetClassesStartingInAndTheClassIs02() {
		Set<Class<?>> classes = classPathResolver.getClassesStartingIn("br.com.geraldoferraz.scanner.resolver.example");
		assertEquals(1, classes.size());
	}

}