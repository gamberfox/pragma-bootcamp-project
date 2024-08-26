package com.emazon.stock_api_service.infrastructure.output.jpa.adapter;

import com.emazon.stock_api_service.domain.model.Article;
import com.emazon.stock_api_service.domain.spi.IArticlePersistencePort;
import com.emazon.stock_api_service.infrastructure.output.jpa.entity.ArticleEntity;
import com.emazon.stock_api_service.infrastructure.output.jpa.mapper.IArticleEntityMapper;
import com.emazon.stock_api_service.infrastructure.output.jpa.repository.IArticleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ArticleJpaAdapter implements IArticlePersistencePort {
    private final IArticleRepository articleRepository;
    private final IArticleEntityMapper articleEntityMapper;
    @Override
    public void createArticle(Article article) {
        ArticleEntity articleEntity = articleEntityMapper.toArticleEntity(article);
        articleRepository.save(articleEntity);
    }

    @Override
    public Article getArticleById(Long id) {
        ArticleEntity articleEntity= articleRepository.findById(id).get();
        return articleEntityMapper.toArticle(articleEntity);
    }

    @Override
    public Article getArticleByName(String name) {
        return null;
    }

    @Override
    public Boolean articleIdExists(Long id) {
        return null;
    }

    @Override
    public Boolean articleNameExists(String name) {
        return null;
    }
}
