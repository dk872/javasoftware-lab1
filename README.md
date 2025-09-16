# Java_software-lab1

## Description
This Java project implements a simple matrix processor that performs basic matrix operations on a predefined 2D byte array. The program ensures data safety by validating the input matrix and preventing byte overflow during arithmetic operations.

## Calculation of the task variant
Number in the group list: **15**;

C5 = 15 % 5 = **0**;

C7 = 15 % 7 = **1**;

C11 = 15 % 11 = **4**.

## Features
- Matrix validation

Ensures the matrix is non-null, non-empty, and all rows have consistent lengths.

- Matrix multiplication

Multiplies each element of a 2D byte matrix by a given byte scalar, with overflow protection.

- Custom sum calculation

Computes the sum of:
  - the maximum elements in even-indexed rows;
  - the minimum elements in odd-indexed rows.

- Safe and clear error handling

Handles arithmetic and input errors with descriptive messages.

## How to run
First, clone the repository and navigate into the project directory:
```
git clone https://github.com/dk872/javasoftware-lab1
```
```
cd javasoftware-lab1
```

Compile the code:
```
javac MatrixProcessor.java
```

Run the program:
```
java MatrixProcessor
```

## Unit tests
This project includes **17** unit tests using *JUnit 5* to ensure the correctness of all core functionalities:
- Matrix validation.
- Matrix multiplication (including overflow checks).
- Custom row-based sum calculation.

### How to run tests
Make sure you have JUnit 5 configured, then run the tests with your preferred method:
  - From command line
  ```
  mvn test
  ```
  - In an IDE like IntelliJ IDEA or Eclipse using the test runner.

## Author info
Dmytro Kulyk, a student of group IM-32.
