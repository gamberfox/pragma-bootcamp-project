package com.emazon.stock_api_service.domain;

import com.emazon.stock_api_service.domain.exception.ArticleUseCaseException;
import com.emazon.stock_api_service.domain.exception.BrandUseCaseException;
import com.emazon.stock_api_service.domain.model.Article;
import com.emazon.stock_api_service.domain.model.Brand;
import com.emazon.stock_api_service.domain.usecase.ArticleUseCase;
import com.emazon.stock_api_service.domain.usecase.BrandUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static com.emazon.stock_api_service.util.ArticleConstants.BRAND_OBLIGATORY;
import static com.emazon.stock_api_service.util.BrandConstants.BRAND_NAME_ALREADY_EXISTS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ArticleUseCaseTest {

    private BrandUseCase brandUseCase;
    private ArticleUseCase articleUseCase;
    private Article article;
    private Brand brand;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        brand=new Brand();
        article=new Article(1L,"article","description",2L,
                new BigDecimal("12.12"),brand,null);
    }

    @Test
    void testValidateBrand() {
        when(articleUseCase.brandIdExists(1L)).thenReturn(false);
        Brand brand = new Brand(null,null, null);
        article.setBrand(brand);
        ArticleUseCaseException ex = assertThrows(ArticleUseCaseException.class
                , () -> articleUseCase.validate(article));
        assertEquals(BRAND_OBLIGATORY
                ,ex.getErrorList().get(0));
    }
}
