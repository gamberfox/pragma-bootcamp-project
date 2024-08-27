package com.emazon.stock_api_service.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "article_category")
@NoArgsConstructor
@Getter
@Setter
public class ArticleCategoryEntity {

    @EmbeddedId
    private ArticleCategoryId id;

    @ManyToOne
    @JoinColumn(name = "id_article", insertable = false, updatable = false)
    private ArticleEntity article;

    @ManyToOne
    @JoinColumn(name = "id_category", insertable = false, updatable = false)
    private CategoryEntity category;
}


