package com.sparta.myselectshop.scheduler;

import com.sparta.myselectshop.controller.NaverApiController;
import com.sparta.myselectshop.dto.ItemDto;
import com.sparta.myselectshop.entity.Product;
import com.sparta.myselectshop.repository.ProductRepository;
import com.sparta.myselectshop.service.NaverApiService;
import com.sparta.myselectshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class Scheduler {
    private final NaverApiService naverApiService;
    private final ProductService productService;
    private final ProductRepository productRepository;

    // 초, 분, 시, 일, 월, 주 순서
    @Scheduled(cron = "0 0 1 * * *")
    public void updatePrice() throws InterruptedException{
        log.info("가격 업데이트 실행");
        List<Product> productList = productRepository.findAll();
        for(Product product : productList){
            // 1초에 한 상품 씩 조회합니다(naver제한)
            TimeUnit.SECONDS.sleep(1);

            String title = product.getTitle();
            List<ItemDto> itemDtoList = naverApiService.searchItems(title); // 해당 관심상품의 title로 네이버 쇼핑에 재검색(키워드검색이 아닌 풀텍스트 검색)
            ItemDto itemDto = itemDtoList.get(0);                           // 해당 관심상품의 최저가 상품을 조회

            // i 번째 관심 상품 정보를 업데이트합니다.
            Long id = product.getId();
            productService.updateBySearch(id, itemDto);
        }
    }
}
