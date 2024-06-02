import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Driver {
    public static void main(String[] args) {
        // Creating polynomials using different constructors
        Polynomial p1 = new Polynomial();
        double[] coefficients2 = {3.0, 2.0, 1.0};
        int[] exponents2 = {2, 1, 0};
        Polynomial p2 = new Polynomial(coefficients2, exponents2);

        File inputFile = new File("example.txt");
        Polynomial p3 = null;
        try {
            p3 = new Polynomial(inputFile);
        } catch (Exception e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }

        // Display the polynomials
        System.out.println("Polynomial p1: " + p1);
        System.out.println("Polynomial p2: " + p2);
        if (p3 != null) {
            System.out.println("Polynomial p3 (from file): " + p3);
        }

        // Adding two polynomials
        Polynomial p4 = p2.add(p2);
        System.out.println("p1 + p2 = " + p4);

        // Multiplying two polynomials
        Polynomial p5 = p2.multiply(p2);
        System.out.println("p2 * p2 = " + p5);

        // Evaluating a polynomial at x = 2
        double evaluationResult = p2.evaluate(2.0);
        System.out.println("p2 evaluated at x = 2: " + evaluationResult);

        // Checking if a value is a root of the polynomial
        boolean hasRoot = p2.hasRoot(-1.0);
        System.out.println("Does p2 have root at x = -1? " + hasRoot);

        // Saving a polynomial to a file
        String outputFile = "empty.txt";
        p2.saveToFile(outputFile);
    }
}
