package br.com.geraldoferraz.scanyourpath;

import static br.com.geraldoferraz.scanyourpath.searches.filters.arguments.SearchArguments.annotedWith;
import static br.com.geraldoferraz.scanyourpath.searches.filters.arguments.SearchArguments.havingConstructorReceivingExactly;
import static br.com.geraldoferraz.scanyourpath.searches.filters.arguments.SearchArguments.havingMethodAnnotedWith;
import static br.com.geraldoferraz.scanyourpath.searches.filters.arguments.SearchArguments.thatNameStartsWith;
import static br.com.geraldoferraz.scanyourpath.searches.loaders.ClassPathLoaderTypes.folder;
import static br.com.geraldoferraz.scanyourpath.searches.loaders.ClassPathLoaderTypes.full;
import static br.com.geraldoferraz.scanyourpath.searches.loaders.ClassPathLoaderTypes.jar;

import java.util.Set;

import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.examples.ClassLevelAnnotation;
import br.com.geraldoferraz.scanyourpath.searches.filters.arguments.examples.MethodLevelAnnotation;

public class Main {
	
	public static void main(String[] args) {
		long inicio = System.currentTimeMillis();
		long fim = System.currentTimeMillis();
		
		Scanner scan = new Scanner();
		scan.limitSearchingPathTo(full());
//		
		Set<Class<?>> classes = scan.allClasses(thatNameStartsWith("Class").and(havingConstructorReceivingExactly(String.class,Integer.class))).startingIn("br.com.geraldoferraz.scanyourpath");
		print(classes);
//		Constructor<?>[] declaredConstructors = classes.iterator().next().getDeclaredConstructors();
//		for (Constructor<?> constructor : declaredConstructors) {
//			Parameter[] parameters = constructor.getParameters();
//			for (Parameter parameter : parameters) {
//				if(parameter.isVarArgs()){
//					System.out.println(parameter.getParameterizedType().getTypeName());
//					System.out.println(parameter.getType().getSimpleName());
//					System.out.println(parameter.getType().equals(Short[].class));
//				}
//				
//			}
//			System.out.println();
//			Class<?>[] parameterTypes = constructor.getParameterTypes();
//			for (Class<?> class1 : parameterTypes) {
//				System.out.println(class1);
//			}
//			System.out.println("-----");
//		}
		System.out.println("fim - " + (fim - inicio));
	}

	public static void main1(String[] args) {
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

		classes = scan.allClasses(annotedWith(ClassLevelAnnotation.class)).exactlyIn(exactlyIn);
		print(classes);
		classes = scan.allClasses(havingMethodAnnotedWith(MethodLevelAnnotation.class)).exactlyIn(exactlyIn);
		print(classes);
		classes = scan.allClasses(annotedWith(ClassLevelAnnotation.class).and(havingMethodAnnotedWith(MethodLevelAnnotation.class))).exactlyIn(exactlyIn);
		print(classes);
		classes = scan.allClasses(annotedWith(ClassLevelAnnotation.class).or(havingMethodAnnotedWith(MethodLevelAnnotation.class))).exactlyIn(exactlyIn);
		print(classes);
		classes = scan.allClasses(annotedWith(ClassLevelAnnotation.class)).startingIn(startingIn);
		print(classes);
		classes = scan.allClasses(havingMethodAnnotedWith(MethodLevelAnnotation.class)).startingIn(startingIn);
		print(classes);
		classes = scan.allClasses(annotedWith(ClassLevelAnnotation.class).and(havingMethodAnnotedWith(MethodLevelAnnotation.class))).startingIn(startingIn);
		print(classes);

		classes = scan.allClasses(annotedWith(ClassLevelAnnotation.class).or(havingMethodAnnotedWith(MethodLevelAnnotation.class))).startingIn(startingIn);
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
