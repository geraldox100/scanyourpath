package br.com.geraldoferraz.scanyourpath;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import br.com.geraldoferraz.scanyourpath.Scanner;
import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.Argument;
import br.com.geraldoferraz.scanyourpath.searches.loaders.ClassPathLoader;

public class ScannerTest {
	
	@Mock
	private Argument argument;
	@Mock
	private ClassPathLoader classPathLoader;

	private Scanner scanner;

	@Before
	public void before() {
		scanner = new Scanner();
	}
	
	@Test
	public void instanceTest(){
		Scanner shouldBeSame01 = scanner.allClasses(argument);
		Scanner shouldBeSame02 = scanner.allClasses();
		Scanner shouldBeSame03 = scanner.limitSearchingPathTo(classPathLoader);
		
		assertThat(shouldBeSame01, sameInstance(scanner));
		assertThat(shouldBeSame01, equalTo(scanner));
		
		assertThat(shouldBeSame02, sameInstance(scanner));
		assertThat(shouldBeSame02, equalTo(scanner));
		
		assertThat(shouldBeSame03, sameInstance(scanner));
		assertThat(shouldBeSame03, equalTo(scanner));
	}
	
	

}
