package com.inatlas.challenge;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CoffeeShop {
	private static List<Product> menu = new ArrayList<>();
	private double discount;

	private static final DecimalFormat df = new DecimalFormat("0.00");

	public static void main(String[] args) {
		CoffeeShop coffeeShop = new CoffeeShop();
		System.out.println("---CAFETERIA---");
		coffeeShop.printMenu();
		
		//CLIENTE JAMES
		Client cliente = new Client("James");
		List<Product> order = new ArrayList<>();
		takeOrder(cliente, order, "Latte", 4);
		takeOrder(cliente, order, "Latte", 4);
		takeOrder(cliente, order, "Espresso", 3);
		takeOrder(cliente, order, "Sandwich", 1);
		try {
			cliente.setOrder(order);
		} catch (Exception e) {
			System.err.println("Error" + e);
		}
		System.out.println(cliente);
		coffeeShop.printReceipt(order);
		
		//CLIENTE LAURA
		Client cliente2 = new Client("Laura");
		order = new ArrayList<>();
		takeOrder(cliente2, order, "Tea", 4);
		takeOrder(cliente2, order, "Sandwich", 1);
		try {
			cliente2.setOrder(order);
		} catch (Exception e) {
			System.err.println("Error" + e);
		}
		System.out.println(cliente2);
		coffeeShop.printReceipt(order);
	}

	public static void takeOrder(Client cliente, List<Product> order, String product, Integer qtt) {
		Product prod = findProduct(product);
		prod.setQtt(qtt);
		order.add(prod);
		cliente.setOrder(order);
	}

	private static Product findProduct(String productName) {
		Product prod = menu.stream().filter(product -> productName.equals(product.getName())).findAny().orElse(null);
		return prod;
	}

	private void addProduct(String product, String price) {
		menu.add(new Product(product, price));
	}

	private Double calcularReceipt(List<Product> order, String prom, boolean deleteQtt) {

		Double total = null;
		switch (prom) {
		case "prom1":
			total = order.stream().map(p -> {
				return p.getName().equals("Espresso") && deleteQtt
						? Double.valueOf(p.getPrice().split("\\$")[1]) * (p.getQtt() - 1)
						: Double.valueOf(p.getPrice().split("\\$")[1]) * p.getQtt();
			}).reduce(0.0, (a, b) -> a + b);

			break;
		case "prom2":
			total = order.stream().map(p -> {
				double price = Double.valueOf(p.getPrice().split("\\$")[1]) * p.getQtt();
				return Double.valueOf(price - (price * discount));
			}).reduce(0.0, (a, b) -> a + b);

			break;
		case "prom3":
			// latte = 3 and recalculate receipt
			Product latteProd = findProduct("Latte");
			latteProd.setPrice("$ 3");
			total = order.stream().map(p -> {
				return Double.valueOf(p.getPrice().split("\\$")[1]) * p.getQtt();
			}).reduce(0.0, (a, b) -> a + b);

			break;
		}

		return total;
	}

	public void printReceipt(List<Product> order) {
		System.out.println("======================================");
		boolean hasMoreThanOneLatte = order.stream().anyMatch(p -> p.getName().equals("Latte") && p.getQtt() > 1);
		long countLatte = order.stream().filter(p -> p.getName().equals("Latte")).count();

		Product esp1;
		boolean deleteQttEspresso = false;

		// para que contabilice bien el total
		order = organizeOrder(order);

		// promotion 1: if you order 2 lattes, you will receive a free espresso
		if (hasMoreThanOneLatte || countLatte > 1) {
			esp1 = order.stream().filter(p -> p.getName().equals("Espresso")).findFirst().get();
			if (esp1.getQtt().equals(1)) {
				esp1.setDiscount(true);
			} else {
				deleteQttEspresso = true;
			}
		}

		// promotion 2: 5% discount on the total, if the order have more than 8
		// products.
		long countProducts = order.size();
		if (countProducts > 8) {
			discount = 0.05;
		}

		final boolean deleteQtt = deleteQttEspresso;
		Double totalProm1 = calcularReceipt(order, "prom1", deleteQtt);
		Double totalProm2 = calcularReceipt(order, "prom2", deleteQtt);

		// take cheapest promotion
		Double total = totalProm1 < totalProm2 ? totalProm1 : totalProm2;

		if (total > 50.0) {
			total = calcularReceipt(order, "prom3", deleteQtt);
		}

		order.stream().forEach(p -> {
			System.out.println(p.getQtt() + " " + p);
		});

		System.out.println("----------------");
		System.out.println("Total: $" + df.format(total));
		System.out.println("======================================");
	}

	public void printMenu() {
		// Print whole menu
		menu = new ArrayList<>();
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
		header += formatRow("| " + pN + " |  " + pr + "  |\n");
		header += formatDiv("d--------------e---------f\n");

		System.out.print(header);

		menu.stream().forEach(prod -> {
			String str1 = "|" + center(prod.getName(), pN.length() + 2) + "|" + center(prod.getPrice(), pr.length() + 4)
					+ "|";
			System.out.println(formatRow(str1));
		});

		System.out.println(formatDiv("g--------------h---------i"));
	}

	private List<Product> organizeOrder(List<Product> orders2) {
		List<Product> ordersTmp = new ArrayList<>();
		for (Product p : orders2) {
			if (ordersTmp.stream().anyMatch(o -> o.getName().equals(p.getName()))) {
				Product prodTmp = ordersTmp.stream().filter(ot -> ot.getName().equals(p.getName())).findFirst().get();
				prodTmp.setQtt(p.getQtt() + prodTmp.getQtt());
			} else {
				ordersTmp.add(p);
			}
		}
		return ordersTmp;
	}

	private static String center(String text, int len) {
		if (len <= text.length())
			return text.substring(0, len);
		int before = (len - text.length()) / 2;
		if (before == 0)
			return String.format("%-" + len + "s", text);
		int rest = len - before;
		return String.format("%" + before + "s%-" + rest + "s", "", text);
	}

	private static String formatRow(String str) {
		return str.replace('|', '\u2502');
	}

	private static String formatDiv(String str) {
		return str.replace('a', '\u250c').replace('b', '\u252c').replace('c', '\u2510').replace('d', '\u251c')
				.replace('e', '\u253c').replace('f', '\u2524').replace('g', '\u2514').replace('h', '\u2534')
				.replace('i', '\u2518').replace('-', '\u2500');
	}
}
