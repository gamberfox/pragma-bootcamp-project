package com.emazon.stock_api_service.domain.usecase;

import com.emazon.stock_api_service.domain.api.IArticleServicePort;
import com.emazon.stock_api_service.domain.exception.ArticleUseCaseException;
import com.emazon.stock_api_service.domain.exception.ResourceNotFoundException;
import com.emazon.stock_api_service.domain.model.Article;
import com.emazon.stock_api_service.domain.spi.IArticlePersistencePort;
import com.emazon.stock_api_service.domain.spi.IBrandPersistencePort;
import com.emazon.stock_api_service.domain.spi.ICategoryPersistencePort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.emazon.stock_api_service.util.ArticleConstants.*;
import static com.emazon.stock_api_service.util.BrandConstants.BRAND_NOT_FOUND;
import static com.emazon.stock_api_service.util.CategoryConstants.CATEGORY_NOT_FOUND;

public class ArticleUseCase implements IArticleServicePort {
    private final IArticlePersistencePort articlePersistencePort;
    private final ICategoryPersistencePort categoryPersistencePort;
    private final IBrandPersistencePort brandPersistencePort;

    public ArticleUseCase(IArticlePersistencePort articlePersistencePort
    , ICategoryPersistencePort categoryPersistencePort
    ,IBrandPersistencePort brandPersistencePort) {
        this.articlePersistencePort = articlePersistencePort;
        this.categoryPersistencePort=categoryPersistencePort;
        this.brandPersistencePort=brandPersistencePort;
    }

    @Override
    public void createArticle(Article article) {
        validate(article);
        articlePersistencePort.createArticle(article);
    }

    @Override
    public Article getArticleById(Long id) {
        if(Boolean.FALSE.equals(idExists(id))) {
            throw new ResourceNotFoundException(ARTICLE_NOT_FOUND);
        }
        return articlePersistencePort.getArticleById(id);
    }

    @Override
    public void validate(Article article) {
        List<String> errorList=new ArrayList<>();
        validateArticle(article,errorList);
//        if(Boolean.FALSE.equals(brandIdExists(article.getBrandId()))){
//            errorList.add(BRAND_ID_NOT_FOUND);
//        }
        if(!errorList.isEmpty()){
            throw new ArticleUseCaseException(errorList);
        }
    }

    public void validateArticle(Article article,List<String> errorList){
//        if(Boolean.TRUE.equals(
//                nameExists(article.getName()))){
//            errorList.add(ARTICLE_NAME_ALREADY_EXISTS);
//        }
//        if(Boolean.FALSE.equals(
//                categoryIdExists(article.getBrandId()))){
//            errorList.add(BRAND_NOT_FOUND);
//        }
    }
    public void validateCategoryIds(List<Long> categoryIds,List<String> errorList){
        if(categoryIds.isEmpty()){
            errorList.add(MINIMUM_CATEGORY);
        }
        /*else{
            Map<Long, Long> hashMap = new HashMap<>();
            for(Long categoryId : categoryIds) {
                if(Boolean.TRUE.equals(categoryIdExists(categoryId))) {
                    if(!hashMap.containsKey(categoryId)) {
                        hashMap.put(categoryId, 1L);
                    }
                    else if(hashMap.get(categoryId)==2L){
                        errorList.add(CATEGORY_REPEATED);
                    }
                }
                else{
                    errorList.add(CATEGORY_NOT_FOUND);
                }
            }
            if(MINIMUM_CATEGORIES_ASSOCIATED>errorList.size()){
                errorList.add(MINIMUM_CATEGORY);
            }
            else if(MAXIMUM_CATEGORIES_ASSOCIATED<errorList.size()){
                errorList.add(MAXIMUM_CATEGORY);
            }
        }*/
    }

    public void validateCategoryNames(List<String> categoryNames,List<String> errorList){
        if(categoryNames.isEmpty()){
            errorList.add(MINIMUM_CATEGORY);
        }
    }

    public Boolean idExists(Long id) {
        return articlePersistencePort.articleIdExists(id);
    }
    public Boolean nameExists(String name) {
        return articlePersistencePort.articleNameExists(name);
    }
    public Boolean categoryNameExists(String name) {
        return categoryPersistencePort.categoryNameExists(name);
    }
    public Boolean categoryIdExists(Long id) {
        return categoryPersistencePort.categoryIdExists(id);
    }
    public Boolean brandIdExists(Long id) {
        return brandPersistencePort.brandIdExists(id);
    }
}
