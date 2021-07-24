/**
 * Level 0 Junior consultan exercise
 * 
 * Exercise 1
 * 
 * In this exercise a simple program to print the details of tickets made for shopping baskets has been made.
 * The program asks the user via terminal input for the details about the products (name, price, amount, etc)
 * and it calculates the taxes that have to be paid for them, adding them to the total price of the products
 * and also showing it separately.
 * 
 * This program has been developed with Eclipse IDE for Java developers version 2019-12 (4.14.0)
 * and with Java version JavaSE-13.
 *  
 * @author Francisco García Navarrete
 * 
 * */
package main.launcher;

import main.controller.Controller;

public class Main {
	
	public static void main(String[] args) {
		Controller c = new Controller();
		c.launch();
	}
	
}