package com.inatlas.challenge;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CoffeeShopTest {
    
    @Test
    public void testTakeMyFirstOrder(){
    	System.out.println("TEST 1");
        CoffeeShop coffeeShop = new CoffeeShop();
        coffeeShop.printMenu();
        Client cliente = new Client("James");
		List<Product> order = new ArrayList<>();
		coffeeShop.takeOrder(cliente, order, "Latte", 1);
		coffeeShop.takeOrder(cliente, order, "Espresso", 1);
		coffeeShop.takeOrder(cliente, order, "Sandwich", 1);
		try {
			cliente.setOrder(order);
		} catch (Exception e) {
			System.err.println("Error" + e);
		}
		System.out.println(cliente);
		coffeeShop.printReceipt(order);
        // Total should be $18.1
		
    }

    @Test
    public void testTakeMySecondOrder(){
        System.out.println("TEST 2");
        CoffeeShop coffeeShop = new CoffeeShop();
        coffeeShop.printMenu();
        Client cliente = new Client("Laura");
        List<Product> order = new ArrayList<>();
        coffeeShop.takeOrder(cliente, order, "Latte", 2);
        coffeeShop.takeOrder(cliente, order, "Espresso", 1);
        coffeeShop.takeOrder(cliente, order, "Sandwich", 1);
        coffeeShop.printReceipt(order);
        // Total should be $15.1
    }

    @Test
    public void testTakeMyThirdOrder3(){
        System.out.println("TEST 3");
        CoffeeShop coffeeShop = new CoffeeShop();
        coffeeShop.printMenu();
        Client cliente = new Client("Laura");
        List<Product> order = new ArrayList<>();
        CoffeeShop.takeOrder(cliente, order, "Latte", 1);
        CoffeeShop.takeOrder(cliente, order, "Latte", 1);
        coffeeShop.takeOrder(cliente, order, "Espresso", 1);
        coffeeShop.takeOrder(cliente, order, "Sandwich", 1);
        coffeeShop.printReceipt(order);
        // Total should be $15.1
    }

    @Test
    public void testTakeMyFourthOrder(){
        System.out.println("TEST 4");
        CoffeeShop coffeeShop = new CoffeeShop();
        coffeeShop.printMenu();
        Client cliente = new Client("Laura");
        List<Product> order = new ArrayList<>();
        coffeeShop.takeOrder(cliente, order, "Latte", 2);
        coffeeShop.takeOrder(cliente, order, "Espresso", 1);
        coffeeShop.takeOrder(cliente, order, "Espresso", 1);
        coffeeShop.takeOrder(cliente, order, "Sandwich", 1);
        coffeeShop.printReceipt(order);
        // Total should be $18.1
    }
    
    @Test
    public void testTakeMyFifthOrder(){
        System.out.println("TEST 5");
        CoffeeShop coffeeShop = new CoffeeShop();
        coffeeShop.printMenu();
        Client cliente = new Client("Laura");
        List<Product> order = new ArrayList<>();
        coffeeShop.takeOrder(cliente, order, "Latte", 3);
        coffeeShop.takeOrder(cliente, order, "Espresso", 3);
        coffeeShop.takeOrder(cliente, order, "Espresso", 2);
        coffeeShop.takeOrder(cliente, order, "Sandwich", 5);
        coffeeShop.printReceipt(order);
        // Total should be $18.1
    }
}
