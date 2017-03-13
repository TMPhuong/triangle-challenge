# triangle-challenge

## Description
Write a program that will determine the type of a triangle. It should take the lengths of the triangle's three sides as input, and return whether the triangle is equilateral, isosceles or scalene.

## How it work
- By default, program will take arguments as triangle's three sides. If there is no arguments, it will prompt user to manually input three side with space seperated and ended by pressing enter. Instruction will be printed out in console.
- Input will then be validated, any errors will be printed out in console with as much details as possible. If input is valid, triangle's type will be printed out and program will be terminated.
- Program except side length in integer, double or a mixed of them. Side is then compared by their mathematics value so that triangle with side length of 5.0 5 5.0000 is equilateral

## Requirements
- JDK8
- maven 3

## How to run
- Clone this repo into your machine
- Go to triangle-challenge. There are two ways to run program

#### Build a standalone package
  
```
> mvn install
> java -jar target/triangle-challenge-1.0-SNAPSHOT-jar-with-dependencies.jar [side...]
```

#### Execute main class from exec-maven-plugin
  
```
> mvn compile
> mvn exec:java [-Dexec.args=side...]
```
