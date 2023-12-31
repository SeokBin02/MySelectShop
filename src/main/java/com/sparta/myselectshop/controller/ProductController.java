package com.sparta.myselectshop.controller;

import com.sparta.myselectshop.dto.ProductRequestDto;
import com.sparta.myselectshop.dto.ProductMypriceRequestDto;
import com.sparta.myselectshop.dto.ProductResponseDto;
import com.sparta.myselectshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    /** 관심 상품 등록하기 */
    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto) throws SQLException {
        // 응답 보내기
        return productService.createProduct(requestDto);

    }

    /** 관심 상품 조회하기 */
    @GetMapping("/products")
    public List<ProductResponseDto> getProducts() throws SQLException{
        return productService.getProducts();
    }

    /** 관심 상품 최저가 등록하기 */
    @PutMapping("/products/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody ProductMypriceRequestDto requestDto) throws SQLException {
        productService.updateProduct(id, requestDto);
    }
}
