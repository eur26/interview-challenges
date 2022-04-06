package com.inatlas.challenge;

import lombok.Getter;
import lombok.Setter;

public class Product {
	
	@Getter
    private String name;
    
    @Getter @Setter
    private Integer qtt;
    
    @Setter
    private boolean discount;
    
    @Setter
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
    
    public String getPrice() {
        if (discount) {
            return "$ 0.0";
        }
        return price;
    }
}
