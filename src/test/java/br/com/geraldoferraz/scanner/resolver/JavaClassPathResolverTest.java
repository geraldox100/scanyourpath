package br.com.geraldoferraz.scanner.resolver;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import br.com.geraldoferraz.scanner.TestBase;
import br.com.geraldoferraz.scanner.resolver.JavaClassPathResolver;

public class JavaClassPathResolverTest extends TestBase {

	private JavaClassPathResolver javaClassPathResolver;

	@Test
	public void whenCreateJavaClassPathResolver() {
		javaClassPathResolver = new JavaClassPathResolver();
		assertThat(javaClassPathResolver.getClassPathUrl(), notNullValue());
	}

}
