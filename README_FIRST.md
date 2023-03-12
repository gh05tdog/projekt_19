# General
This project can be used as a starting project for your own projects which have a JavaFX GUI.

It contains all the necessary libraries to run Cucumber tests, JUnit 5 tests, and JUnit 4 tests. In addition, it contains the reference to the Mockito libraries.

It is a good idea to change the name of the project. Don't forget to also change the name in the `pom.xml` file. 

# Java version
If you use a different version than Java 11, then change the `maven.compiler.source` and `maven.compiler.target` properties in the `pom.xml` file. This should only be needed if you want to use Java 8. The setting Java 11 is compatible with all installed JDK's of version 11 or higher.

# Running the tests
The tests can be run through Maven, e.g., `mvn clean test`, Eclipse (select the project and then run as JUnit test), and ItelliJ (select the project and then run all tests). 

In case of Eclipse, if the run configuration for the test is set to JUnit 4, all the Cucumber tests and all the JUnit 4 tests are run, but not the JUnit 5 tests. If the setting is set to JUnit 5, then all the tests are run.

# JavaFX
This project contains also the references to JavaFX in the pom.xml file. Make sure that the main class is correctly set in the pom.xml file in the JavaFX plugin. 

To run the main class execute: `mvn javafx:run`. 

The project won't run in Eclipse and IntelliJ. Both IDE's need to add the JavaFX libraries as command line options to their run configurations.  For example `--module-path /path/to/javafx-sdk-17.0.1/lib --add-modules javafx.controls,javafx.fxml`. Note that this requires to have the JavaFX SDK downloaded.

More information can be found at [https://openjfx.io/openjfx-docs/](https://openjfx.io/openjfx-docs/).

## Not using JavaFX
If you don't need JavaFX, you can remove the javafx entries from the pom.xml file.

# Misc
- Be sure to remove unnecessary tests, packages, classes, etc. from the example project in your final project. 
- Remember to create a `README.txt` (plain text) or a `README.md` (using Markdown) file that explains how to build and run the tests and the application and any necessary information to use the information, e.g., a short user manual and a description of any preset logins with password, if required.