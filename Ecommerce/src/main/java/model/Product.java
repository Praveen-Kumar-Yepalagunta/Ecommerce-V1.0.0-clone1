  

package model;

/**
 * User.java
 * This is a model class represents a User entity
 *
 */
public class Product {
	protected int id;
	protected String name;
	protected String description;
	protected double price;
	protected int quantity;
	
	public Product() {
	}
	
	public Product(String name, String description, Double price, int quantity) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}

	public Product(int id, String name, String description, Double price, int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	public void getDescription(String description) {
		this.description = description;
	}
	
	public double getPrice() {
		return price;
	}
	public void getPrice(Double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}