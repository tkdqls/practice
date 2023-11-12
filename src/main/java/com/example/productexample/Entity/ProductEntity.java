package com.example.productexample.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name="good")
@SequenceGenerator(
        name = "good_SEQ",
        sequenceName = "good_SEQ",
        initialValue = 1,
        allocationSize = 1
)
public class ProductEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "good_SEQ")
    private Integer product_id; //상품번호

    @Column(name = "good_id")
    private Integer good_id; //추천고유번호

    @Column(name = "categorynumber")
    private Integer categorynumber;  //카테고리고유번호

    @Column(name = "product_name", nullable = false)
    private String product_name;//상품명

    @Column(name = "product_detail")
    private String product_detail;  //상품설명

    @Column(name = "product_price")
    private Integer product_price;   //판매가

    @Column(name = "product_price2")
    private Integer product_price2; //소비자가

    @Column(name = "product_img")
    private String product_img;     //상품이미지

    @Column(name = "product_count")
    private Integer product_count;  //상품재고수량

    @Column(name = "product_sale")
    private Integer product_sale;   //판매상태

    @Column(name = "product_option")
    private Integer product_option; //상품옵션

    @Column(name = "product_like")
    private Integer product_like;   //상품좋아요
}
