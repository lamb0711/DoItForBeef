package edu.handong.database.team6;

public class ShoppingCart {
	int id;
	int productId;
	int number;
	int validity;
	
	public ShoppingCart(int id, int productId,int number, int validity) {
		this.id = id;
		this.productId = productId;
		this.number = number;
		this.validity = validity;
	}
	public ShoppingCart() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id=id;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId=productId;
	}
	public int getNumber() {
		return number;
	}
	public void setCartNumber(int number) {
		this.number=number;
	}
	public int getValidity() {
		return validity;
	}
	public void setCartValidity(int validity) {
		this.validity=validity;
	}
}
