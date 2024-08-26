package com.emazon.stock_api_service.application.handler;

import com.emazon.stock_api_service.application.dto.ArticleRequest;
import com.emazon.stock_api_service.application.dto.ArticleResponse;
import com.emazon.stock_api_service.domain.model.Article;

public interface IArticleHandler {
    void createArticle(ArticleRequest articleRequest);
    ArticleResponse getArticleResponseById(Long id);
    ArticleResponse getArticleResponseByName(String name);
}
