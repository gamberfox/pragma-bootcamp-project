package com.emazon.stock_api_service.domain.api;

import com.emazon.stock_api_service.domain.model.Article;

import java.util.List;

//this will be exposed as the service to the exterior
//we'll also declare methods that we'll expose through the interface
public interface IArticleServicePort {
    //this function will have a connection with the persistency
    void createArticle(Article article);    ;

}
