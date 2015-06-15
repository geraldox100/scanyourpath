package br.com.geraldoferraz.scanyourpath;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.Argument;
import br.com.geraldoferraz.scanyourpath.searches.loaders.ClassPathLoader;

@RunWith(MockitoJUnitRunner.class)
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
	public void instanceTest() {
		Location shouldBeSame01 = scanner.allClasses(argument);
		Location shouldBeSame02 = scanner.allClasses();

		assertThat(shouldBeSame01, not(equalTo(null)));
		assertThat(shouldBeSame02, not(equalTo(null)));

	}

}
