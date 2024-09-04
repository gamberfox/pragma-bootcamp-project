package com.emazon.stock_api_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StockApiServiceCategoryTest {
    @Test
    void contextLoads() {}

    @Test
    void categoryTest(){
        int a=2;
        assertEquals(2,a);
    }
    @Test
    void getCategoryTest2() {
        int a=2;
        int b=2;
        assertEquals(4,a+b);
    }
}
