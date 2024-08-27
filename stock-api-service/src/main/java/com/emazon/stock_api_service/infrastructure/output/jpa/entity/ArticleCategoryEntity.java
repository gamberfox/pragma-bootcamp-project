package com.emazon.stock_api_service.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "article_category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticleCategoryEntity {

    @Column(name = "id_article")
    private Long articleId;

    @Column(name = "id_category")
    private Long categoryId;

    /*@EmbeddedId
    private ArticleCategoryId id;

    @ManyToOne
    @JoinColumn(name = "id_article", insertable = false, updatable = false)
    private ArticleEntity articleId;

    @ManyToOne
    @JoinColumn(name = "id_category", insertable = false, updatable = false)
    private CategoryEntity categoryId;*/
}


