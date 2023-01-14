package com.barter.barter.data.entity;

import com.barter.barter.data.AreaCategory;
import com.barter.barter.data.ProductCategory;
import com.barter.barter.data.State;
import com.barter.barter.data.dto.UserDTO;
import com.barter.barter.data.dto.product.ProductResponseDto;
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


    public ProductResponseDto toDto(){
        return ProductResponseDto.builder()
                .id(id)
                .title(title)
                .content(content)
                .productCategory(productCategory)
                .areaCategory(areaCategory)
                .state(state)
                .user(UserDTO.builder()
                        .id(user.id)
                        .name(user.name)
                        .password(user.password)
                        .nickname(user.nickname)
                        .build())
                .build();

    }
}
