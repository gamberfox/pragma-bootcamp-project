package com.emazon.stock_api_service.application.handler;

import com.emazon.stock_api_service.application.dto.ArticleRequest;
import com.emazon.stock_api_service.application.dto.ArticleResponse;
import com.emazon.stock_api_service.application.mapper.IArticleRequestMapper;
import com.emazon.stock_api_service.application.mapper.IArticleResponseMapper;
import com.emazon.stock_api_service.domain.api.IArticleServicePort;
import com.emazon.stock_api_service.domain.api.IBrandServicePort;
import com.emazon.stock_api_service.domain.api.ICategoryServicePort;
import com.emazon.stock_api_service.domain.exception.ArticleUseCaseException;
import com.emazon.stock_api_service.domain.model.Article;
import com.emazon.stock_api_service.domain.model.Category;
import com.emazon.stock_api_service.domain.spi.IBrandPersistencePort;
import com.emazon.stock_api_service.domain.spi.ICategoryPersistencePort;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.emazon.stock_api_service.util.ArticleConstants.*;
import static com.emazon.stock_api_service.util.BrandConstants.*;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleHandler implements IArticleHandler {
    private final IArticleServicePort articleServicePort;
    private final IArticleRequestMapper articleRequestMapper;
    private final IArticleResponseMapper articleResponseMapper;
    private final IBrandServicePort brandServicePort;
    private final IBrandPersistencePort brandPersistencePort;
    private final ICategoryServicePort categoryServicePort;
    private final ICategoryPersistencePort categoryPersistencePort;

    @Override
    public void createArticle(ArticleRequest articleRequest) {
        validateIds(articleRequest);
        Article article = articleRequestMapper.toArticle(articleRequest);
        article.setBrand(brandServicePort.getBrandById(articleRequest.getBrandId()));
        List<Category> categoriesToAdd = new ArrayList<>();
        for(Long categoryId : articleRequest.getCategoryIds()) {
            categoriesToAdd.add(categoryServicePort.getCategoryById(categoryId));
        }
        article.setCategories(categoriesToAdd);
        articleServicePort.createArticle(article);
    }

    @Override
    public ArticleResponse getArticleResponseById(Long id) {
        Article article= articleServicePort.getArticleById(id);
        return articleResponseMapper.toArticleResponse(article);
    }
    @Validate
    public void validateIds(ArticleRequest articleRequest){
        List<String> errorList = new ArrayList<>();
        if(articleRequest.getBrandId()==null){
            errorList.add(BRAND_ID_OBLIGATORY);
        }
        else if(Boolean.FALSE.equals(brandIdExists(articleRequest.getBrandId()))){
            errorList.add(BRAND_ID_NOT_FOUND);
        }
        if(articleRequest.getCategoryIds()==null){
            errorList.add(CATEGORY_IDS_OBLIGATORY);
        }
        else{
            if(articleRequest.getCategoryIds().size()<MINIMUM_CATEGORIES_ASSOCIATED){
                errorList.add(MINIMUM_CATEGORIES_MESSAGE);
            }
            if(articleRequest.getCategoryIds().size()
                    >MAXIMUM_CATEGORIES_ASSOCIATED){
                errorList.add(MAXIMUM_CATEGORIES_MESSAGE);
            }
            HashSet<Long> categoryIdSet=new HashSet<>(articleRequest.getCategoryIds());
            if(categoryIdSet.size()
                    <articleRequest.getCategoryIds().size()){
               errorList.add(CATEGORY_REPEATED);
            }
            for(Long categoryId : categoryIdSet){
                if(Boolean.FALSE.equals(categoryIdExists(categoryId))){
                    errorList.add(CATEGORY_ID_DOES_NOT_EXIST(categoryId));
                }
            }
        }
        if(!errorList.isEmpty()){
            throw new ArticleUseCaseException(errorList);
        }
    }
    @Override
    public Boolean brandIdExists(Long id){
        return brandPersistencePort.brandIdExists(id);
    }
    @Override
    public Boolean categoryIdExists(Long id){
        return categoryPersistencePort.categoryIdExists(id);
    }
}
