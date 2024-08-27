package com.emazon.stock_api_service.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "article_category")
@NoArgsConstructor
@Getter
@Setter
public class ArticleCategoryEntity {

    @EmbeddedId
    private ArticleCategoryId id;

    // Additional fields if needed
}


