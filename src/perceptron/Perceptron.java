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
 * and one output (alarm). All values are boolean where 1 is real 
 * and 0 is fake. 
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
	
	//initializing function, called by constructor
	public void initializePerceptron() {
		Random rand = new Random();
		weights[0] = rand.nextDouble();
		weights[1] = rand.nextDouble();
		weights[2] = rand.nextDouble();
		bias = rand.nextDouble();
	}
	
	/*
	 * This function calculates the output of a perception, given input values;
	 * It uses an index into the inputs array. 
	 * 
	 * It uses the formula 
	 * z = SUM(weights*inputs  + bias)
	 * Then uses the sigmoid activation function on z to produce the activation
	 */
	public double calculateOutput(int eg) {
		double z = 0; //acculumlator for guess output calculation
		double a;
		
		//calculate z
		for (int i = 0; i < inputs[0].length; i++) {
			z += weights[i] * inputs[eg][i] + bias;
		}
		
		//apply sigmoid to calculate the perceptron's activation:
		a = sigmoid(z);
		
		return a;		
	}
	
	/*
	 * overloaded output calc (full input given rather than index)
	 * into the output array
	 */
	public double calculateOutput(double input[]) {
		double z = 0; //acculumlator for guess output calculation
		double a;
		
		//calculate z
		for (int i = 0; i < input.length; i++) {
			z += weights[i] * input[i] + bias;
		}
		
		//apply sigmoid to calculate the perceptron's activation:
		a = sigmoid(z);
		
		return a;		
	}
	
	/*
	 * Sigmoid Function
	 */
	private double sigmoid(double x) {
		double result;		
		result = 1/(1 + Math.exp(-x));		
		return result;
	}
	
	/*
	 * This function updates the weights given a gradient for the perceptron.
	 * It uses the formula
	 * new weight = weight - input*gradient
	 * 
	 *This uses the learning rate, alpha as 0.5
	 *@param gradient - the gradient calculated for the perceptron
	 */
	public void updateWeights(int eg, double gradient) {
		
		double alpha = 0.5;
		for(int i = 0; i < weights.length; i++) {
			weights[i] -= alpha * inputs[eg][i] * gradient;
		}
	}
	
	/*
	 * Updates the bias with a given gradient.
	 * This is done with this calculation:
	 * new bias = bias - alpha * gradient
	 * 
	 * The learning rate, alpha, is set to 0.5
	 * 
	 * @param gradient - the gradient calculated for the perceptron
	 */
	public void updateBias(double gradient) {
		double alpha = 0.5;
		bias -= alpha *gradient;
	}
	
	
	/*
	 * This function calculates the error for a single example;
	 * It uses the index of an example and indexes into 
	 * the outputs array (correct outputs), comparing that
	 * to the other parameter, the guess from the perceptron
	 * 
	 * This function is used by calculateAverageError()
	 *
	 */
	public double calculateExampleError(int eg, double guess) {
		double error;
		error = (Math.pow((outputs[eg][0] - guess), 2))/2;
		return error;
	}
	
	
	
	/*
	 * This function calculates the difference between the expected/correct
	 * output of a training example and the guess from the perceptron
	 * @param eg - index into the outputs array
	 * @param guess - the perceptron's guess for a training example
	 */
	public double calculateDifference(int eg, double guess) {
		double diff;
		diff = (guess - outputs[eg][0]);
		return diff;
	}
	
}
