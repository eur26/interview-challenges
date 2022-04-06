package com.inatlas.challenge;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CoffeeShop {
    private List<Product> orders = new ArrayList<>();
    private List<Product> menu = new ArrayList<>();
	private double discount;
	
	private static final DecimalFormat df = new DecimalFormat("0.00");
	
	public void takeOrder(String product, Integer qtt) {
    	Product prod = findProduct(product);
    	prod.setQtt(qtt);
        this.orders.add(prod);
    }
    
    private Product findProduct(String productName) {
    	Product prod = menu.stream()
		  .filter(product -> productName.equals(product.getName()))
		  .findAny()
		  .orElse(null);
		return prod;
	}
    
	public void addProduct(String product, String price) {
        this.menu.add(new Product(product, price));
    }
	
	public Double calcularReceipt(String prom, boolean deleteQtt){
		
		Double total = null;
		switch(prom) {
			case "prom1":
				total = this.orders.stream().map(p -> {
		        	return p.getName().equals("Espresso") && deleteQtt? 
		        			Double.valueOf(p.getPrice().split("\\$")[1])*(p.getQtt()-1)
		        			:Double.valueOf(p.getPrice().split("\\$")[1])*p.getQtt();
		        }).reduce(0.0, (a, b) -> a + b);
				
				break;
			case "prom2":
				total = this.orders.stream().map(p -> {
		            double price = Double.valueOf(p.getPrice().split("\\$")[1])*p.getQtt();
		            return Double.valueOf(price-(price*discount));
		        }).reduce(0.0, (a, b) -> a + b);
				
				break;
			case "prom3":
				//latte = 3 and recalculate receipt
				Product latteProd = findProduct("Latte");
				latteProd.setPrice("$ 3");
				total = this.orders.stream().map(p -> {
		        	return Double.valueOf(p.getPrice().split("\\$")[1])*p.getQtt();
		        }).reduce(0.0, (a, b) -> a + b);
				
				break;
		}

		return total;
	}

    public void printReceipt() {
    	System.out.println("======================================");
        boolean hasMoreThanOneLatte = this.orders.stream().anyMatch(p -> p.getName().equals("Latte") && p.getQtt() > 1);
        long countLatte = this.orders.stream().filter(p -> p.getName().equals("Latte")).count();

        Product esp1;
        boolean deleteQttEspresso = false;
        
        //para que contabilice bien el total
        orders = organizeOrder(this.orders);
        
        //promotion 1: if you order 2 lattes, you will receive a free espresso
        if (hasMoreThanOneLatte || countLatte > 1) {
            esp1 = this.orders.stream().filter(p -> p.getName().equals("Espresso")).findFirst().get();
            if(esp1.getQtt().equals(1)) {
            	esp1.setDiscount(true);
            } else {
            	deleteQttEspresso=true;
            }
        }
        
        //promotion 2: 5% discount on the total, if the order have more than 8 products.
        long countProducts = this.orders.size();
        if (countProducts>8) {
        	discount = 0.05;
        }
        
        final boolean deleteQtt = deleteQttEspresso;
		Double totalProm1 = calcularReceipt("prom1", deleteQtt);
		Double totalProm2 = calcularReceipt("prom2", deleteQtt);
        
        //take cheapest promotion
        Double total = totalProm1 < totalProm2? totalProm1:totalProm2;
        
        if(total > 50.0) {
        	total = calcularReceipt("prom3", deleteQtt);
        }
        
        this.orders.stream().forEach(p -> {
        	System.out.println(p.getQtt()+" "+p);
        });
			
        System.out.println("----------------");
        System.out.println("Total: $" + df.format(total));
        System.out.println("======================================");
    }

    public void printMenu() {
        // Print whole menu
        addProduct("Latte", "$ 5.3");
    	addProduct("Espresso", "$ 4");
    	addProduct("Sandwich", "$ 10.10");
    	addProduct("Capuccino", "$ 8");
    	addProduct("Tea", "$ 6.1");
    	addProduct("Cake Slice", "$ 9");
    	addProduct("Milk", "$ 1");
    	
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
    
    public static String center(String text, int len) {
        if (len <= text.length())
            return text.substring(0, len);
        int before = (len - text.length())/2;
        if (before == 0)
            return String.format("%-" + len + "s", text);
        int rest = len - before;
        return String.format("%" + before + "s%-" + rest + "s", "", text);  
    }
    
    public static String formatRow(String str) {
        return str.replace('|', '\u2502');
    }

    public static String formatDiv(String str) {
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
}
