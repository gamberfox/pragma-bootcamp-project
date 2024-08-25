package com.emazon.stock_api_service.domain.usecase;

import com.emazon.stock_api_service.domain.api.IArticleServicePort;
import com.emazon.stock_api_service.domain.exception.ArticleUseCaseException;
import com.emazon.stock_api_service.domain.exception.ResourceNotFoundException;
import com.emazon.stock_api_service.domain.model.Article;
import com.emazon.stock_api_service.domain.model.ArticleCategory;
import com.emazon.stock_api_service.domain.spi.IArticleCategoryPersistencePort;
import com.emazon.stock_api_service.domain.spi.IArticlePersistencePort;

import java.util.ArrayList;
import java.util.List;

import static com.emazon.stock_api_service.util.ArticleConstants.*;

public class ArticleUseCase implements IArticleServicePort {
    private final IArticlePersistencePort articlePersistencePort;
    private final IArticleCategoryPersistencePort articleCategoryPersistencePort;

    public ArticleUseCase(IArticlePersistencePort articlePersistencePort
    , IArticleCategoryPersistencePort articleCategoryPersistencePort) {
        this.articlePersistencePort = articlePersistencePort;
        this.articleCategoryPersistencePort=articleCategoryPersistencePort;
    }

    @Override
    public void createArticle(Article article, List<Long> categoryIds) {
        validate(article,categoryIds);
        articlePersistencePort.createArticle(article);
        Long articleId=articlePersistencePort
                .getArticleByName(article.getName()).getId();
        for(Long categoryId : categoryIds) {
            articleCategoryPersistencePort
                    .createArticleCategory(new ArticleCategory(articleId, categoryId));
        }
    }

    @Override
    public Article getArticleById(Long id) {
        if(Boolean.FALSE.equals(idExists(id))) {
            throw new ResourceNotFoundException(ARTICLE_NOT_FOUND);
        }
        return articlePersistencePort.getArticleById(id);
    }

    @Override
    public Article getArticleByName(String name) {
        if(Boolean.FALSE.equals(nameExists(name))) {
            throw new ResourceNotFoundException(ARTICLE_NOT_FOUND);
        }
        return null;
    }

    @Override
    public void validate(Article article, List<Long> categoryIds) {
        List<String> errorList=new ArrayList<>();
        validateArticle(article,errorList);
        validateCategoryIds(categoryIds,errorList);
        if(!errorList.isEmpty()){
            throw new ArticleUseCaseException(errorList);
        }
    }

    public void validateCategoryIds(List<Long> categoryIds,List<String> errorList){
        if(categoryIds.isEmpty()){

        }
    }

    public void validateArticle(Article article,List<String> errorList){

    }
    public Boolean idExists(Long id) {
        return articlePersistencePort.articleIdExists(id);
    }
    public Boolean nameExists(String name) {
        return articlePersistencePort.articleNameExists(name);
    }
}
