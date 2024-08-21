package com.emazon.stock_api_service.infrastructure.input.rest;

import com.emazon.stock_api_service.application.dto.CategoryRequest;
import com.emazon.stock_api_service.application.dto.CategoryResponse;
import com.emazon.stock_api_service.application.handler.ICategoryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class    CategoryRestController {
    //every independence we inject are interfaces
    private final ICategoryHandler categoryHandler;
    @PostMapping("/")
    //we'll return a response entity of a  Void type because we're not interested
    //showing the user/client anything beyond the creation being made
    public ResponseEntity<Void> createCategory(@RequestBody CategoryRequest categoryRequest) {
        categoryHandler.createCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/aa")
    public ResponseEntity<String> responseTest(){
        System.out.println("another aa test");
        return ResponseEntity.ok("another aa test");
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable(name="id") Long id) {
        return ResponseEntity.ok(categoryHandler.getCategoryResponse(id));
    }

    @GetMapping("/all/{ascendingOrder}")
    public ResponseEntity<Page<CategoryResponse>> getCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @PathVariable(name="ascendingOrder") boolean ascendingOrder) {
        //Pageable pageable = PageRequest.of(page, size, ascendingOrder ? Sort.by("name").ascending() : Sort.by("name").descending());
        Pageable pageable = PageRequest.of(page, size);
        List<CategoryResponse> categoryResponses = categoryHandler.getCategoryResponses(ascendingOrder);
        return ResponseEntity.ok(new PageImpl<>(categoryResponses, pageable, categoryResponses.size()));
    }
}
