package com.supermarket.stockDetails;

public enum StockPrice {

	A("A", 50), B("B", 30), C("C", 20), D("D", 15), E("E", 5);

	private final String item;

	private final Integer price;

	StockPrice(String item, Integer price) {
		this.item = item;
		this.price = price;
	}

	public Integer getPrice() {
		return price;
	}

	public String getItem() {
		return item;
	}
}
