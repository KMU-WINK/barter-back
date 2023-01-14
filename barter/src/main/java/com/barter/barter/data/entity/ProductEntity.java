package com.barter.barter.data.entity;

import com.barter.barter.data.AreaCategory;
import com.barter.barter.data.ProductCategory;
import com.barter.barter.data.State;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;
    @Enumerated(EnumType.STRING)
    private AreaCategory areaCategory;
    @Enumerated(EnumType.STRING)
    private State state;
}
