package com.emazon.stock_api_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StockApiServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testOfTheTest(){
		int a=2;
		int b=3;
		assertEquals(a,b-1);
	}
	@Test
	void testOfTheTest2(){
		int a=3;
		int b=3;
		assertEquals(a,b);
	}
	@Test
	void testOfTheTest3(){
		int a=2;
		int b=2;
		assertEquals(a,b);
	}
	@Test
	void testOfTheTest4(){
		int a=2;
		int c=2;
		int d=4;
		assertEquals(a+c,d);
	}
}
