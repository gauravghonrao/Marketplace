package com.supermarket.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.supermarket.cart.CartOperations;
import com.supermarket.cart.ICartHandler;

class TestCartOperations {

	ICartHandler cartOperations=new CartOperations();
	Map<String,Integer> cart = new HashMap<>();
	
	@Test
	void testA() {
		cart.clear();
		cart.put("A", 6);
		Integer total = cartOperations.calculateCart(cart);
		Integer expectedOutput= 130*2 ;
		Assert.assertEquals(total,expectedOutput);
		
		cart.clear();
		cart.put("A", 5);
		total = cartOperations.calculateCart(cart);
		expectedOutput= 130 + 100 ;
		Assert.assertEquals(total,expectedOutput);
		
		cart.clear();
		cart.put("A", 7);
		total = cartOperations.calculateCart(cart);
		expectedOutput= 260 + 50 ;
		Assert.assertEquals(total,expectedOutput);
	}
	
	@Test
	void testB() {
		cart.clear();
		cart.put("B", 2);
		Integer total = cartOperations.calculateCart(cart);
		Integer expectedOutput= 45;
		Assert.assertEquals(total,expectedOutput);
		
		cart.clear();
		cart.put("B", 1);
		total = cartOperations.calculateCart(cart);
		expectedOutput= 30;
		Assert.assertEquals(total,expectedOutput);
		
		cart.clear();
		cart.put("B", 3);
		total = cartOperations.calculateCart(cart);
		expectedOutput= 75;
		Assert.assertEquals(total,expectedOutput);
	}
	
	@Test
	void testC() {
		cart.clear();
		cart.put("C", 3);
		Integer total = cartOperations.calculateCart(cart);
		Integer expectedOutput= 50;
		Assert.assertEquals(expectedOutput,total);
		
		cart.clear();
		cart.put("C", 4);
		total = cartOperations.calculateCart(cart);
		expectedOutput= 70;
		Assert.assertEquals(expectedOutput,total);
		
		cart.clear();
		cart.put("C", 7);
		total = cartOperations.calculateCart(cart);
		expectedOutput= 120;
		Assert.assertEquals(expectedOutput,total);
		
		cart.clear();
		cart.put("C", 6);
		total = cartOperations.calculateCart(cart);
		expectedOutput= 100;
		Assert.assertEquals(expectedOutput,total);
		
	}
	
	@Test
	void testD() {
		cart.clear();
		cart.put("D", 1);
		Integer total = cartOperations.calculateCart(cart);
		Integer expectedOutput= 15*1;
		Assert.assertEquals(expectedOutput,total);
		
		cart.clear();
		cart.put("A", 6);
		cart.put("D", 10);
		total = cartOperations.calculateCart(cart);
		expectedOutput= 6*5 + 15*4 + 130 + 130;
		Assert.assertEquals(expectedOutput,total);
	}
	
	@Test
	void testE() {
		cart.clear();
		cart.put("E", 6);
		Integer total = cartOperations.calculateCart(cart);
		Integer expectedOutput= 6*5;
		Assert.assertEquals(expectedOutput,total);
	}

}
