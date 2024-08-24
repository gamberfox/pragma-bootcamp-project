package com.emazon.stock_api_service.domain;

import com.emazon.stock_api_service.domain.exception.CategoryUseCaseException;
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

import static com.emazon.stock_api_service.util.Constants.MAXIMUM_CATEGORY_DESCRIPTION_LENGTH;
import static com.emazon.stock_api_service.util.Constants.MAXIMUM_CATEGORY_NAME_LENGTH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CategoryUseCaseTest {
    @Mock
    private ICategoryPersistencePort categoryPersistencePort;
    @InjectMocks
    private CategoryUseCase categoryUseCase;

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
        CategoryUseCaseException ex = assertThrows(CategoryUseCaseException.class
                , () -> categoryUseCase.validate(category));
        assertEquals("the name cannot be longer than "
                        +MAXIMUM_CATEGORY_NAME_LENGTH
                        +" characters"
                ,ex.getMessage());
    }
    @Test
    void testCategoryValidateNameEmpty(){
        Category category = new Category(4L, "", "description");
        CategoryUseCaseException ex = assertThrows(CategoryUseCaseException.class
                , () -> categoryUseCase.validate(category));
        assertEquals("the name cannot be empty"
                ,ex.getMessage());

    }
    @Test
    void testCategoryValidateDescriptionTooLong(){
        Category category = new Category(3L, "lego", "a".repeat(91));
        CategoryUseCaseException ex = assertThrows(CategoryUseCaseException.class
                , () -> categoryUseCase.validate(category));
        assertEquals("the description cannot be longer than "
                        +MAXIMUM_CATEGORY_DESCRIPTION_LENGTH
                        +" 90 characters"
                ,ex.getMessage());
    }
    @Test
    void testCategoryValidateDescriptionEmpty(){
        Category category = new Category(3L, "lego", "");
        CategoryUseCaseException ex = assertThrows(CategoryUseCaseException.class, () -> categoryUseCase.validate(category));
        assertEquals("the description cannot be empty"
                ,ex.getMessage());
    }
}
