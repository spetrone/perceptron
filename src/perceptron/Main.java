package perceptron;

public class Main {

	public static void main(String[] args) {
		
		//Create a Train_Test instance (also instantiates a perceptron)
		Train_Test trainTest = new Train_Test();
		double currentError = 0;
		
		//calculate the average error;
		currentError = trainTest.calculateAverageError();
		System.out.println("Error of dataset before training: " + currentError);
		
		System.out.println("\n\nTesting before training...");
		trainTest.test();
		
		System.out.println("\n\nNow Training...");
		trainTest.train();
		System.out.println("\n...trained 1000 epochs...");
		
		currentError = trainTest.calculateAverageError();
		System.out.println("\nError of dataset after training: " + currentError);
		
		System.out.println("\n\nTesting after training...");
		trainTest.test();
		
		
		System.out.println("\n\nNow Training 2nd time...");
		trainTest.train();
		System.out.println("\n...trained 1000 epochs again...");
		
		currentError = trainTest.calculateAverageError();
		System.out.println("\nError of dataset after two traininsg: " + currentError);
		
		System.out.println("\n\nTesting after two trainings...");
		trainTest.test();
		
		


	}

}
