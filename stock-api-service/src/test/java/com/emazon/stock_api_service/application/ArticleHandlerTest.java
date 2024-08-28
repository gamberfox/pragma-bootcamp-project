package com.emazon.stock_api_service.application;

import com.emazon.stock_api_service.application.dto.ArticleRequest;
import com.emazon.stock_api_service.application.handler.ArticleHandler;
import com.emazon.stock_api_service.application.mapper.IArticleRequestMapper;
import com.emazon.stock_api_service.application.mapper.IArticleResponseMapper;
import com.emazon.stock_api_service.domain.api.IArticleServicePort;
import com.emazon.stock_api_service.domain.api.IBrandServicePort;
import com.emazon.stock_api_service.domain.api.ICategoryServicePort;
import com.emazon.stock_api_service.domain.exception.ArticleUseCaseException;
import com.emazon.stock_api_service.domain.spi.IBrandPersistencePort;
import com.emazon.stock_api_service.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.emazon.stock_api_service.util.ArticleConstants.*;
import static com.emazon.stock_api_service.util.BrandConstants.BRAND_ID_OBLIGATORY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ArticleHandlerTest {
    @Mock
    private IArticleServicePort articleServicePort;
    @Mock
    private IArticleRequestMapper articleRequestMapper;
    @Mock
    private IArticleResponseMapper articleResponseMapper;
    @Mock
    private IBrandServicePort brandServicePort;
    @Mock
    private IBrandPersistencePort brandPersistencePort;
    @Mock
    private ICategoryServicePort categoryServicePort;
    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private ArticleHandler articleHandler;

    ArticleRequest articleRequest;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        articleRequest
                = new ArticleRequest(
                "article","article description"
                ,2L, new BigDecimal("12.22")
                ,1L,Arrays.asList(1L,2L,3L));
        when(articleHandler.brandIdExists(1L)).thenReturn(true);
        when(articleHandler.categoryIdExists(1L)).thenReturn(true);
        when(articleHandler.categoryIdExists(2L)).thenReturn(true);
        when(articleHandler.categoryIdExists(3L)).thenReturn(true);
    }

    @Test
    void testBrandIdObligatory(){
        articleRequest.setBrandId(null);
        ArticleUseCaseException ex = assertThrows(ArticleUseCaseException.class
                , () -> articleHandler.validateIds(articleRequest));

        assertEquals(BRAND_ID_OBLIGATORY
                ,ex.getErrorList().get(0));
    }

    @Test
    void testBrandIdExists(){
        articleRequest.setBrandId(2L);
        ArticleUseCaseException ex = assertThrows(ArticleUseCaseException.class
                , () -> articleHandler.validateIds(articleRequest));

        assertEquals(BRAND_ID_NOT_FOUND
                ,ex.getErrorList().get(0));
    }

    @Test
    void testCategoryIdsObligatory(){
        articleRequest.setCategoryIds(null);
        ArticleUseCaseException ex = assertThrows(ArticleUseCaseException.class
                , () -> articleHandler.validateIds(articleRequest));

        assertEquals(CATEGORY_IDS_OBLIGATORY
                ,ex.getErrorList().get(0));
    }
    @Test
    void testMinimumCategoriesAssociated(){
        articleRequest.setCategoryIds(Arrays.asList());
        ArticleUseCaseException ex = assertThrows(ArticleUseCaseException.class
                , () -> articleHandler.validateIds(articleRequest));

        assertEquals(MINIMUM_CATEGORIES_MESSAGE
                ,ex.getErrorList().get(0));
    }
    @Test
    void testMaximumCategoriesAssociated(){
        List<Long> categoryIds=new ArrayList<>(articleRequest.getCategoryIds());
        categoryIds.add(4L);
        when(articleHandler.categoryIdExists(4L)).thenReturn(true);
        articleRequest.setCategoryIds(categoryIds);
        ArticleUseCaseException ex = assertThrows(ArticleUseCaseException.class
                , () -> articleHandler.validateIds(articleRequest));

        assertEquals(MAXIMUM_CATEGORIES_MESSAGE
                ,ex.getErrorList().get(0));
    }

    @Test
    void testCategoryRepeated(){
        List<Long> categoryIds=articleRequest.getCategoryIds();
        categoryIds.set(0,2L);
        articleRequest.setCategoryIds(categoryIds);
        ArticleUseCaseException ex = assertThrows(ArticleUseCaseException.class
                , () -> articleHandler.validateIds(articleRequest));

        assertEquals(CATEGORY_REPEATED
                ,ex.getErrorList().get(0));
    }

    @Test
    void testCategoryIdDoesNotExist(){
        Long unknownCategoryId=4L;
        List<Long> categoryIds=articleRequest.getCategoryIds();
        categoryIds.set(0,unknownCategoryId);
        articleRequest.setCategoryIds(categoryIds);
        ArticleUseCaseException ex = assertThrows(ArticleUseCaseException.class
                , () -> articleHandler.validateIds(articleRequest));

        assertEquals(CATEGORY_ID_DOES_NOT_EXIST(unknownCategoryId)
                ,ex.getErrorList().get(0));
    }
}
