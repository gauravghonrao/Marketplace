package com.supermarket.discounthandler;

import java.util.Map;

public interface IDiscountedPriceFactory {

	Integer getDiscountedFinalPrice(Map<String, Integer> cart, String item);
}
