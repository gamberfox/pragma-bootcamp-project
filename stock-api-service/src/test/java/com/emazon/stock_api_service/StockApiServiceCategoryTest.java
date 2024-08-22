package com.emazon.stock_api_service;

import com.emazon.stock_api_service.application.handler.CategoryHandler;
import com.emazon.stock_api_service.domain.exception.ErrorType;
import com.emazon.stock_api_service.domain.exception.category.CategoryUseCaseException;
import com.emazon.stock_api_service.domain.model.Category;
import com.emazon.stock_api_service.domain.spi.ICategoryPersistencePort;
import com.emazon.stock_api_service.domain.usecase.CategoryUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class StockApiServiceCategoryTest {
    @Mock
    private ICategoryPersistencePort categoryPersistencePort;
    @InjectMocks
    private CategoryUseCase categoryUseCase;

    Category category = new Category(1L,"lego","lego description");
    Category category2 = new Category(2L,"nike","nike description");
    List<Category> categories = new ArrayList<>();
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSortCategories(){
        Category c1=new Category(1L,"a","description");
        Category c2=new Category(2L,"b","description");
        Category c3=new Category(3L,"c","description");
        List<Category> organizedCategories = Arrays.asList(c1,c2,c3);
        List<Category> categories = Arrays.asList(c1,c3,c2);
        List<Category> testList = new ArrayList<>(categories);
        categoryUseCase.sortCategories(testList,true);
        for(int i=0; i<organizedCategories.size(); i++){
            assertEquals(organizedCategories.get(i).getId(),testList.get(i).getId());
            assertEquals(organizedCategories.get(i).getName(),testList.get(i).getName());
            assertEquals(organizedCategories.get(i).getDescription(),testList.get(i).getDescription());
        }

        Collections.reverse(organizedCategories);
        testList = new ArrayList<>(categories);
        categoryUseCase.sortCategories(testList,false);
        for(int i=0; i<organizedCategories.size(); i++){
            assertEquals(organizedCategories.get(i).getId(),testList.get(i).getId());
            assertEquals(organizedCategories.get(i).getName(),testList.get(i).getName());
            assertEquals(organizedCategories.get(i).getDescription(),testList.get(i).getDescription());
        }
    }
    @Test
    void testCategoryValidateNameTooLong(){
        Category category = new Category(null, "a".repeat(51), "description");
        CategoryUseCaseException ex = assertThrows(CategoryUseCaseException.class, () -> {
            categoryUseCase.validate(category);
        });
        assertEquals(ErrorType.VALIDATION_ERROR.getDescription(),
                ex.getErrorType().getDescription());
        assertEquals("the name is too long, it cannot be longer than 50 characters"
                ,ex.getMessage());
    }
    @Test
    void testCategoryValidateNameEmpty(){
        Category category = new Category(4L, "", "description");
        CategoryUseCaseException ex = assertThrows(CategoryUseCaseException.class, () -> {
            categoryUseCase.validate(category);
        });
        assertEquals(ErrorType.VALIDATION_ERROR.getDescription(),
                ex.getErrorType().getDescription());
        assertEquals("the name cannot be empty"
                ,ex.getMessage());

    }
    @Test
    void testCategoryValidateDescriptionTooLong(){
        Category category = new Category(3L, "lego", "a".repeat(91));
        CategoryUseCaseException ex = assertThrows(CategoryUseCaseException.class, () -> {
            categoryUseCase.validate(category);
        });
        assertEquals(ErrorType.VALIDATION_ERROR.getDescription(),
                ex.getErrorType().getDescription());
        assertEquals("the description is too long, it cannot be longer than 90 characters"
                ,ex.getMessage());
    }
    @Test
    void testCategoryValidateDescriptionEmpty(){
        Category category = new Category(3L, "lego", "");
        CategoryUseCaseException ex = assertThrows(CategoryUseCaseException.class, () -> {
            categoryUseCase.validate(category);
        });
        assertEquals(ErrorType.VALIDATION_ERROR.getDescription(),
                ex.getErrorType().getDescription());
        assertEquals("the description cannot be empty"
                ,ex.getMessage());
    }
}
