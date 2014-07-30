package br.com.geraldoferraz.scanyourpath;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

public class TestBase {
	
	
	@Before
	public final void testBaseBefore(){
		MockitoAnnotations.initMocks(this);
	}

}
