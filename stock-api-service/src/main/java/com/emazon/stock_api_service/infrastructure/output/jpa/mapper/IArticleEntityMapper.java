package com.emazon.stock_api_service.infrastructure.output.jpa.mapper;

import com.emazon.stock_api_service.domain.model.Article;
import com.emazon.stock_api_service.infrastructure.output.jpa.entity.ArticleEntity;
import com.emazon.stock_api_service.infrastructure.output.jpa.entity.BrandEntity;

import java.util.List;

public interface IArticleEntityMapper {
    ArticleEntity toArticleEntity(Article brand);
    Article toArticle(ArticleEntity articleEntity);
    List<Article> toArticles(List<ArticleEntity> articleEntities);
}
