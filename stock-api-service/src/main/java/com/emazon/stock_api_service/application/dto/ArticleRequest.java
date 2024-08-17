package com.emazon.stock_api_service.application.dto;

import lombok.Getter;
import lombok.Setter;

//Request is a petition we want to make to the system
@Getter
@Setter
public class ArticleRequest {
    //there are cases where the id won't be sent
    private String name;
    private String description;
}
