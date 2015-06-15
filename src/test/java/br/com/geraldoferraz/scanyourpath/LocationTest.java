package br.com.geraldoferraz.scanyourpath;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.geraldoferraz.scanyourpath.resolver.ClassPathResolver;
import br.com.geraldoferraz.scanyourpath.searches.filters.SearchType;

@RunWith(MockitoJUnitRunner.class)
public class LocationTest {

	@Mock
	private SearchType searchType;
	@Mock
	private ClassPathResolver resolver;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Class<? extends Set<Class<?>>> setClass = (Class<HashSet<Class<?>>>) (Class) HashSet.class;

	private static final String PACKAGE_NAME = "package";

	private Location location;

	@Before
	public void beforeLocationTest() {
		location = new Location(searchType, resolver);
	}

	@Test
	public void whenAskingForClassesExactlyIn() {
		location.exactlyIn(PACKAGE_NAME);
		verify(resolver, times(1)).getClassesExactlyIn(PACKAGE_NAME);
		verify(searchType, times(1)).search(Matchers.any(setClass));
	}
	
	@Test
	public void whenAskingForClassesStartingIn() {
		location.startingIn(PACKAGE_NAME);
		verify(resolver, times(1)).getClassesStartingIn(PACKAGE_NAME);
		verify(searchType, times(1)).search(Matchers.any(setClass));
	}
	
	@Test
	public void whenAskingForClassesAnyWhere() {
		location.anyWhere();
		verify(resolver, times(1)).getClassesAnyWhere();
		verify(searchType, times(1)).search(Matchers.any(setClass));
	}

}
