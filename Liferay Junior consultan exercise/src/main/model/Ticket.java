package main.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Ticket {
	/**
	 * List of products used to keep track of their info and to generate the ticket's info
	 * */
	private List<Product> products;
	
	/**
	 * Sum of all the products' taxes
	 * */
	private BigDecimal totalTaxPrice;
	
	/**
	 * Sum of all the products' total prices.
	 * */
	private BigDecimal totalPrice;
	
	/**
	 * Constructor for the Ticket class. It initializes its private parameters, not needing arguments.
	 * */
	public Ticket() {
		this.products = new ArrayList<Product>();
		this.totalTaxPrice = new BigDecimal(0);
		this.totalPrice = new BigDecimal(0);
	}
	
	/**
	 * Adds a product to the ticket.
	 * @param p The product to be added.
	 * */
	public void addProduct(Product p) {
		this.products.add(p);
		this.totalTaxPrice = this.totalTaxPrice.add(p.getTaxesPrice());
		this.totalPrice = this.totalPrice.add(p.getTaxedPrice());
	}
	
	/**
	 * @return The tax price of the ticket.
	 * */
	public BigDecimal getTotalTaxPrice() {
		return this.totalTaxPrice;
	}
	
	/**
	 * @return The total price of all the products of the ticket.
	 * */
	public BigDecimal getTotalPrice() {
		return this.totalPrice;
	}
	
	/**
	 * @return The list with all the products in the ticket.
	 * */
	public List<Product> getProducts() {
		return this.products;
	}
	
	/**
	 * Empties the ticket from all products.
	 * */
	public void clear() {
		this.products = new ArrayList<Product>();
		this.totalTaxPrice = new BigDecimal(0);
		this.totalPrice = new BigDecimal(0);
	}
}
