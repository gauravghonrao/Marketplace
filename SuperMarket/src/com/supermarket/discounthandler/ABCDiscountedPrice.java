package com.supermarket.discounthandler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.supermarket.cart.Discounts;
import com.supermarket.stockDetails.StockPrice;

public class ABCDiscountedPrice implements IDiscountedPriceFactory {

	static Map<String, List<Discounts>> discounts = new HashMap<String, List<Discounts>>();
	static {
		discounts.put("A", Arrays.asList(new Discounts("A", 3, 130)));
		discounts.put("B", Arrays.asList(new Discounts("B", 2, 45)));
		discounts.put("C", Arrays.asList(new Discounts("C", 3, 50), new Discounts("C", 2, 38)));
	}

	/*
	 * Iterating Over Discounts based on ItemName and getting its minimum total if
	 * it has multiple discounts
	 */
	@Override
	public Integer getDiscountedFinalPrice(Map<String, Integer> cart, String item) {
		if (discounts.containsKey(item)) {
			List<Discounts> discountsOnItem = discounts.get(item);
			Iterator<Discounts> iterator = discountsOnItem.iterator();
			Integer min = 0;
			Integer total = 0;
			while (iterator.hasNext()) {
				Discounts discountAvailable = iterator.next();
				total = getFinalPriceForDiscountedItem(cart, discountAvailable, cart.get(item), 0);
				if (min == 0) {
					min = total;
				}
				min = min > total ? total : min;
			}
			return min;
		}
		return cart.get(item) * StockPrice.valueOf(item).getPrice();
	}

	// Getting Discounted amount for cases like :
	// example:
	// case 1:6 units to order and discount available on 3 units then process it for
	// first 3 units and again calling recursively for second chunk of 3
	// case 2:3 units to order and discount available is on 3 units then process it
	// immediately
	// case 3:3 units discount is available and 5 are the no of units ordered then
	// process it for discounted price and rest as per unit price
	// case 4:Any other scenario like 3 units discount available but want only 2
	// units then process it for unit price
	private int getFinalPriceForDiscountedItem(Map<String, Integer> cart, Discounts discountAvailable,
			Integer unitsToOrder, Integer total) {
		String item = discountAvailable.item;
		if (unitsToOrder >= discountAvailable.unit && checkDiscount(item, unitsToOrder)) {
			total = total + discountAvailable.price;
			return getFinalPriceForDiscountedItem(cart, discountAvailable, unitsToOrder - discountAvailable.unit,
					total);
		}

		if (unitsToOrder != 0 && discountAvailable.unit == unitsToOrder) {
			total = total + discountAvailable.price;
		} else if (unitsToOrder != 0) {
			total = total + unitsToOrder * StockPrice.valueOf(item).getPrice();
		}

		return total;

	}

	private Boolean checkDiscount(String item, Integer unitsToOrder) {
		return discounts.get(item).stream().map(x -> x.unit)
				.filter(x -> Math.max(unitsToOrder, x) % Math.min(unitsToOrder, x) >= 0).findAny().isPresent();

	}
}
