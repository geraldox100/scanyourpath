ScanYourPath, a DSL java library for classpath scanning.
==


I went through many situations where I needed to search classes dynamically, Hibernate and GAE are good examples.  
I looked up and found some APIs, but didn't like how they were implemented. So I thought it would be cool to make my own library that would help in this task.

Usage
--

Maven dependency

To use module on Maven-based projects, use following dependency:

````
<dependency>
	<groupId>br.com.geraldoferraz</groupId>
	<artifactId>scanyourpath</artifactId>
	<version>1.1.1</version>
</dependency>
````
(or whatever version is most up-to-date at the moment)


Getting Started
--

You first have to obtain an instance of the scanner:
````
Scanner scan = new Scanner();
````

Before we start, inform the package you want to scan. You can do this by telling the starting package
or the exact package you want to scan.
````
Set<Class<?>> classes1 = scan.allClasses().startingIn("br.com.geraldoferraz");
Set<Class<?>> classes2 = scan.allClasses().exactlyIn("br.com.geraldoferraz");
````
Unlike "exactlyIn", the "startingIn" method will scan all "inner" packages.

The previous example will return all the classes on your class path, but that may not be so intresting.
Lets try a another example, say you want to find all classes annotated with @Entity:
````
Set<Class<?>> classes = scan.allClasses(annotatedOnClassWith(Entity.class)).exactlyIn("br.com.domain");
````
The method "annotatedOnClassWith" returns an instance of the Argument interface.
````
public interface Argument{

  boolean validate(Class<?> clazz);

}
````
Although the api has a certain amount of Argument implementations, you may need something more specific,
so you may create your own implementation of Argument.

You may also combine Arguments to make a more specific search. Say you want to find all classes with the @Entity
that has not implemented "equals" method yet:

````

Set<Class<?>>  classes = scan.allClasses(
    annotatedOnClassWith(Entity.class).and(not(hasMethod("equals")))
    ).exactlyIn("br.com.domain");
````
If you have a large amount of classes, scanning may take a while.
By default it will scan only your source folder classes. 
Some times you may need to scan jars as well, so you can configure the searching path:
````
scan.limitSearchingPathTo(folder());
scan.limitSearchingPathTo(jar());
scan.limitSearchingPathTo(full());
````

Creating your own Argument
--
Creating your own argument is quite simple, just implement the Argument interface.
Lets create an argument that checks if the class name ends with "Test"

First create a class that implements the Argument interface.
````
public class NameEndsWithTest implements Argument {
  @Override
    public boolean validate(Class<?> clazz) {
        return false;
    }
}
````
You have to implement the "validate" method according to what you want to check.

````
public boolean validate(Class<?> clazz) {
  return clazz.getSimpleName().endsWith("Test");
}
````
To use your argument just pass it to the scanner.
````
Set<Class<?>> classes = scan.allClasses(new NameEndsWithTest()).exactlyIn("br.com.test");
````

Gaining Fluency
==
Doing this "new NameEndsWithTest()" is ok, but not very fluent... let's try something else. 
Maybe if we extract it to a method call.
````
Set<Class<?>> classes = scan.allClasses(thatNameEndsWithTest()).exactlyIn("br.com.test");

public Argument thatNameEndsWithTest(){
    return new NameEndsWithTest();
}

````
Now that's better, but we can do more. 
Maybe if we pass the name we want our class to end with as an argument.
````
Set<Class<?>> classes = scan.allClasses(thatNameEndsWith("Test")).exactlyIn("br.com.test");

public Argument thatNameEndsWith(String name){
    return new NameEndsWithTest(name);
}
````
Let's modify our "NameEndsWithTest" class. 
Rename it to "NameEndsWith" and next, create a constructor that recives the String we want to check.
````
public class NameEndsWith implements Argument {
    private String endsWith;
    public NameEndsWith(String endsWith){
        this.endsWith = endsWith;
    }
````
Now use the "endsWith" on the "validate" method.
````
public boolean validate(Class<?> clazz) {
  return clazz.getSimpleName().endsWith(endsWith);
}
````
Advanced Features
==
You can also combine arguments in order to 	obtain a more specific search.
````
Set<Class<?>> classes = scan.allClasses(annotatedWith(Entity.class).and(havingMethodWithName("equals"))).startingIn("br.com.test");
````
You will notice that in our previus example (thatNameEndsWith) this is not possible due to the Argument interface is too simple and has no way to allow the combination to other arguments. But not to worry, ScanYourPath has a solution. All you have to do is decorate your own argument with a CombinableArgument class.
````
Set<Class<?>> classes = scan.allClasses(thatNameEndsWith("Test").or(thatNameStartsWith("Test"))).exactlyIn("br.com.test");

public CombinableArgument thatNameEndsWith(String name){
    return new CombinableArgument(new NameEndsWithTest(name));
}

public CombinableArgument thatNameStartsWith(String name){
    return new CombinableArgument(new NameStartsWithTest(name));
}
````
The CombinableArgument class allows you to combine arguments using the logical operators "And" and "Or".

Known problems
--
Due to how Maven manages classpath you may have to add to your mave-surefire plugin the following configuration:
````
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-surefire-plugin</artifactId>
	<configuration>
		<useSystemClassLoader>false</useSystemClassLoader>
	</configuration>
</plugin>
````

for more information read <a href="http://maven.apache.org/surefire/maven-surefire-plugin/examples/class-loading.html">this</a>.
