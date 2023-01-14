package com.barter.barter.data.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "prdocut_img")
public class ProductImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String src;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;
}
