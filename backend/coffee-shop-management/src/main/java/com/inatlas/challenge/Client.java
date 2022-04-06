package com.inatlas.challenge;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class Client {
	
	@Getter @Setter
	private String name;
	
	@Getter @Setter
	private List<Product> order;

    public Client(String name) {
        this.name = name;
    }
    
    public Client(String name, List<Product> order) {
        this.name = name;
        this.setOrder(order);
    }
    
    public String toString() {
    	return name;
    }
}
