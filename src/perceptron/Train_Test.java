package perceptron;
/*
 * This class performs the training of a perceptron with a given
 * training dataset. It utilize the Forward_Propogation class during 
 * each iteration. It does this through stochastic gradient descent whereby
 * each training example is forward propogated through the perceptron, and then
 * for each training example, backpropogation is done through the perceptron
 * and the weights are then updated. This is done for each training example, which
 * makes up one epoch. It is run for 1000 epochs.
 * 
 * The process is as follows for each training example:
 * (1) forward propogate using the Forward_Propogation class
 * (2) Calculate error
 * (3) Backpropogate the error
 * (4) Update weights and biases according to gradient/partial derivative for each weight
 * 
 * After the training set is run 1000 times, a test value is run to see if it correctly
 * produces the correct output (i.e. the perceptron is properly trained).
 */
public class Train_Test {
	
	public Perceptron ptrn;
	
	//instantiate a perceptron in the constructor for this
	public Train_Test() {
	this.ptrn = new Perceptron(); //will have its weights and biases initialized randomly automatically
	}
	
	
	
	//function for one training example
	private void trainExample(int eg) {
		
		double activation;
		double gradient;
		double diff;
		
		//calculate activation
		activation = ptrn.calculateOutput(eg);
		
		//calculate error(difference) for the example
		diff = ptrn.calculateDifference(eg, activation);
		
		//backpropogate the error --> calculate the gradient for the perceptron
		// = s'(activation) x derivative of the error(= difference)
		// = s'(activation) * (difference)
		gradient = dSigmoid(activation) * diff;
		
		//update weights given the gradient
		ptrn.updateWeights(eg, gradient);
		
		//update bias given the gradient
		ptrn.updateBias(gradient);
	}
	
	//function for one epoch (4 training examples)
	private void trainEpoch() {
		for(int i = 0; i < 4; i++) {
			trainExample(i);
		}
	}
	
	//function for 1000 epochs
	public void train() {
		for(int i = 0; i < 1000; i++) {
			trainEpoch();
		}
	}
	
	public double calculateAverageError() {
		double errorSum = 0;
		double aveError = 0;
		
		double guesses[] = new double[4];
		
		for (int i = 0; i < 4; i++) {
			guesses[i] = ptrn.calculateOutput(i);
		}
		
		for (int i = 0; i < 4; i++) {
			errorSum += (Math.pow((ptrn.outputs[i][0] - guesses[i]), 2))/2;
		}
		
		aveError = errorSum/4;	
		return aveError;
		
	}
	
	//function for a test
	public void test() {
		double testInput[] = {0,0,0};
		double output = ptrn.calculateOutput(testInput);
		
		System.out.println("\nTesting {0,0,0}...\nOutput is: " + output);
		
	}
	

	
	//helper function for calculating sigmoid
	private double sigmoid(double x) {
		double result;		
		result = 1/(1 + Math.exp(-x));		
		return result;
	}
	
	//helper function for calculating derivative of sigmoid
	private double dSigmoid(double x) {
		double result;
		result = sigmoid(x) * (1-sigmoid(x));
		return result;
	}

}
