import java.util.Arrays;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Polynomial {
	double coefficients[];
	int exponents[];
	public Polynomial() {
		coefficients = new double[] {0};
		exponents = new int[] {0};
	}
	
	public Polynomial(double arr[], int exp[]) {
		exponents = new int[exp.length];
		coefficients = new double[arr.length];
		for(int i = 0; i < arr.length; i++) {
			coefficients[i] = arr[i];
		}
		for(int j = 0; j < exp.length; j++) {
			exponents[j] = exp[j];
		}
	}
	
	public Polynomial(File f) throws IOException {
		BufferedReader input = new BufferedReader(new FileReader(f));
		String line = input.readLine();
		String[] terms;
		if (line.indexOf("+") == -1) {
			if (line.indexOf("-") == -1) {
				terms = line.split(" ");
			}
			terms = line.replace("-", " -").split(" ");
		}
		terms = line.replace("+", " +").replace("-", " -").split(" ");
		
		coefficients = new double[terms.length];
		exponents = new int[terms.length];
		for(int i = 0; i < terms.length; i++) {
			String[] s_terms = terms[i].split("x");
			coefficients[i] = Double.parseDouble(s_terms[0]);
			if (s_terms.length != 1) {
				exponents[i] = Integer.parseInt(s_terms[1]);
			}
		}
		input.close();
	}
	
	public void saveToFile(String s) throws IOException {
		String data = "";
		for(int i = 0; i < this.coefficients.length; i++) {
			String term;
			term = " +" + String.valueOf(this.coefficients[i]) + "x" + String.valueOf(this.exponents[i]);
			data += term;
		}
		data.replace(" +-", "-").replace(" +", "+").replace("x0", "");
		
		FileWriter write = new FileWriter(s);
		BufferedWriter buffer = new BufferedWriter(write);
		buffer.write(data);
		buffer.close();
	}
	
	public Polynomial add(Polynomial p) {
		
		int max_e = Math.max(Arrays.stream(p.exponents).max().getAsInt(), Arrays.stream(this.exponents).max().getAsInt());
		double new_c[] = new double[max_e+1];
		for(int i = 0; i < this.coefficients.length; i++) {
			new_c[this.exponents[i]] += this.coefficients[i];
		}
		for(int i = 0; i < p.coefficients.length; i++) {
			new_c[p.exponents[i]] += p.coefficients[i];
		}
		
		int zeroes = 0;
		for(int i = 0; i < new_c.length; i++) {
			if (new_c[i] == 0) {
				zeroes++;
			}
		}
		int r_e[] = new int[new_c.length-zeroes];
		double r_c[] = new double[new_c.length-zeroes];
		int arr_i = 0;
		for(int i = 0; i < new_c.length; i++) {
			if(new_c[i] != 0) {
				r_c[arr_i] = new_c[i];
				r_e[arr_i] = i;
				arr_i++;
			}
		}
		Polynomial result = new Polynomial(r_c, r_e);
		return result;
	}
	
	public double evaluate(double x) {
		double result = 0;
		double term = x;
		for(int i = 0; i < this.coefficients.length; i++) {
			term = Math.pow(x, this.exponents[i]) * this.coefficients[i];
			result += term;
		}
		return result;
	}
	
	public boolean hasRoot(double input) {
		return evaluate(input) == 0;
	}
	
	public Polynomial multiply(Polynomial p) {
		int max_e_p= Arrays.stream(p.exponents).max().getAsInt();
		int max_e_arr= Arrays.stream(this.exponents).max().getAsInt();
		int max_e = max_e_p + max_e_arr;
		double new_c[] = new double[max_e+1];
		double coeff = 0;
		int exp = 0;
		for(int i = 0; i < this.coefficients.length; i++) {
			for(int j = 0; j < p.coefficients.length; j++) {
				coeff = this.coefficients[i] * p.coefficients[j];
				exp = this.exponents[i] + p.exponents[i];
				new_c[exp] += coeff;
			}
		}
		
		int zeroes = 0;
		for(int i = 0; i < new_c.length; i++) {
			if (new_c[i] == 0) {
				zeroes++;
			}
		}
		int r_e[] = new int[new_c.length-zeroes];
		double r_c[] = new double[new_c.length-zeroes];
		int arr_i = 0;
		for(int i = 0; i < new_c.length; i++) {
			if(new_c[i] != 0) {
				r_c[arr_i] = new_c[i];
				r_e[arr_i] = i;
				arr_i++;
			}
		}
		Polynomial result = new Polynomial(r_c, r_e);
		return result;
	}
		
	
}
