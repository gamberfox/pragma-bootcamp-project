package com.emazon.stock_api_service;

import com.emazon.stock_api_service.application.handler.CategoryHandler;
import com.emazon.stock_api_service.domain.model.Category;
import com.emazon.stock_api_service.domain.spi.ICategoryPersistencePort;
import com.emazon.stock_api_service.domain.usecase.CategoryUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class StockApiServiceCategoryTest {
    @Mock
    private ICategoryPersistencePort categoryPersistencePort;
    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    Category category = new Category(1L,"lego","lego description");
    Category category2 = new Category(2L,"nike","nike description");
    List<Category> categories = new ArrayList<>();
    @Test
    void contextLoads() {}
    @Test
    void testCreateCategory(){
        Long categoryId = 1L;
        Long categoryId2 = 2L;
        when(categoryPersistencePort.getCategory(categoryId)).thenReturn(category);
        when(categoryPersistencePort.getCategory(categoryId2)).thenReturn(category2);

        Category result = categoryUseCase.getCategory(categoryId);
        assertEquals(categoryId, result.getId());
        assertEquals("nike", result.getName());
        Category result2 = categoryUseCase.getCategory(categoryId);
        assertEquals(categoryId, result.getId());
        assertEquals("lego", result.getName());
        verify(categoryPersistencePort, times(2)).getCategory(categoryId);
    }
    @Test
    void testGetCategory() {
        Long categoryId = 1L;
        Long categoryId2 = 2L;
        when(categoryPersistencePort.getCategory(categoryId)).thenReturn(category);
        when(categoryPersistencePort.getCategory(categoryId2)).thenReturn(category2);

        Category result = categoryUseCase.getCategory(categoryId);
        assertEquals(categoryId, result.getId());
        assertEquals("nike", result.getName());
        assertEquals("nike description", result.getDescription());
        Category result2 = categoryUseCase.getCategory(categoryId);
        assertEquals(categoryId2, result2.getId());
        assertEquals("lego", result2.getName());
        assertEquals("lego description", result2.getDescription());
        verify(categoryPersistencePort, times(2)).getCategory(categoryId);
    }

    @Test
    void testGetCategories(){
        categories.add(category);
        categories.add(category2);
        when(categoryPersistencePort.getCategories(true,0,10)).thenReturn(categories);
        
    }
}
