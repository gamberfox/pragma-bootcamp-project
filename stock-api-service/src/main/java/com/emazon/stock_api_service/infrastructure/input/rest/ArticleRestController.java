package com.emazon.stock_api_service.infrastructure.input.rest;

import com.emazon.stock_api_service.application.dto.ArticleRequest;
import com.emazon.stock_api_service.application.dto.ArticleResponse;
import com.emazon.stock_api_service.application.handler.IArticleHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.emazon.stock_api_service.util.ArticleConstants.*;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleRestController {
    private final IArticleHandler articleHandler;

    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> createArticle(@RequestBody ArticleRequest articleRequest) {
        articleHandler.createArticle(articleRequest);
        //ArticleResponse articleResponse=articleHandler.getArticleResponseByName(articleRequest.getName());
        RestResponse response=new RestResponse(ARTICLE_CREATED, articleRequest);
        return new ResponseEntity<>(response.getResponse(), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponse> getArticleById(@PathVariable(name="id") Long id) {
        return ResponseEntity.ok(articleHandler.getArticleResponseById(id));
    }

    @GetMapping("/a/{id}")
    public ResponseEntity<String> test1(@PathVariable(name="id") Long id) {
        return ResponseEntity.ok(
                articleHandler.getArticleResponseById(id).getName()
        +articleHandler.getArticleResponseById(id).getId()
        +articleHandler.getArticleResponseById(id).getBrand().getName());
    }

//    @GetMapping("/name/{name}")
//    public ResponseEntity<ArticleResponse> getArticleByName(@PathVariable(name="name") String name) {
//        return ResponseEntity.ok(articleHandler.getArticleResponseByName(name));
//    }
}
