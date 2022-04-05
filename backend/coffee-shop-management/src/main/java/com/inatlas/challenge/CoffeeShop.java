package com.inatlas.challenge;

import java.util.ArrayList;
import java.util.List;

public class CoffeeShop {
    private List<Product> orders = new ArrayList<>();
    private List<Product> menu = new ArrayList<>();

    public void takeOrder(String product, Integer qtt) {
        this.orders.add(new Product(product, qtt));
    }

    public void addProduct(String product) {
        this.menu.add(new Product(product));
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
        addProduct("Latte");
    	addProduct("Espresso");
    	addProduct("Sandwich");
    	
    	String pN = "Product Name";
    	String pr = "Price";
    	String header = "";
        header += formatDiv("a--------------b---------c\n");
        header += formatRow("| "+pN+" |  "+pr+"  |\n");
        header += formatDiv("d--------------e---------f\n");
        
        System.out.print(header);
        
        this.menu.stream().forEach(prod -> {
        	String str1 = "|"+center(prod.getName(), pN.length()+2)+"|"+center(prod.getPrice(), pr.length()+4)+"|";
        	System.out.println(formatRow(str1));
        });

        System.out.println(formatDiv("g--------------h---------i"));
    }
    
    public static String center(String text, int len){
        if (len <= text.length())
            return text.substring(0, len);
        int before = (len - text.length())/2;
        if (before == 0)
            return String.format("%-" + len + "s", text);
        int rest = len - before;
        return String.format("%" + before + "s%-" + rest + "s", "", text);  
    }
    
    public static String formatRow(String str)
    {
        return str.replace('|', '\u2502');
    }

    public static String formatDiv(String str)
    {
        return str.replace('a', '\u250c')
                .replace('b', '\u252c')
                .replace('c', '\u2510')
                .replace('d', '\u251c')
                .replace('e', '\u253c')
                .replace('f', '\u2524')
                .replace('g', '\u2514')
                .replace('h', '\u2534')
                .replace('i', '\u2518')
                .replace('-', '\u2500');
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
