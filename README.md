# Polynomial Class

The `Polynomial` class is a Java implementation of polynomial manipulation, including addition, multiplication, evaluation, and saving to a file.

## Class Overview

- **Fields**:
  - `coefficients`: Array of double representing the coefficients of the polynomial terms.
  - `expo`: Array of int representing the exponents of the polynomial terms.

- **Constructors**:
  - `Polynomial()`: No-argument constructor that sets the polynomial to null.
  - `Polynomial(double[] a, int[] b)`: Constructor that takes arrays of doubles and ints as arguments and sets the coefficients accordingly.
  - `Polynomial(File file)`: Constructor that reads polynomial data from a file and initializes the coefficients and exponents.

- **Methods**:
  - `add(Polynomial a)`: Method to add two polynomials.
  - `multiply(Polynomial a)`: Method to multiply two polynomials.
  - `evaluate(double x)`: Method to evaluate the polynomial for a given value of x.
  - `hasRoot(double a)`: Method to check if a given value is a root of the polynomial.
  - `saveToFile(String file)`: Method to save the polynomial to a file.
  - `toString()`: Method to represent the polynomial as a string.

## Usage

1. **Creating a Polynomial**:
   ```java
   double[] coefficients = {1, -2, 3}; // Example coefficients
   int[] exponents = {2, 1, 0}; // Corresponding exponents
   Polynomial polynomial = new Polynomial(coefficients, exponents);
   ```

2. **Adding Two Polynomials**:
   ```java
   Polynomial result = polynomial1.add(polynomial2);
   ```

3. **Multiplying Two Polynomials**:
   ```java
   Polynomial result = polynomial1.multiply(polynomial2);
   ```

4. **Evaluating the Polynomial**:
   ```java
   double x = 2.5; // Example value of x
   double result = polynomial.evaluate(x);
   ```

5. **Saving Polynomial to File**:
   ```java
   polynomial.saveToFile("output.txt");
   ```

## Requirements

- Java Development Kit (JDK) installed on your system.
- Basic understanding of Java programming concepts.
