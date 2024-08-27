package com.emazon.stock_api_service.infrastructure.output.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ArticleCategoryId implements Serializable {
    @Column(name = "id_article")
    private Long article;

    @Column(name = "id_category")
    private Long category;
}
