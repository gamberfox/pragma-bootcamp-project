package com.emazon.stock_api_service.application.dto;

import com.emazon.stock_api_service.domain.exception.ArticleUseCaseException;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import static com.emazon.stock_api_service.util.ArticleConstants.*;

@Getter
@Setter
public class ArticleRequest {
    public static final int MIN_CAT_ASSOCIATED =1;//(int)MINIMUM_CATEGORIES_ASSOCIATED;
    public static final int MAX_CAT_ASSOCIATED =3;//(int)MAXIMUM_CATEGORIES_ASSOCIATED;
    public static final String ASSOCIATED_CATEGORIES="associated_categories";
    private String name;
    private String description;
    private Long stock;
    private BigDecimal price;
    //@NotNull(message = BRAND_OBLIGATORY)
    private Long brandId;
    //@NotNull(message = CATEGORY_LIST_OBLIGATORY)
    //@Size(min=MIN_CAT_ASSOCIATED,max=MAX_CAT_ASSOCIATED,message=ASSOCIATED_CATEGORIES)
    private List<Long> categoryIds;
}
