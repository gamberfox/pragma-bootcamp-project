package com.emazon.stock_api_service.domain.usecase;

import com.emazon.stock_api_service.domain.api.IArticleServicePort;
import com.emazon.stock_api_service.domain.model.Article;
import com.emazon.stock_api_service.domain.spi.IArticlePersistencyPort;

public class ArticleUseCase implements IArticleServicePort {
    //@AutoWired is not recommended, if you want to do dependency injection,
    //you need to do it through injections in the class constructor.
    //we can't use spring because we're being agnostic of the technology,
    //so we won't use any annotation, therefore we'll make the injection
    //manually
    private final IArticlePersistencyPort articlePersistencyPort;
    //we'll generate a constructor with our attribute
    //we're performing dependency injection through a constructor
    public ArticleUseCase(IArticlePersistencyPort articlePersistencyPort) {
        this.articlePersistencyPort = articlePersistencyPort;
    }
    //we need to communicate what we're receiving with the thing that will
    //go through the domain, and what will be sent to the persistency
    @Override
    public void createArticle(Article article) {
        //we're using the class that will be implemented by the interface we declared
        //we're making separation through interfaces because if we use a
        //PersistencyPort class, that class could change any day, and then we'll have
        // to change the whole code. we won't be affected by this if we use interfaces
        this.articlePersistencyPort.createArticle(article);
    }
}
