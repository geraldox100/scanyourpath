package br.com.geraldoferraz.scanyourpath;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

//@RunWith(MockitoJUnitRunner.class)
public class TestBase {
	
	@Before
	public final void testBaseBefore(){
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void justMavenPass(){
		assertEquals(0, 0);
	}

}
