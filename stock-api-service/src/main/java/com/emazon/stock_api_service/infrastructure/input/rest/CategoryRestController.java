package com.emazon.stock_api_service.infrastructure.input.rest;

import com.emazon.stock_api_service.application.dto.CategoryRequest;
import com.emazon.stock_api_service.application.dto.CategoryResponse;
import com.emazon.stock_api_service.application.handler.ICategoryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryRestController {
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
        System.out.println("this is a test");
        return ResponseEntity.ok(categoryHandler.getCategoryResponse(id));
    }
}
