package br.com.geraldoferraz.scanner.searches.filters;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import br.com.geraldoferraz.scanner.TestBase;
import br.com.geraldoferraz.scanner.searches.filters.SearchByArguments;
import br.com.geraldoferraz.scanner.searches.filters.arguments.Argument;

public class SearchByArgumentsTest extends TestBase {

	private SearchByArguments searcher;

	@Mock
	private Argument argument;

	private Set<Class<?>> classes;

	@Before
	public void before() {
		classes = new HashSet<Class<?>>();
		classes.add(String.class);
		classes.add(Integer.class);
		classes.add(Boolean.class);
		classes.add(Long.class);

		searcher = new SearchByArguments(argument);

		when(argument.validate(String.class)).thenReturn(true);
		when(argument.validate(Long.class)).thenReturn(true);

	}

	@Test(expected = IllegalArgumentException.class)
	public void whenSearchWithNullArgument() {
		searcher.search(null);
	}

	@Test
	public void whenSearch() {
		Set<Class<?>> search = searcher.search(classes);

		assertEquals(2, search.size());

		assertTrue(search.contains(String.class));
		assertTrue(search.contains(Long.class));

		assertThat(search, not(sameInstance(classes)));
		assertThat(search, not(equalTo(classes)));

	}

	@Test
	public void whenSearchWithEmptySet() {
		classes.clear();
		Set<Class<?>> search = searcher.search(classes);

		assertEquals(0, search.size());
	}
}
