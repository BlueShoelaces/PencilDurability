# PencilDurability
Kata to simulate a pencil writing on paper

SETUP
Installation requirements:
  Java 1.8
  JUnit 4.12

Add the following to the CLASSPATH environment variable:
  <path-to>\PencilDurability\bin
  <path-to>\PencilDurabilityTest\bin
  <path-to>\junit.jar
  <path-to>\org.hamcrest.core.jar


PROGRAM
From the command line...

To compile the program:
  > javac -d <path-to>\PencilDurability\bin\ <path-to>\PencilDurability\src\*
  
To run the program:
  > java nicole.durability.Launcher

  
TESTS
The PencilDurabilityTest project contains packages that mirror the structure
of the PencilDurability project. Test classes are located in packages named
according to their corresponding production class packages.

From the command line...

To compile tests:
  > javac \d <path-to>\PencilDurabilityTest\bin\ <path-to>\durability\mocks\*.java
  > javac \d <path-to>\PencilDurabilityTest\bin\ <path-to>\test\TestHelper.java
  > javac \d <path-to>\PencilDurabilityTest\bin\ <path-to>\durability\*.java
  > javac \d <path-to>\PencilDurabilityTest\bin\ <path-to>\test\AllTests.java

To run a test class:
  > java org.junit.runner.JUnitCore <qualified name of test class>

Included in the PencilDurabilityTest project is a test suite called AllTests.

To run all tests in the project:
  > java org.junit.runner.JUnitCore nicole.test.AllTests

Qualified names of all included test classes:
  nicole.durability.LauncherTest
  nicole.durability.PencilTest
  nicole.durability.PaperTest
  nicole.durability.PencilWriterSingletonTest