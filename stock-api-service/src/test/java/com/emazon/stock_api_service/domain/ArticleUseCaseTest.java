package com.emazon.stock_api_service.domain;

import com.emazon.stock_api_service.domain.exception.ArticleUseCaseException;
import com.emazon.stock_api_service.domain.exception.BrandUseCaseException;
import com.emazon.stock_api_service.domain.model.Article;
import com.emazon.stock_api_service.domain.model.Brand;
import com.emazon.stock_api_service.domain.model.Category;
import com.emazon.stock_api_service.domain.spi.IArticlePersistencePort;
import com.emazon.stock_api_service.domain.spi.IBrandPersistencePort;
import com.emazon.stock_api_service.domain.spi.ICategoryPersistencePort;
import com.emazon.stock_api_service.domain.usecase.ArticleUseCase;
import com.emazon.stock_api_service.domain.usecase.BrandUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.emazon.stock_api_service.util.ArticleConstants.*;
import static com.emazon.stock_api_service.util.BrandConstants.*;
import static com.emazon.stock_api_service.util.CategoryConstants.CATEGORY_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ArticleUseCaseTest {


    @Mock
    private IArticlePersistencePort articlePersistencePort;
    @Mock
    private ICategoryPersistencePort categoryPersistencePort;
    @Mock
    private IBrandPersistencePort brandPersistencePort;

    @InjectMocks
    private ArticleUseCase articleUseCase;
    private BrandUseCase brandUseCase;
    private Article article;
    private Brand brand;
    private Category category;
    private Category category2;
    private Category category3;
    private Category category4;
    private Category category5;
    private List<Category> categories;
    ArticleUseCaseException ex;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        brand=new Brand(1L,null,null);
        category=new Category(1L,null,null);
        article=new Article(1L,"article","description",2L,
                new BigDecimal("12.12"),brand,null);
        categories=new ArrayList<>();
        article.setBrand(brand);
        categories.add(category);
        article.setCategories(categories);
        when(articleUseCase.categoryIdExists(1L)).thenReturn(true);
        when(articleUseCase.brandIdExists(1L)).thenReturn(true);
    }

    @Test
    void testValidateBrandNotNull() {
        article.setBrand(null);
        ex = assertThrows(ArticleUseCaseException.class
                , () -> articleUseCase.validate(article));

        assertEquals(BRAND_OBLIGATORY
                ,ex.getErrorList().get(0));
        assertEquals(ex.getErrorList().size(),1);
    }
    @Test
    void testValidateBrandExists() {
        when(articleUseCase.brandIdExists(1L)).thenReturn(false);
        ex = assertThrows(ArticleUseCaseException.class
                , () -> articleUseCase.validate(article));

        assertEquals(BRAND_NOT_FOUND
                ,ex.getErrorList().get(0));
        assertEquals(ex.getErrorList().size(),1);
    }

    @Test
    void testValidateCategoriesNotNull() {
        article.setCategories(null);

        ex = assertThrows(ArticleUseCaseException.class
                , () -> articleUseCase.validate(article));

        assertEquals(MINIMUM_CATEGORY
                ,ex.getErrorList().get(0));
        assertEquals(ex.getErrorList().size(),1);
    }

    @Test
    void testValidateCategoriesRepeated() {
        categories.add(category);

        ex = assertThrows(ArticleUseCaseException.class
                , () -> articleUseCase.validate(article));


        assertEquals(CATEGORY_REPEATED
                ,ex.getErrorList().get(0));
        assertEquals(1,ex.getErrorList().size());
    }

    @Test
    void testValidateCategoriesExist() {
        when(articleUseCase.categoryIdExists(2L)).thenReturn(false);
        category2=new Category(2L,null,null);
        categories.add(category2);

        ex = assertThrows(ArticleUseCaseException.class
                , () -> articleUseCase.validate(article));

        assertEquals(CATEGORY_NOT_FOUND
                ,ex.getErrorList().get(0));
        assertEquals(ex.getErrorList().size(),1);
    }

    @Test
    void testValidateCategoriesMaximumCategoriesAssociated() {
        when(articleUseCase.categoryIdExists(2L)).thenReturn(true);
        when(articleUseCase.categoryIdExists(3L)).thenReturn(true);
        when(articleUseCase.categoryIdExists(4L)).thenReturn(true);
        when(articleUseCase.categoryIdExists(5L)).thenReturn(true);
        category2=new Category(2L,null,null);
        category3=new Category(3L,null,null);
        category4=new Category(4L,null,null);
        category5=new Category(5L,null,null);
        categories.add(category2);
        categories.add(category3);
        categories.add(category4);
        categories.add(category5);

        ex = assertThrows(ArticleUseCaseException.class
                , () -> articleUseCase.validate(article));

        assertEquals(MAXIMUM_CATEGORY
                ,ex.getErrorList().get(0));
        assertEquals(ex.getErrorList().size(),1);
    }

    @Test
    void testValidateCategoriesMinimumCategoriesAssociated() {
        categories=new ArrayList<>();
        article.setCategories(categories);
        ex = assertThrows(ArticleUseCaseException.class
                , () -> articleUseCase.validate(article));

        assertEquals(MINIMUM_CATEGORY
                ,ex.getErrorList().get(0));
        assertEquals(ex.getErrorList().size(),1);
    }
}
