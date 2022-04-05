package com.inatlas.challenge;

import org.junit.Test;

public class CoffeeShopTest {
    
    @Test
    public void testTakeMyFirstOrder(){
        CoffeeShop coffeeShop = new CoffeeShop();
        System.out.println("TEST 1");
        coffeeShop.printMenu();
        coffeeShop.takeOrder("Latte", 1);
        coffeeShop.takeOrder("Espresso", 1);
        coffeeShop.takeOrder("Sandwich", 1);
        coffeeShop.printReceipt();
        // Total should be $18.1
    }

    @Test
    public void testTakeMySecondOrder(){
        System.out.println("TEST 2");
        CoffeeShop coffeeShop = new CoffeeShop();
        coffeeShop.printMenu();
        coffeeShop.takeOrder("Latte", 2);
        coffeeShop.takeOrder("Espresso", 1);
        coffeeShop.takeOrder("Sandwich", 1);
        coffeeShop.printReceipt();
        // Total should be $15.1
    }

    @Test
    public void testTakeMyThirdOrder3(){
        System.out.println("TEST 3");
        CoffeeShop coffeeShop = new CoffeeShop();
        coffeeShop.printMenu();
        coffeeShop.takeOrder("Latte", 1);
        coffeeShop.takeOrder("Latte", 1);
        coffeeShop.takeOrder("Espresso", 1);
        coffeeShop.takeOrder("Sandwich", 1);
        coffeeShop.printReceipt();
        // Total should be $15.1
    }

    @Test
    public void testTakeMyFourthOrder(){
        System.out.println("TEST 4");
        CoffeeShop coffeeShop = new CoffeeShop();
        coffeeShop.printMenu();
        coffeeShop.takeOrder("Latte", 2);
        coffeeShop.takeOrder("Espresso", 1);
        coffeeShop.takeOrder("Espresso", 1);
        coffeeShop.takeOrder("Sandwich", 1);
        coffeeShop.printReceipt();
        // Total should be $18.1
    }
    
    @Test
    public void testTakeMyFifthOrder(){
        System.out.println("TEST 5");
        CoffeeShop coffeeShop = new CoffeeShop();
        coffeeShop.printMenu();
        coffeeShop.takeOrder("Latte", 3);
        coffeeShop.takeOrder("Espresso", 3);
        coffeeShop.takeOrder("Espresso", 2);
        coffeeShop.takeOrder("Sandwich", 2);
        coffeeShop.printReceipt();
        // Total should be $18.1
    }
}
