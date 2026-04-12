# Truffula Notes
As part of Wave 0, please fill out notes for each of the below files. They are in the order I recommend you go through them. A few bullet points for each file is enough. You don't need to have a perfect understanding of everything, but you should work to gain an idea of how the project is structured and what you'll need to implement. Note that there are programming techniques used here that we have not covered in class! You will need to do some light research around things like enums and and `java.io.File`.

PLEASE MAKE FREQUENT COMMITS AS YOU FILL OUT THIS FILE.

## App.java
the core, where we will call our functions
runs the program with file path and flags as arguments

## ConsoleColor.java
an object that stores the console color (ANSI escape code)
we will use this by prepending it to our print statements using toString or getCode

## ColorPrinter.java / ColorPrinterTest.java
set the console color that will then be printed in the output stream (ex System.out)
print message with reset as true for default, but there is method overloading as well

## TruffulaOptions.java / TruffulaOptionsTest.java
constructs options object with file path (root) and flags as agruments
flags include showing hidden files, and using a color
can set the options explicitly or through the command line user input

## TruffulaPrinter.java / TruffulaPrinterTest.java
prints the file directories as a tree using the options object config and supports a color sequence and sorting files
hidden files are files that start w/ .

## AlphabeticalFileSorter.java