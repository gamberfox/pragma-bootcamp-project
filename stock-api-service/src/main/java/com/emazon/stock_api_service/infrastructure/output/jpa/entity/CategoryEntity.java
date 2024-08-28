package com.emazon.stock_api_service.infrastructure.output.jpa.entity;

import com.emazon.stock_api_service.domain.model.Category;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryEntity {
    //we can see this class is very similar to the domain.model classes
    @Id
    //this does the auto implement
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private String description;

    @ManyToMany(mappedBy = "categories")//we tell spring this not the honor entity
    private List<ArticleEntity> categories;
}
