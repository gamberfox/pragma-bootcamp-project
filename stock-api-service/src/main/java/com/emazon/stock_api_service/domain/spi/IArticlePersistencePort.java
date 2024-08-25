package com.emazon.stock_api_service.domain.spi;

import com.emazon.stock_api_service.domain.model.Article;

import java.util.List;

public interface IArticlePersistencePort {
    void createArticle(Article article, List<Long> categoryIdList);
    Article getArticleById(Long id);
    Article getArticleByName(String name);
    Boolean articleIdExists(Long id);
    Boolean articleNameExists(String name);
}
