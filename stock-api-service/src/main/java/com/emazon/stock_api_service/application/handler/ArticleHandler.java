package com.emazon.stock_api_service.application.handler;

import com.emazon.stock_api_service.application.dto.ArticleRequest;
import com.emazon.stock_api_service.application.dto.ArticleResponse;
import com.emazon.stock_api_service.application.mapper.IArticleRequestMapper;
import com.emazon.stock_api_service.application.mapper.IArticleResponseMapper;
import com.emazon.stock_api_service.domain.api.IArticleServicePort;
import com.emazon.stock_api_service.domain.api.IBrandServicePort;
import com.emazon.stock_api_service.domain.api.ICategoryServicePort;
import com.emazon.stock_api_service.domain.model.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class ArticleHandler implements IArticleHandler {
    private final IArticleServicePort articleServicePort;
    private final IArticleRequestMapper articleRequestMapper;
    private final IArticleResponseMapper articleResponseMapper;
    private final IBrandServicePort brandServicePort;
    private final ICategoryServicePort categoryServicePort;

    @Override
    public void createArticle(ArticleRequest articleRequest) {
        Article article = articleRequestMapper.toArticle(articleRequest);
        articleServicePort.createArticle(article);
    }

    @Override
    public ArticleResponse getArticleResponseById(Long id) {
        Article article= articleServicePort.getArticleById(id);
        return articleResponseMapper.toArticleResponse(article);
    }
}
