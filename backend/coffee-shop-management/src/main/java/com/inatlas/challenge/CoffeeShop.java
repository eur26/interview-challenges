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
        if (hasMoreThanOneLatte || countLatte > 1) {
            Product exp1 = this.orders.stream().filter(p -> p.getName().equals("Espresso")).findFirst().get();
            exp1.setDiscount(true);
        }
        Double total = this.orders.stream().map(p -> {
            System.out.println(p);
            return Double.valueOf(p.getPrice().split("\\$")[1])*p.getQtt();
        }).reduce(0.0, (a, b) -> a + b);
        System.out.println("----------------");
        System.out.println("Total: $" + total);
        System.out.println("======================================");
    }

    public void printMenu() {
        // Print whole menu
    }
}
