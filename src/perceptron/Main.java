package perceptron;

/*
 * This is the main class for testing the Perceptron and Train_Test
 * classes.
 * This program instantiates a perceptron and trains it with stochastic
 * gradient descent. The program then compares error and classification results
 * before and after training the perceptron.
 */
public class Main {

	public static void main(String[] args) {
		
		//Create a Train_Test instance (also instantiates a perceptron)
		Train_Test trainTest = new Train_Test();
		double currentError = 0;
		
		//calculate the average error and test before training, for comparison
		currentError = trainTest.calculateAverageError();
		System.out.println("Error of dataset before training: " +  String.format("%6.3f", currentError));
		
		System.out.println("\n\nTesting before training...");
		trainTest.test();
		
		//First round of training
		System.out.println("\n\n-----------------------------");
		System.out.println("Now Training...");
		trainTest.train(); //stochastic gradient descent
		System.out.println("\n...trained 1000 epochs...");
		
		currentError = trainTest.calculateAverageError();
		System.out.println("\nError of dataset after training: " +  String.format("%6.2e", currentError) );
		
		System.out.println("\nTesting after training...");
		trainTest.test();
		
		
		//Second round of training
		System.out.println("\n\n-----------------------------");
		System.out.println("Now Training 2nd time...");
		trainTest.train();
		System.out.println("\n...trained 1000 epochs again...");
		
		currentError = trainTest.calculateAverageError();
		System.out.println("\nError of dataset after two trainings: " + String.format("%6.2e", currentError));
		
		System.out.println("\nTesting after two trainings...");
		trainTest.test();	
		
	}

}
