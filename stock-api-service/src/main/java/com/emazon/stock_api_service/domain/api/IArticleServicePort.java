package com.emazon.stock_api_service.domain.api;

import com.emazon.stock_api_service.domain.model.Article;

import java.util.List;


public interface IArticleServicePort {
    void validate(Article article);
    void createArticle(Article article);
    Article getArticleById(Long id);
    List<Article> getArticles(Boolean ascendingOrder,String comparor);
}
