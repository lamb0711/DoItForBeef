package edu.handong.database.team6;

import java.security.Timestamp;

public class Product {
	private int productId;
    private String productName;
    private int price;
    private String type;
    private String brand;
    private int inventory;
    private Timestamp date;
    private String mall;

    public Product(int productId, String productName, int price, String type, String brand, int inventory, Timestamp date, String mall) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.type = type;
        this.brand = brand;
        this.inventory = inventory;
        this.date = date;
        this.mall = mall;
    }
    
    public int getProdutId() {
        return productId;
    }

    public void setProdutId(int productId) {
        this.productId = productId;
    }
    
    public String getProductName() {
        return productName;
    }

    public void setProductName(String address) {
        this.productName = productName;
    }
    
    public int getPrice() {
        return price;
    }

    public void price(int price) {
        this.price = price;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    public int getInventory() {
        return inventory;
    }

    public void inventory(int inventory) {
        this.inventory = inventory;
    }
    
}
