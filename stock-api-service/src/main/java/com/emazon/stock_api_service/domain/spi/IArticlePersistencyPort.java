package com.emazon.stock_api_service.domain.spi;

import com.emazon.stock_api_service.domain.model.Article;

//spi are interfaces that extend the capacities of our system.
public interface IArticlePersistencyPort {
    void createArticle(Article article);
}
