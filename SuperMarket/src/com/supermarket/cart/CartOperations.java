package com.supermarket.cart;

import java.util.Iterator;
import java.util.Map;

import com.supermarket.discounthandler.ABCDiscountedPrice;
import com.supermarket.discounthandler.DDiscountedPrice;
import com.supermarket.discounthandler.EDiscountedPrice;
import com.supermarket.discounthandler.IDiscountedPriceFactory;
import com.supermarket.stockDetails.StockPrice;

public class CartOperations implements ICartHandler {

	IDiscountedPriceFactory discountedPriceFactory;

	@Override
	public Integer calculateCart(Map<String, Integer> cart) {

		Iterator<Map.Entry<String, Integer>> iterator = cart.entrySet().iterator();
		Integer total = 0;
		while (iterator.hasNext()) {
			Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) iterator.next();
			System.out
			.println("ItemName         Item Quantity      Unit Price       W/O Discount      Discounted Price");

			System.out.println(entry.getKey() + "                   " + entry.getValue() + "                    "
					+ StockPrice.valueOf(entry.getKey()).getPrice() + "           "
					+ StockPrice.valueOf(entry.getKey()).getPrice() * entry.getValue() + "              "
					+ getDiscountedPrice(cart, entry.getKey()));
			total += getDiscountedPrice(cart, entry.getKey());
		}
		return total;
	}

	private int getDiscountedPrice(Map<String, Integer> cart, String item) {

		switch (item) {
		case "A":
		case "B":
		case "C":
			discountedPriceFactory=new ABCDiscountedPrice();
			break;
		case "D":
			discountedPriceFactory=new DDiscountedPrice();
			break;
		case "E":
			discountedPriceFactory=new EDiscountedPrice();
			break;
		default:
			return 0;
		}
		return discountedPriceFactory.getDiscountedFinalPrice(cart, item);
	}

	
}
