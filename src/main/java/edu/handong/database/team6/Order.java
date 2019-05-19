package edu.handong.database.team6;

public class Order {
	
	private int id;
	private int productId;
	private int number;
	private String address;
	private String phone;

    public Order(int id, int productId, int number, String address, String phone) {
        this.id = id;
        this.productId = productId;
        this.number = number;
        this.address = address;
        this.phone = phone;
    }
    public Order() {
    	
    }
    
    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }
    
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
