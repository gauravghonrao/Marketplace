package com.supermarket.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.supermarket.cart.CartOperations;
import com.supermarket.cart.ICartHandler;

public class Supermarket {

	public static void main(String[] args) {

		ICartHandler cartHandler = new CartOperations();
		Map<String, Integer> cart ;
		
		try (Scanner in = new Scanner(System.in)) {
			System.out.println(
					"Please Enter your Item Name and Number of Items you would like to buy (press X if yould like to exit):");
			cart = new HashMap<>();
			String[] order;
			while (true) {
				order = in.nextLine().split(" ");
				if (order[0].equalsIgnoreCase("X")) {
					break;
				}
				String itemName = order[0];
				int quantity = Integer.parseInt(order[1]);
				System.out.println("You entered Item:" + itemName + " No of Items:" + quantity);
				if (cart.containsKey(itemName)) {
					cart.put(itemName, cart.get(itemName) + quantity);
				}else {
					cart.put(itemName, quantity);
				}
			}
		}
		
		cartHandler.calculateCart(cart);

	}
}
