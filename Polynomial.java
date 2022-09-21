public class Polynomial {
	double coefficients[];
	public Polynomial() {
		coefficients = new double[] {0};
	}
	
	public Polynomial(double arr[]) {
		coefficients = new double[arr.length];
		for(int i = 0; i < arr.length; i++) {
			coefficients[i] = arr[i];
		}
	}
	
	public Polynomial add(Polynomial p) {
		int max = Math.max(p.coefficients.length, this.coefficients.length);
		double new_coefficients[] = new double[max]; 
		for(int i = 0; i < max; i++) {
			new_coefficients[i] = 0;
			if (i < p.coefficients.length) {
				new_coefficients[i] += p.coefficients[i];
			}
			if (i < this.coefficients.length) {
				new_coefficients[i] += this.coefficients[i];
			}
		}
		Polynomial result = new Polynomial(new_coefficients);
		return result;
	}
	
	public double evaluate(double x) {
		double result = 0;
		double term = x;
		for(int i = 0; i < this.coefficients.length; i++) {
			term = Math.pow(x, i) * this.coefficients[i];
			result += term;
		}
		return result;
	}
	public boolean hasRoot(double input) {
		double divisor = 1;
		
		//just a constant
		if(this.coefficients.length == 1) {
			if (this.coefficients[0] == 0.0) {
				return true;
			}
			return false;
		}
		
		//not just a constant
		double result[] = new double[this.coefficients.length];
		int length = this.coefficients.length;
		result[length-1] = this.coefficients[length-1];
		divisor = input * result[length-1];
		for(int i = length-2; i > -1; i--) {
			result[i] = this.coefficients[i] + divisor;
			divisor = input * result[i];
		}
		
		if (result[0] == 0) {
			return true;
		}
		return false;
	}
}
