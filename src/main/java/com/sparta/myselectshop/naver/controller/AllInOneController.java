package com.sparta.myselectshop.naver.controller;

import com.sparta.myselectshop.naver.dto.ProductRequestDto;
import com.sparta.myselectshop.naver.dto.ProductResponseDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AllInOneController {

    // 관심 상품 등록하기
    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto){
        // 요청받은 DTO로 DB에 저장할 객체 만들기
    }
}
