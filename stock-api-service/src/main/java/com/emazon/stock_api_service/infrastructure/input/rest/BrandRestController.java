package com.emazon.stock_api_service.infrastructure.input.rest;


import com.emazon.stock_api_service.application.dto.BrandRequest;
import com.emazon.stock_api_service.application.dto.BrandResponse;
import com.emazon.stock_api_service.application.dto.CategoryResponse;
import com.emazon.stock_api_service.application.handler.IBrandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
public class BrandRestController {
    private final IBrandHandler brandHandler;

    @PostMapping("/")
    public ResponseEntity<String> createBrand(@RequestBody BrandRequest brandRequest) {
        brandHandler.createBrand(brandRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Brand created");
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandResponse> getBrandById(@PathVariable(name="id") Long id) {
        return ResponseEntity.ok(brandHandler.getBrandResponseById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<BrandResponse> getBrandByName(@PathVariable(name="name") String name) {
        return ResponseEntity.ok(brandHandler.getBrandResponseByName(name));
    }
}
