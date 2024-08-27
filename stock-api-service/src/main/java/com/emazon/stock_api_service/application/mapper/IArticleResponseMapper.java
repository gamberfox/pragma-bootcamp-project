package com.emazon.stock_api_service.application.mapper;

import com.emazon.stock_api_service.application.dto.ArticleResponse;
import com.emazon.stock_api_service.domain.model.Article;
import com.emazon.stock_api_service.domain.model.Brand;
import com.emazon.stock_api_service.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
uses={IArticleResponseMapper.class,IBrandResponseMapper.class})
public interface IArticleResponseMapper {
    @Mapping(source = "article.id", target = "id")
    @Mapping(source = "article.name", target = "name")
    @Mapping(source = "article.description", target = "description")
    @Mapping(source = "article.stock", target = "stock")
    @Mapping(source = "article.price", target = "price")
    @Mapping(source = "brand", target = "brand")
    @Mapping(source = "categories", target = "categories")
    ArticleResponse toArticleResponse(Article article, Brand brand, List<Category> categories);
}
