package edu.handong.database.team6;

public class Dibs {
	int id;
	int productId;
	
	public Dibs(int id, int productId) {
		this.id = id;
		this.productId = productId;
	}
	public Dibs() {
		
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
}
