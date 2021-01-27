package com.inatlas.challenge;

public class Product {
    private String name;
    private Integer qtt;
    private boolean discount;

    public Product(String name, Integer qtt) {
        this.name = name;
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

    public String getPrice() {
        if (discount) {
            return "$ 0.0";
        }

        if ("Sandwich".equals(this.name)) {
            return "$ 10.10";
        }
        if ("Latte".equals(this.name)) {
            return "$ 5";
        }
        if ("Espresso".equals(this.name)) {
            return "$ 3";
        }
        return "";
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }
}
