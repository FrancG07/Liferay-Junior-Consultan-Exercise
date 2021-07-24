package main.controller;

import java.util.Scanner;

import main.model.Product;
import main.model.Ticket;

public class Controller {
	/**
	 * Object Ticket to keep track of the products added to it and its prices (tax and total)
	 * */
	private Ticket t;
	/**
	 * Scanner used to get input from the terminal
	 * */
	private Scanner in;
	
	/**
	 * Constructor for the Controller class. It initializes its private parameters, not needing arguments.
	 * */
	public Controller() {
		this.t = new Ticket();
		this.in = new Scanner(System.in);
	}
	
	/**
	 * Launches the program to read the info needed to generate the ticket's costs.
	 * */
	public void launch() {
		while(readTicket()) {
			this.t.clear();
			System.out.println();
		}
	}
	
	/**
	 * Asks for the info needed to generate a ticket and prints the result through the terminal.
	 * @return A boolean indicating if the user wants to generate another ticket (true: yes / false: no)
	 * */
	private boolean readTicket() {
		while(this.readProduct()) {
			System.out.println();
		}
		
		for(Product p : this.t.getProducts()) {
			String imported = ": ";
			if(p.getImported())
				imported = " importado: ";
			
			System.out.println(p.getAmount() + " " + p.getName() + imported + p.getTaxedPrice() + " €");
		}
		
		System.out.println("Impuestos sobre las ventas: " + t.getTotalTaxPrice() + " €");
		System.out.println("Total: " + t.getTotalPrice() + " €");
		
		
		System.out.print("¿Desea crear otro ticket? (s/n) ");
		
		return this.checkYesOrNo();
	}
	
	/**
	 * Asks for the info needed to create a product and also checks that it is valid.
	 * @return A boolean indicating if the user wants to create another product (true: yes / false: no)
	 * */
	private boolean readProduct() {
		System.out.print("Introduce el nombre del producto: ");
		String pName = in.nextLine();
		
		System.out.print("Introduce la cantidad de productos: ");
		int pAmount;
		while(true) {
			try {
				pAmount = in.nextInt();
				
				if(pAmount <= 0)
					throw new Exception();
				
				break;
			} catch(Exception e) {
				in.nextLine();
				System.out.println("Introduce una cantidad válida");
			}
		}
		
		System.out.print("Introduce el precio del producto: ");
		double pPrice;
		while(true) {
			try {
				pPrice = in.nextDouble();
				
				if(pPrice <= 0 || String.valueOf(pPrice).split("\\.")[1].length() > 2)
					throw new Exception();
				
				break;
			} catch(Exception e) {
				in.nextLine();
				System.out.println("Introduce un precio válido");
			}
		}
		in.nextLine();
		
		System.out.print("¿El producto es un alimento, un libro o un producto médico? (s/n) ");
		boolean taxed = !this.checkYesOrNo();
		
		System.out.print("¿El producto es importado? (s/n) ");
		boolean imported = this.checkYesOrNo();
		
		this.t.addProduct(new Product(pName, pPrice, pAmount, taxed, imported));
		
		System.out.print("¿Desea añadir otro producto? (s/n) ");
	
		return this.checkYesOrNo();
	}
	
	/**
	 * Asks for the user to input a s or a n to indicate yes or no respectively.
	 * @return A boolean indicating if the user has input a s or a n (true: s / false: n)
	 * */
	private boolean checkYesOrNo() {
		String aux = in.nextLine();
		
		while(!(aux.equals("s") || aux.equals("n"))) {
			System.out.println("Por favor, introduce s para indicar que sí o n para indicar que no");
			aux = in.nextLine();
		}
		
		return aux.equals("s");
	}
}
