
public class Polynomial { 
	//fields
	double[] coefficients;
	
	//methods
	//can have as many methods as I want as long as their arguments are different (methods overloading)
	
	public Polynomial() { // no-argument constructor that sets the polynomial to zero
		
		coefficients = new double[1];
		coefficients[0]=0;
	}
	
	public Polynomial(double[] a) { //constructor that takes an array of double as an argument and sets the coefficients accordingly
		coefficients = new double[a.length];
		for (int i = 0; i < a.length; i++) {
			coefficients[i]=a[i];
			}
	}
	public Polynomial add(Polynomial a) {
		
	    int minLength = Math.min(coefficients.length, a.coefficients.length);
	    int maxLength = Math.max(coefficients.length, a.coefficients.length);
	    
	    double[] array = new double[maxLength];
	    
		for (int i = 0; i < minLength; i++) {
			array[i] = coefficients[i] + a.coefficients[i];
			}
		
		double[] longerCo;
		if (coefficients.length == maxLength) {
		    longerCo= coefficients;
		} else {
		    longerCo= a.coefficients;
		}

		for (int i=minLength; i < maxLength; i++) {
			array[i] = longerCo[i];
			}
		
		Polynomial result = new Polynomial(array);
		return result;
	}
	
	public double evaluate(double x) {
		double sum=0;
		sum += coefficients[0];
		for (int i = 1; i < coefficients.length; i++) {
			sum += (coefficients[i] * (Math.pow(x,i)));
			}
		return sum;
	}
	
	public boolean hasRoot(double a) {
		
		return evaluate(a) == 0;
	}
	
}
