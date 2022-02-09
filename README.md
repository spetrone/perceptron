Alarm System Perceptron in Java

1. Description

This is a simple perceptron written in Java. The program does the following:
- instantiates a perceptron object
- trains the perceptron using stochastic gradient descent
  - each round of training involves 1000 epochs
- calculates and prints the average error of the training set before and after training 
- the perceptron's prediction uses a threshold of 0.5 for boolean activation --> <= 0.5, the alarm is fake, > 0.5, the alarm is real

The program is written to train a perceptron testing if an alarm is real or fake given sensor inputs.
- the perceptron has three boolean inputs (sensors) and one boolean output (alarm - real or fake)
- there are four examples in the training set 
- there is one test example inputs {0,0,0} output {0]

2. How to run
- install Java
Simply run Main.java on the JVM.

3. License
© All rights reserved


