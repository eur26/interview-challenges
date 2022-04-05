package com.inatlas.challenge;

import java.util.ArrayList;
import java.util.List;

public class CoffeeShop {
    private List<Product> orders = new ArrayList<>();

    public void takeOrder(String product, Integer qtt) {
        this.orders.add(new Product(product, qtt));
    }

    public void printReceipt() {
        System.out.println("======================================");
        boolean hasMoreThanOneLatte = this.orders.stream().anyMatch(p -> p.getName().equals("Latte") && p.getQtt() > 1);
        long countLatte = this.orders.stream().filter(p -> p.getName().equals("Latte")).count();
        
        Product esp1;
        boolean deleteQttEspresso = false;
        
        //para que contabilice bien el total
        orders = organizeOrder(this.orders);
        
        if (hasMoreThanOneLatte || countLatte > 1) {
            esp1 = this.orders.stream().filter(p -> p.getName().equals("Espresso")).findFirst().get();
            if(esp1.getQtt().equals(1)) {
            	esp1.setDiscount(true);
            } else {
            	deleteQttEspresso=true;
            }
        }
        
        
        final boolean deleteQtt = deleteQttEspresso;
        
        Double total = this.orders.stream().map(p -> {
        	System.out.println(p.getQtt()+" "+p);
        	return p.getName().equals("Espresso") && deleteQtt? 
        			Double.valueOf(p.getPrice().split("\\$")[1])*(p.getQtt()-1)
        			:Double.valueOf(p.getPrice().split("\\$")[1])*p.getQtt();
        }).reduce(0.0, (a, b) -> a + b);
        System.out.println("----------------");
        System.out.println("Total: $" + total);
        System.out.println("======================================");
    }

    public void printMenu() {
        // Print whole menu
    }
    
    private List<Product> organizeOrder(List<Product> orders2) {
    	List<Product> ordersTmp = new ArrayList<>();
		for(Product p : orders2) {
			if(ordersTmp.stream().anyMatch(o -> o.getName().equals(p.getName()))) {
				Product prodTmp = ordersTmp.stream().filter(ot -> ot.getName().equals(p.getName())).findFirst().get();
				prodTmp.setQtt(p.getQtt()+prodTmp.getQtt());
			}else {
				ordersTmp.add(p);
			}
		}
		return ordersTmp;
	}
}
