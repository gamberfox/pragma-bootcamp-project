package com.emazon.stock_api_service.domain.usecase;

import com.emazon.stock_api_service.domain.api.IArticleServicePort;
import com.emazon.stock_api_service.domain.exception.ArticleUseCaseException;
import com.emazon.stock_api_service.domain.exception.ResourceNotFoundException;
import com.emazon.stock_api_service.domain.model.Article;
import com.emazon.stock_api_service.domain.spi.IArticlePersistencePort;
import com.emazon.stock_api_service.domain.spi.IBrandPersistencePort;
import com.emazon.stock_api_service.domain.spi.ICategoryPersistencePort;

import java.util.ArrayList;
import java.util.List;

import static com.emazon.stock_api_service.util.ArticleConstants.*;

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
        if(!errorList.isEmpty()){
            throw new ArticleUseCaseException(errorList);
        }
    }

    public void validateArticle(Article article,List<String> errorList){
        //empty method
    }
    public void validateCategoryIds(List<Long> categoryIds,List<String> errorList){
        if(categoryIds.isEmpty()){
            errorList.add(MINIMUM_CATEGORIES_MESSAGE);
        }
    }

    public void validateCategoryNames(List<String> categoryNames,List<String> errorList){
        if(categoryNames.isEmpty()){
            errorList.add(MINIMUM_CATEGORIES_MESSAGE);
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
