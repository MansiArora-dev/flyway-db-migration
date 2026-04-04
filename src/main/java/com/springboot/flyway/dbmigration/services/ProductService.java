package com.springboot.flyway.dbmigration.services;

import com.springboot.flyway.dbmigration.dto.request.ProductRequestDto;
import com.springboot.flyway.dbmigration.dto.response.ProductResponseDto;
import java.util.List;

public interface ProductService {
    List<ProductResponseDto> getAllProducts();
    ProductResponseDto getProductById(Long id);
    List<ProductResponseDto> getProductsByCategory(String category);
    ProductResponseDto createProduct(ProductRequestDto request);
    ProductResponseDto updateProduct(Long id, ProductRequestDto request);
    void deleteProduct(Long id);
}