package com.emazon.stock_api_service.domain.spi;

import com.emazon.stock_api_service.domain.model.Article;

public interface IArticlePersistencePort {
    void createArticle(Article article);
    Article getArticleById(Long id);
    Boolean articleIdExists(Long id);
    Boolean articleNameExists(String name);
}