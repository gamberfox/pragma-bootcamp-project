package com.emazon.stock_api_service.application.mapper;

import com.emazon.stock_api_service.application.dto.ArticleRequest;
import com.emazon.stock_api_service.domain.model.Article;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IArticleRequestMapper {
    Article toArticle(ArticleRequest articleRequest);
}
