[![Contributor Covenant](https://img.shields.io/badge/Contributor%20Covenant-2.1-4baaaa.svg)](CODE_OF_CONDUCT.md)

# SeeYouL8r Calculator

A simple calculator app with a graphical user interface, currently for integer arithmetic only.

[![Stand With Ukraine](https://raw.githubusercontent.com/vshymanskyy/StandWithUkraine/main/banner2-direct.svg)](https://stand-with-ukraine.pp.ua)

# Installation

After cloning the repository, run
```
cd seeyoul8r
mvn package -f pom.xml
```
to build with Maven.

# Usage

To run the generated `.jar` file, use
```
java -jar target/seeyoul8r-<version>.jar
```
The on-screen buttons should be used to input the desired calculation. The input is evaluated when the "=" button is clicked. The "CLEAR" button clears the input and output ready for the next calculation. The "ANS" button copies the last evaluated answer to the input field.

Currently, the calculator observes order of operations for addition, subtraction, multiplication and division, but does not include support for brackets or exponentiation, and is limited to integer (whole-number) calculations.


# Contribute

See [guide for contributors](CONTRIBUTING).

# License

Made available under MIT License (see [LICENSE](LICENSE)).

# Acknowledgments

The approach used to parse the input and evaluate expressions with multiple binary operators while observing order of operations is based on the discussion in chapters 1-3 of [Let's Build a Compiler, by Jack Crenshaw](https://compilers.iecc.com/crenshaw/).
