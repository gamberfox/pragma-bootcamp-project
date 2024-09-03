package com.emazon.stock_api_service.application.handler;

import com.emazon.stock_api_service.application.dto.ArticleRequest;
import com.emazon.stock_api_service.application.dto.ArticleResponse;

public interface IArticleHandler {
    void createArticle(ArticleRequest articleRequest);
    ArticleResponse getArticleResponseById(Long id);
}
