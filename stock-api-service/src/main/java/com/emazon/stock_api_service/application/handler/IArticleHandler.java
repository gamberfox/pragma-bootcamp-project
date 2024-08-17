package com.emazon.stock_api_service.application.handler;


import com.emazon.stock_api_service.application.dto.ArticleRequest;
import com.emazon.stock_api_service.application.dto.ArticleResponse;
import com.emazon.stock_api_service.domain.model.Article;

public interface IArticleHandler {
    //methods very similar to the domain because it's a CRUD
    //but we're using the methods with elements of the same layer
    void createArticle(ArticleRequest articleRequest);
}
