//import classes and packages  
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Polynomial { 
	//fields
	double[] coefficients;
	int[] expo;
	
	//methods
	//can have as many methods as I want as long as their arguments are different (methods overloading)
	
	public Polynomial() { // no-argument constructor that sets the polynomial to null
	    coefficients = null;
	    expo = null;
	}
	
	public Polynomial(double[] a, int[] b) { //constructor that takes an array of double as an argument and sets the coefficients accordingly
		coefficients = new double[a.length];
		expo = new int[b.length];
		for (int i = 0; i < a.length; i++) {
			coefficients[i]=a[i];
			expo[i]=b[i];
			}
	}
	
/*	public Polynomial(File file) {
        
	    int[] expoArray = new int[1000]; 
	    double[] coefficientArray = new double[1000];
        int flag=0;
        int count = 0;        
        try {
            Scanner scanner = new Scanner(file).useDelimiter("(?=[-+])|(?<=[-+])"); // Adjusted delimiter to include sign
            while (scanner.hasNext()) { 
                String token = scanner.next();  
                
                if (token.isEmpty()) {
                    continue;
                }
                double coefficient=0;
                int exponent=0;
             // Check if token is not "-" or "+" and the flag is not set
                if (!token.equals("-") && !token.equals("+") && flag == 0) {
                    // Parse coefficient and exponent
                    if (token.contains("x")) {
                        String[] parts = token.split("x");
                        if (parts.length==1) {
                        coefficient = Double.parseDouble(parts[0]);
                        exponent = 1;
                        } else if (parts.length==2) {
                            coefficient = Double.parseDouble(parts[0]);
                            exponent = Integer.parseInt(parts[1]);
                        }
                    } else {
                        coefficient = Double.parseDouble(token);
                        exponent = 0;
                    }
                }
                flag=1;
                // Check for standalone sign tokens
                if (token.equals("-") || token.equals("+")) {
                    String nextToken = scanner.hasNext() ? scanner.next().trim() : "";
                    token = token + nextToken; // Append next token to include sign
                    
                    // Parse coefficient and exponent
                    if (token.contains("x")) {
                        String[] parts = token.split("x");
                        if (parts.length==2) {
                        coefficient = Double.parseDouble(parts[0]+parts[1]);
                        exponent = 1;
                        } else if (parts.length==3) {
                            coefficient = Double.parseDouble(parts[0]+parts[1]);
                            exponent = Integer.parseInt(parts[2]);
                        }
                    } else {
                        coefficient = Double.parseDouble(token);
                        exponent = 0;
                    }
   
                    // Store the parsed coefficient and exponent
                    coefficientArray[count] = coefficient;
                    expoArray[count] = exponent;
                    count++;
                }
                
            }         	
            scanner.close(); // Close the Scanner
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred: File not found.");
            e.printStackTrace();
        }
        
        // Trim arrays to actual size
        expoArray = java.util.Arrays.copyOf(expoArray, count);
        coefficientArray = java.util.Arrays.copyOf(coefficientArray, count);
        
        // Assign local arrays to instance variables
        this.coefficients = coefficientArray;
        this.expo = expoArray;
    }
*/
	public Polynomial(File file) {
	    int[] expoArray = new int[1000];
	    double[] coefficientArray = new double[1000];
	    int count = 0;
	    try {
	        Scanner scanner = new Scanner(file).useDelimiter("(?=[-+])|(?<=[-+])"); // delimiter to include sign
	        boolean isCoefficientSet = false; // Flag to track if the coefficient has been set
	        double sign = 1; // Variable to track the sign of the coefficient

	        while (scanner.hasNext()) {
	            String token = scanner.next().trim();

	            if (token.isEmpty()) {
	                continue;
	            }

	            double coefficient = 0;
	            int exponent = 0;

	            if (token.equals("-") || token.equals("+")) {
	                sign = token.equals("-") ? -1 : 1;
	                isCoefficientSet = false;
	            } else {
	                if (token.contains("x")) {
	                    String[] parts = token.split("x");
	                    if (parts.length == 1 || parts[0].isEmpty()) {
	                        coefficient = sign * 1;
	                        exponent = parts.length == 2 ? Integer.parseInt(parts[1]) : 1;
	                    } else {
	                        coefficient = sign * Double.parseDouble(parts[0]);
	                        exponent = parts.length == 2 ? Integer.parseInt(parts[1]) : 1;
	                    }
	                } else {
	                    coefficient = sign * Double.parseDouble(token);
	                    exponent = 0;
	                }

	                // Store the parsed coefficient and exponent
	                coefficientArray[count] = coefficient;
	                expoArray[count] = exponent;
	                count++;
	                sign = 1; // Reset sign to positive for next term
	            }
	        }
	        scanner.close(); // Close the Scanner
	        
		    // Count non-zero coefficients
		    int nonZeroCount = 0;
		    for (double coeff : coefficientArray) {
		        if (coeff != 0) {
		            nonZeroCount++;
		        }
		    }

		    // Create arrays for the final non-zero coefficients and exponents
		    int[] finalExpo = new int[nonZeroCount];
		    double[] finalCoefficientsArray = new double[nonZeroCount];
		    int index = 0;
		    for (int i = 0; i < coefficientArray.length; i++) {
		        if (coefficientArray[i] != 0) {
		            finalExpo[index] = expoArray[i];
		            finalCoefficientsArray[index] = coefficientArray[i];
		            index++;
		        }
		    }
	        // Trim arrays to actual size
	        expoArray = java.util.Arrays.copyOf(finalExpo, index);
	        coefficientArray = java.util.Arrays.copyOf(finalCoefficientsArray, index);

	        // Assign local arrays to instance variables
	        this.coefficients = finalCoefficientsArray;
	        this.expo = finalExpo;

	    } catch (FileNotFoundException e) {
	        System.out.println("An error occurred: File not found.");
	        e.printStackTrace();
	    }
	}
	
	public Polynomial add(Polynomial a) {
	    // Create a set to store all unique exponents
	    Set<Integer> uniqueExponentsSet = new HashSet<>();
	    for (int exp : this.expo) {
	        uniqueExponentsSet.add(exp);
	    }
	    for (int exp : a.expo) {
	        uniqueExponentsSet.add(exp);
	    }

	    // Convert the set to an array
	    int[] uniqueExponentsArray = new int[uniqueExponentsSet.size()];
	    int index = 0;
	    for (int exp : uniqueExponentsSet) {
	        uniqueExponentsArray[index++] = exp;
	    }

	    // Sort the unique exponents
	    Arrays.sort(uniqueExponentsArray);

	    // Create an array for the coefficients of the resulting polynomial
	    double[] coefficientsArray = new double[uniqueExponentsArray.length];

	    // Fill the coefficientsArray with appropriate sums of coefficients
	    for (int i = 0; i < uniqueExponentsArray.length; i++) {
	        int exp = uniqueExponentsArray[i];
	        for (int j = 0; j < this.expo.length; j++) {
	            if (this.expo[j] == exp) {
	                coefficientsArray[i] += this.coefficients[j];
	            }
	        }
	        for (int k = 0; k < a.expo.length; k++) {
	            if (a.expo[k] == exp) {
	                coefficientsArray[i] += a.coefficients[k];
	            }
	        }
	    }

	    // Count non-zero coefficients
	    int nonZeroCount = 0;
	    for (double coeff : coefficientsArray) {
	        if (coeff != 0) {
	            nonZeroCount++;
	        }
	    }

	    // Create arrays for the final non-zero coefficients and exponents
	    int[] finalExpo = new int[nonZeroCount];
	    double[] finalCoefficientsArray = new double[nonZeroCount];
	    index = 0;
	    for (int i = 0; i < uniqueExponentsArray.length; i++) {
	        if (coefficientsArray[i] != 0) {
	            finalExpo[index] = uniqueExponentsArray[i];
	            finalCoefficientsArray[index] = coefficientsArray[i];
	            index++;
	        }
	    }

	    // Create the resulting Polynomial and return it
	    return new Polynomial(finalCoefficientsArray, finalExpo);
	}
	
	public Polynomial multiply(Polynomial a) {
	    // Maximum possible number of terms in the product polynomial
	    int maxTerms = this.expo.length * a.expo.length;

	    // Arrays to store the intermediate results
	    double[] tempCoefficients = new double[maxTerms];
	    int[] tempExponents = new int[maxTerms];
	    int tempIndex = 0;

	    // Multiply each term of this polynomial by each term of polynomial a
	    for (int i = 0; i < this.expo.length; i++) {
	        for (int j = 0; j < a.expo.length; j++) {
	            int newExponent = this.expo[i] + a.expo[j];
	            double newCoefficient = this.coefficients[i] * a.coefficients[j];

	            // Check if the new exponent already exists in the temp arrays
	            boolean found = false;
	            for (int k = 0; k < tempIndex; k++) {
	                if (tempExponents[k] == newExponent) {
	                    tempCoefficients[k] += newCoefficient;
	                    found = true;
	                    break;
	                }
	            }

	            // If the new exponent does not exist, add it to the temp arrays
	            if (!found) {
	                tempExponents[tempIndex] = newExponent;
	                tempCoefficients[tempIndex] = newCoefficient;
	                tempIndex++;
	            }
	        }
	    }

	    // Count non-zero coefficients
	    int nonZeroCount = 0;
	    for (int i = 0; i < tempIndex; i++) {
	        if (tempCoefficients[i] != 0) {
	            nonZeroCount++;
	        }
	    }

	    // Create arrays for the final non-zero coefficients and exponents
	    int[] finalExponents = new int[nonZeroCount];
	    double[] finalCoefficients = new double[nonZeroCount];
	    int index = 0;
	    for (int i = 0; i < tempIndex; i++) {
	        if (tempCoefficients[i] != 0) {
	            finalExponents[index] = tempExponents[i];
	            finalCoefficients[index] = tempCoefficients[i];
	            index++;
	        }
	    }

	    // Create the resulting Polynomial and return it
	    return new Polynomial(finalCoefficients, finalExponents);
	}
	
    public double evaluate(double x) {
        double sum = 0;
        for (int i = 0; i < coefficients.length; i++) {
            sum += this.coefficients[i] * Math.pow(x, this.expo[i]);
        }
        return sum;
    }
    
    public boolean hasRoot(double a) {
	    return evaluate(a) == 0;
	    }		
	public void saveToFile(String file) {
        StringBuilder data = new StringBuilder();
        
        for (int i = 0; i < this.expo.length; i++) {
            if (i > 0) {
                if (this.coefficients[i] >= 0) {
                    data.append(" + ");
                } else {
                    data.append(" - ");
                }
                data.append(Math.abs(this.coefficients[i]));
            } else {
                data.append(this.coefficients[i]);
            }
            data.append("x").append(this.expo[i]);
        }

        // Use FileWriter in append mode (second parameter 'true' enables appending)
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(data.toString());
            writer.write(System.lineSeparator());  // Add a newline after the polynomial
            System.out.println("Data appended to the file successfully.");
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
	
	public String toString() {
	    if (coefficients == null || expo == null) {
	        return "Polynomial is not initialized";
	    }
	    
	    StringBuilder sb = new StringBuilder();

	    for (int i = 0; i < coefficients.length; i++) {
	        if (i != 0) {
	            sb.append(coefficients[i] >= 0 ? " + " : " - ");
	        }
	        sb.append(Math.abs(coefficients[i]));
	        if (expo[i] != 0) {
	            sb.append("x");
	            if (expo[i] != 1) {
	                sb.append("^").append(expo[i]);
	            }
	        }
	    }

	    return sb.toString();
	}

	
}
