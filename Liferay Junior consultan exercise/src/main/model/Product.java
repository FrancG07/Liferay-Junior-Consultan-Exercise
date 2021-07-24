package main.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {
	/**
	 * Name of the product
	 * */
	private String name;
	
	/**
	 * Price of each unit of the product
	 * */
	private BigDecimal price;
	
	/**
	 * Number of products
	 * */
	private int amount;
	
	/**
	 * Indicates if the product has the 10% tax (true means yes, false means no)
	 * */
	private boolean taxed;
	
	/**
	 * Indicates if the product is imported (true means yes, false means no)
	 * */
	private boolean imported;
	
	/**
	 * Indicates the price of the product without taxes
	 * */
	private BigDecimal untaxedPrice;
	
	/**
	 * Indicates the price of the taxes that are paid for this product
	 * */
	private BigDecimal taxesPrice;
	
	/**
	 * Constructor for the Product class.
	 * @param name Name of the product.
	 * @param price Price of each unit of the product.
	 * @param amount Number of units of the product.
	 * @param taxed Indicates if the product has the 10% for not being a book, medical product or food.
	 * @param imported Indicates if the product is imported.
	 * */
	public Product(String name, double price, int amount, boolean taxed, boolean imported) {
		this.name = name;
		this.price = new BigDecimal(price);
		this.amount = amount;
		this.taxed = taxed;
		this.imported = imported;
		this.calculateUntaxedPrice();
		this.calculateTaxesPrice();
	}
	
	/**
	 * @return The name of the product.
	 * */
	public String getName() {
		return name;
	}
	
	/**
	 * @return The price of the product.
	 * */
	public BigDecimal getPrice() {
		return price;
	}
	
	/**
	 * @return The number of units of the product.
	 * */
	public int getAmount() {
		return amount;
	}
	
	/**
	 * @return The price with taxes of the product, rounded up to 2 decimals.
	 * */
	public BigDecimal getTaxedPrice() {
		return this.taxesPrice.add(this.untaxedPrice).setScale(2, RoundingMode.HALF_EVEN);
	}
	
	/**
	 * @return The price of the taxes of the product, rounded up to 2 decimals.
	 * */
	public BigDecimal getTaxesPrice() {
		return this.taxesPrice;
	}
	
	/**
	 * @return Indicates if the product is imported (true: yes / false: no).
	 * */
	public boolean getImported() {
		return this.imported;
	}
	
	/**
	 * Calculates and sets the price of the product without taxes.
	 * */
	private void calculateUntaxedPrice() {
		this.untaxedPrice = price.multiply(new BigDecimal(this.amount));
	}
	
	/**
	 * Calculates and sets the taxes price of the product.
	 * */
	private void calculateTaxesPrice() {
		BigDecimal tax = new BigDecimal(0);
		
		if(this.imported)
			tax = tax.add(new BigDecimal(0.05));
		
		if(this.taxed)
			tax = tax.add(new BigDecimal(0.1));
		
		if(tax.doubleValue() > 0)
			this.taxesPrice = this.roundZeroFive(this.untaxedPrice.multiply(tax));
		else
			this.taxesPrice = new BigDecimal(0);
	}
	
	/**
	 * Rounds the given number up to 2 decimales and to 0.05.
	 * @param value The number to be rounded.
	 * @return The number rounded.
	 * */
	private BigDecimal roundZeroFive(BigDecimal value) {
		BigDecimal aux = new BigDecimal(0.05);
		return value.divide(aux, 0, RoundingMode.UP).multiply(aux).setScale(2, RoundingMode.HALF_EVEN);
	}
}
