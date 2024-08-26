package com.emazon.stock_api_service.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="article_category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticleCategoryEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_article")
    private ArticleEntity article;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_category")
    private CategoryEntity category;

    /*@Id
    @Column(name = "id_article")
    private Long idArticle;

    @Id
    @Column(name = "id_category")
    private Long idCategory;*/
}
