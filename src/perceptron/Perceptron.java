package perceptron;

import java.util.Random;

/*
 * This class performs the forward propogation of training 
 * data through a perceptron by doing the following
 * (1) Defines the input as a 2d double array double[][] inputs
 * (2) Defines the output as a 2d double array double[][] outputs
 * (3) Generates initial weights and biases using Math.random()
 * (4) Calculates the output of a given set of inputs
 * 
 * The perceptron has three inputs (sensor 1, sensor2, sensor 3) 
 * and one output (alarm). All values are boolean where 1 is on 
 * and 0 is off. 
 */

public class Perceptron {

	double[][] inputs = { {0,0,1}, {1,1,1}, {1,0,1}, {0,1,1}};
	double[][] outputs = { {0}, {1}, {1}, {0} };
	double[] weights = new double[3];
	double bias;
	
	//constructor for perceptron that will automatically initialize the perceptron
	public Perceptron() {
		initializePerceptron();
	}
	
	public void initializePerceptron() {
		Random rand = new Random();
		weights[0] = rand.nextDouble();
		weights[1] = rand.nextDouble();
		weights[2] = rand.nextDouble();
		bias = rand.nextDouble();
	}
	
	public double calculateOutput(int eg) {
		double z = 0; //acculumlator for guess output calculation
		double a;
		
		//calculate z
		for (int i = 0; i < 3; i++) {
			z += weights[i] * inputs[eg][i] + bias;
		}
		
		//apply sigmoid to calculate the perceptron's activation:
		a = sigmoid(z);
		
		return a;		
	}
	
	//overloaded output calc (full input given rather than index)
	public double calculateOutput(double input[]) {
		double z = 0; //acculumlator for guess output calculation
		double a;
		
		//calculate z
		for (int i = 0; i < 3; i++) {
			z += weights[i] * input[i] + bias;
		}
		
		//apply sigmoid to calculate the perceptron's activation:
		a = sigmoid(z);
		
		return a;		
	}
	
	private double sigmoid(double x) {
		double result;		
		result = 1/(1 + Math.exp(-x));		
		return result;
	}
	
	
	public void updateWeights(int eg, double gradient) {
		
		for(int i = 0; i < 3; i++) {
			weights[i] -= inputs[eg][i]*gradient;
		}
	}
	
	
	public void updateBias(double gradient) {
		bias -= gradient;
	}
	
	
	public double calculateExampleError(int eg, double guess) {
		double error;
		error = (Math.pow((outputs[eg][0] - guess), 2))/2;
		return error;
	}
	
	
	
	
	
	public double calculateDifference(int eg, double guess) {
		double diff;
		diff = (guess - outputs[eg][0]);
		return diff;
	}
	
}
