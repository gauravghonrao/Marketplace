package com.supermarket.discounthandler;

import java.util.Map;

import com.supermarket.stockDetails.StockPrice;

/*ItemSpecific discounts in their own class*/
public class DDiscountedPrice implements IDiscountedPriceFactory {

	@Override
	public Integer getDiscountedFinalPrice(Map<String, Integer> cart, String item) {
		Integer total = 0;
		if (cart.containsKey("A")) {
			Integer totalDItems = cart.get("D");
			if (cart.get("A") <= totalDItems) {
				total = cart.get("A") * 5;
				totalDItems = totalDItems - cart.get("A");
				total = total + (totalDItems * 15);
			} else {
				total = totalDItems * 5;
			}
		} else {
			total = cart.get(item) * StockPrice.D.getPrice();
		}
		return total;
	}

}
