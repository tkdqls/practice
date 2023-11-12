package com.example.productexample;

import com.example.productexample.Entity.ProductEntity;
import com.example.productexample.Repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void register() {
        for (int i = 1; i <= 50; i++) {
            ProductEntity data = ProductEntity.builder()
                    .product_id(i)
                    .product_name("상품"+i)
                    .product_detail("설명"+i)
                    .product_price(10)
                    .build();

            productRepository.save(data);
        }
    }
}
