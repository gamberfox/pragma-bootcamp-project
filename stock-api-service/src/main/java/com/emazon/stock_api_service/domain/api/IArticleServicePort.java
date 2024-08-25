package com.emazon.stock_api_service.domain.api;

import com.emazon.stock_api_service.domain.model.Article;

import java.util.List;

public interface IArticleServicePort {
    void validateArticle(Article article);
    void createArticle(Article article, List<Long> categoryIdList);
    Article getArticleById(Long id);
    Article getArticleByName(String name);
}
