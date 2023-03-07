package com.barter.barter.data.entity;


import com.barter.barter.data.dto.product.ProductImageDto;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "product_img")
public class ProductImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String src;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    public ProductImageDto toDto(){
        return ProductImageDto.builder()
                .id(id)
                .src(src)
                .build();
    }
}
