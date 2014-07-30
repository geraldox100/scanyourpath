package br.com.geraldoferraz.scanyourpath.searches.filters;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import br.com.geraldoferraz.scanyourpath.searches.filters.SearchAll;


public class SearchAllTest {
	
	private SearchAll searcher;
	private Set<Class<?>> classes;
	
	
	@Before
	public void before(){
		searcher = new SearchAll();
		
		classes = new HashSet<Class<?>>();
		
		classes.add(String.class);
		classes.add(Integer.class);
		classes.add(Boolean.class);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void whenSearchWithNullArgument(){
		searcher.search(null);
	}
	
	@Test
	public void whenSearch(){
		Set<Class<?>> result = searcher.search(classes);
		
		assertEquals(3,result.size());
		
		assertTrue(result.contains(String.class));
		assertTrue(result.contains(Integer.class));
		assertTrue(result.contains(Boolean.class));
	}

}
