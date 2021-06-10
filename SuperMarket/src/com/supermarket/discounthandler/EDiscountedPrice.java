package com.supermarket.discounthandler;

import java.util.Map;

import com.supermarket.stockDetails.StockPrice;

/*ItemSpecific discounts in their own class*/
public class EDiscountedPrice implements IDiscountedPriceFactory {

	@Override
	public Integer getDiscountedFinalPrice(Map<String, Integer> cart, String item) {
		return cart.get(item) * StockPrice.E.getPrice();
	}

}
