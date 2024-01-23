[![Contributor Covenant](https://img.shields.io/badge/Contributor%20Covenant-2.1-4baaaa.svg)](CODE_OF_CONDUCT.md)

# SeeYouL8r Calculator

A simple calculator app with a graphical user interface.

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
The on-screen buttons should be used to input the desired calculation. The input is evaluated when the "=" button is clicked. The "CLEAR" button clears the input and output ready for the next calculation.

Currently, the calculator only supports inputs with one mathematical operation.

# Contribute

See [guide for contributors](CONTRIBUTING).

# License

Made available under MIT License (see [LICENSE](LICENSE)).


