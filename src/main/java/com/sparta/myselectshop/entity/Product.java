package com.sparta.myselectshop.entity;

import com.sparta.myselectshop.dto.ItemDto;
import com.sparta.myselectshop.dto.ProductMypriceRequestDto;
import com.sparta.myselectshop.dto.ProductRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private int lprice;

    @Column(nullable = false)
    private int myprice;

    public Product(ProductRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.image = requestDto.getImage();
        this.link = requestDto.getLink();
        this.lprice = requestDto.getLprice();
        this.myprice = 0;
    }

    public void update(ProductMypriceRequestDto requestDto) {
        this.myprice = requestDto.getMyprice();
    }

    public void updateByItemDto(ItemDto itemDto) {
        this.lprice = itemDto.getLprice();
    }
}
