package perceptron;
/*
 * This class performs the training of a perceptron with a given
 * training dataset. It utilize the Forward_Propogation class during 
 * each iteration. It does this through stochastic gradient descent whereby
 * each training example is fed forward through the perceptron, and then
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
 * For testing - the result (classification) is based on a threshold of 0.5 for the output.
 * For outputs <= 0.5, the classification is that the alarm is fake; for outputs > 0.5, the
 * classification is that the alarm is real.
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
		
	
	
	/*
	 * function for one training example where the activation and error 
	 * are calculated and then the error is backpropogated through the percepton. 
	 * That is used to calculate the gradient. 
	 * The gradient is used to update the weights and biases for each training example
	 * (stochastic gradient descent)
	 */
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
	
	/*
	 * function for training one epoch (4 training examples)
	 * with stochastic gradient descent
	 */
	private void trainEpoch() {
		for(int i = 0; i < 4; i++) {
			trainExample(i);
		}
	}
	
	
	/*
	 * function for training 1000 epochs
	 */
	public void train() {
		for(int i = 0; i < 1000; i++) {
			trainEpoch();
		}
	}
	
	
	/*
	 * Function for calculating the error for a whole training set;
	 * Could be used for batch gradient descent but currently used
	 * for comparing the changes in error between rounds of training.
	 */
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
	
	
	/*
	 * function for testing one test example
	 * For this program, for simplicity, the test is hard-coded 
	 */
	public void test() {
		double testInput[] = {0,0,0};
		double output = ptrn.calculateOutput(testInput);
		
		System.out.println("\nTesting {0,0,0}...\nOutput is: " + String.format("%6.4f", output));
		
		//calculate boolean outcome value with a threshold of 0.5
		if (output <= 0.5) {
			System.out.println("Result: Alarm is fake");
		}
		else { //> 0.5
			System.out.println("Result: Alarm is real");
		}
		
		
		
	}
	

	
	/*
	 * function for calculating the sigmoid function
	 */
	private double sigmoid(double x) {
		double result;		
		result = 1/(1 + Math.exp(-x));		
		return result;
	}
	
	/*
	 *  function for calculating derivative of the sigmoid function
	 */
	private double dSigmoid(double x) {
		double result;
		result = sigmoid(x) * (1-sigmoid(x));
		return result;
	}

}
