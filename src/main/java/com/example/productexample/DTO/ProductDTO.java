package com.example.productexample.DTO;

import lombok.*;
import lombok.extern.log4j.Log4j2;
import net.bytebuddy.asm.Advice;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
public class ProductDTO {
    private Integer product_id; //상품번호
    private Integer good_id; //추천고유번호
    private Integer categorynumber;  //카테고리고유번호
    @NotEmpty(message = "상품명은 생략할 수 없습니다.")
    private String product_name;   //상품명
    private String product_detail;  //상품설명
    private Integer product_price;   //판매가
    private Integer product_price2; //소비자가
    private String product_img;     //상품이미지
    private Integer product_count;  //상품재고수량
    private Integer product_sale;   //판매상태
    private Integer product_option; //상품옵션
    private Integer product_like;   //상품좋아요

}
