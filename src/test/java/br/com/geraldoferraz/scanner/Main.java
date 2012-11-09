package br.com.geraldoferraz.scanner;

import static br.com.geraldoferraz.scanner.searches.filters.arguments.SearchArguments.annotedOnClassWith;
import static br.com.geraldoferraz.scanner.searches.filters.arguments.SearchArguments.annotedOnMethodWith;
import static br.com.geraldoferraz.scanner.searches.loaders.ClassPathLoaderTypes.folder;
import static br.com.geraldoferraz.scanner.searches.loaders.ClassPathLoaderTypes.full;
import static br.com.geraldoferraz.scanner.searches.loaders.ClassPathLoaderTypes.jar;

import java.util.Set;

import br.com.geraldoferraz.scanner.Scanner;
import br.com.geraldoferraz.scanner.searches.filters.arguments.examples.ClassLevelAnnotation;
import br.com.geraldoferraz.scanner.searches.filters.arguments.examples.MethodLevelAnnotation;

public class Main {

	public static void main(String[] args) {
		long inicio = System.currentTimeMillis();
		Scanner scan = new Scanner();
		String exactlyIn = "br.com";
		String startingIn = "br.com";

		Set<Class<?>> classes;

		scan.limitSearchingPathTo(folder());
		scan.limitSearchingPathTo(jar());
		scan.limitSearchingPathTo(full());

		scan.allClasses().startingIn("br.com.ca");
		scan.allClasses().exactlyIn("br.com");

		classes = scan.allClasses(annotedOnClassWith(ClassLevelAnnotation.class)).exactlyIn(exactlyIn);
		print(classes);
		classes = scan.allClasses(annotedOnMethodWith(MethodLevelAnnotation.class)).exactlyIn(exactlyIn);
		print(classes);
		classes = scan.allClasses(
				annotedOnClassWith(ClassLevelAnnotation.class).and(annotedOnMethodWith(MethodLevelAnnotation.class)))
				.exactlyIn(exactlyIn);
		print(classes);
		classes = scan.allClasses(
				annotedOnClassWith(ClassLevelAnnotation.class).or(annotedOnMethodWith(MethodLevelAnnotation.class)))
				.exactlyIn(exactlyIn);
		print(classes);
		classes = scan.allClasses(annotedOnClassWith(ClassLevelAnnotation.class)).startingIn(startingIn);
		print(classes);
		classes = scan.allClasses(annotedOnMethodWith(MethodLevelAnnotation.class)).startingIn(startingIn);
		print(classes);
		classes = scan.allClasses(
				annotedOnClassWith(ClassLevelAnnotation.class).and(annotedOnMethodWith(MethodLevelAnnotation.class)))
				.startingIn(startingIn);
		print(classes);

		classes = scan.allClasses(
				annotedOnClassWith(ClassLevelAnnotation.class).or(annotedOnMethodWith(MethodLevelAnnotation.class)))
				.startingIn(startingIn);
		print(classes);
		classes = scan.allClasses().exactlyIn(exactlyIn);
		print(classes);
		classes = scan.allClasses().startingIn(startingIn);
		// .and(
		print(classes);

		// CombinableArgument a4 =
		// annotedOnClassWith(ClassLevelAnnotation.class)
		// annotedOnMethodWith(MethodLevelAnnotation.class)
		// .or(
		// annotedOnFieldWith(ClassLevelAnnotation.class)
		// )
		// );
		//
		//
		// System.out.println(a4.validate(String.class));
		// System.out.println(System.getProperty("java.class.path"));
		// System.out.println(File.pathSeparator);

		long fim = System.currentTimeMillis();
		System.out.println("fim - " + (fim - inicio));

	}

	private static void print(Set<Class<?>> classes) {
		for (Class<?> clazz : classes) {
			System.out.println(clazz);
		}
		System.out.println("------------");

	}

}
