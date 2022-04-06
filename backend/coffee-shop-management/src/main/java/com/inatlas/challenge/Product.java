package com.inatlas.challenge;

public class Product {
    private String name;
    private Integer qtt;
    private boolean discount;
    private String price;

    public Product(String name) {
        this.name = name;
    }
    
    public Product(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public Product(String name, Integer qtt) {
        this.name = name;
        this.qtt = qtt;
    }
    
    public Product(String name, String price, Integer qtt) {
        this.name = name;
        this.price = price;
        this.qtt = qtt;
    }

    @Override
    public String toString() {
        return name + " " + getPrice();
    }

    public Integer getQtt() {
        return qtt;
    }

    public String getName() {
        return name;
    }

    public void setPrice(String price) {
    	this.price = price;
    }
    
    public String getPrice() {
        if (discount) {
            return "$ 0.0";
        }
        
        return price;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

	public void setQtt(int qtt) {
		this.qtt = qtt;
		
	}
}
