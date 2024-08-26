package com.emazon.stock_api_service.infrastructure.output.jpa.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ArticleCategoryId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "id_article")
    private ArticleEntity article;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private CategoryEntity category;

    public ArticleCategoryId() {}

    public ArticleCategoryId(ArticleEntity article, CategoryEntity category) {
        this.article = article;
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleCategoryId that = (ArticleCategoryId) o;
        return Objects.equals(article, that.article) && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(article, category);
    }

    // Getters and Setters
}
