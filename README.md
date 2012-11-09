ScanYourPath
==
--

Scan Your Path is a DSL java library for classpath scanning.

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
Lets try a another example, say you want to find all classes annoted with @Entity:
````
Set<Class<?>> classes = scan.allClasses(annotedOnClassWith(Entity.class)).exactlyIn("br.com.domain");
````
The method "annotedOnClassWith" returns an instance of the Argument interface.
````
public interface Argument{

  boolean validate(Class<?> clazz);

}
````
Although the framework has a certain amount of Argument implementations, you may need something more specific,
so you may create your own implementation of Argument.

You may also combine Arguments to make a more specific search. Say you want to find all classes with the @Entity
that has not implemented "equals" method yet:

````

Set<Class<?>>  classes = scan.allClasses(
    annotedOnClassWith(Entity.class).and(not(hasMethod("equals")))
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

How to create your own Argument
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

public Argument thatNameEndsWithTest(String namme){
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
