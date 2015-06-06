package br.com.geraldoferraz.scanyourpath;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestBase {
	
	@Test
	public void justMavenPass(){
		assertEquals(0, 0);
	}

}
