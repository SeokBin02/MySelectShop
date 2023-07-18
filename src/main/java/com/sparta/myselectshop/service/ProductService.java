package com.sparta.myselectshop.service;

import com.sparta.myselectshop.dto.ItemDto;
import com.sparta.myselectshop.dto.ProductMypriceRequestDto;
import com.sparta.myselectshop.dto.ProductRequestDto;
import com.sparta.myselectshop.dto.ProductResponseDto;
import com.sparta.myselectshop.entity.Product;
import com.sparta.myselectshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    /** 관심 상품 등록하기 */
    public ProductResponseDto createProduct(ProductRequestDto requestDto){
        Product product = productRepository.saveAndFlush(new Product(requestDto));
        return new ProductResponseDto(product);
    }

    /** 관심 상품 조회하기 */
    @Transactional(readOnly = true)
    public List<ProductResponseDto> getProducts(){
        List<ProductResponseDto> products = new ArrayList<>();
        for(Product product : productRepository.findAll()){
            products.add(new ProductResponseDto(product));
        }

        return products;
    }

    /** 관심 상품 최저가 등록하기 */
    public void updateProduct(Long id, ProductMypriceRequestDto requestDto){
        Product product = productRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 상품은 존재하지 않습니다.")
        );

        product.update(requestDto);
    }

    public void updateBySearch(Long id, ItemDto itemDto) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 상품이 존재하지 않습니다.")
        );

        product.updateByItemDto(itemDto);
    }
}
