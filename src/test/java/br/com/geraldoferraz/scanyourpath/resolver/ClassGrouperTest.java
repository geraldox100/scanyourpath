package br.com.geraldoferraz.scanyourpath.resolver;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import br.com.geraldoferraz.scanyourpath.TestBase;
import br.com.geraldoferraz.scanyourpath.resolver.ClassGrouper;
import br.com.geraldoferraz.scanyourpath.util.EmptyStringException;

public class ClassGrouperTest extends TestBase {
	
	private ClassGrouper grouper;
	private static final String packageName = "br.com.geraldoferraz.scanner.resolver";
	
	@Before
	public void before(){
		grouper = new ClassGrouper(packageName);
	}
	
	@Test(expected=EmptyStringException.class)
	public void whenCreatingClassGroupperPassingNullString(){
		grouper = new ClassGrouper(null);
	}
	
	@Test(expected=EmptyStringException.class)
	public void whenCreatingClassGroupperPassingEmptyString(){
		grouper = new ClassGrouper("");
	}
	
	@Test(expected=EmptyStringException.class)
	public void whenCreatingClassGroupperPassingWhiteSpaceString(){
		grouper = new ClassGrouper("  ");
	}
	
	@Test
	public void whenCreatingClassGroupperPassingValiString(){
		assertEquals(packageName, grouper.getPackageName());
	}
	
	@Test
	public void whenGettinClassesOnStringAndTheresNoClass(){
		Set<Class<?>> classesOnString = grouper.getClassesOnString();
		assertEquals(0, classesOnString.size());
	}
	
	@Test(expected=EmptyStringException.class)
	public void whenAddinClassPassingNullArgument(){
		grouper.addClass(null);
	}
	
	@Test(expected=EmptyStringException.class)
	public void whenAddinClassPassingEmptyStringArgument(){
		grouper.addClass("");
	}
	
	@Test(expected=EmptyStringException.class)
	public void whenAddinClassPassingNotEmptyStringButonlyWhiteSpaceArgument(){
		grouper.addClass("   ");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void whenAddinClassThatsNotFromPackage(){
		grouper.addClass("br.com.test.ClassGrouperTest");
	}
	
	@Test
	public void whenAddingOneClass(){
		grouper.addClass("br.com.geraldoferraz.scanner.resolver.ExampleClass01");
		Set<Class<?>> classesOnString = grouper.getClassesOnString();
		assertEquals(1, classesOnString.size());
	}
	
	@Test
	public void whenAddingTwoClass(){
		grouper.addClass("br.com.geraldoferraz.scanner.resolver.ExampleClass02");
		grouper.addClass("br.com.geraldoferraz.scanner.resolver.ExampleClass03");
		
		Set<Class<?>> classesOnString = grouper.getClassesOnString();
		assertEquals(2, classesOnString.size());
	}
	
	@Test
	public void whenAddingTheSameClassTwice(){
		grouper.addClass("br.com.geraldoferraz.scanner.resolver.ExampleClass04");
		grouper.addClass("br.com.geraldoferraz.scanner.resolver.ExampleClass04");
		
		Set<Class<?>> classesOnString = grouper.getClassesOnString();
		assertEquals(1, classesOnString.size());
	}
}
