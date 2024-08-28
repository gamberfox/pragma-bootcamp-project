package com.emazon.stock_api_service.infrastructure.output.jpa.mapper;

import com.emazon.stock_api_service.domain.model.Article;
import com.emazon.stock_api_service.infrastructure.output.jpa.entity.ArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IArticleEntityMapper {
    ArticleEntity toArticleEntity(Article article);
    Article toArticle(ArticleEntity articleEntity);
}
