package com.barter.barter.data.dto.product;

import com.barter.barter.data.AreaCategory;
import com.barter.barter.data.ProductCategory;
import com.barter.barter.data.State;
import com.barter.barter.data.dto.UserDTO;
import com.barter.barter.data.entity.UserEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponseDto {

    private Long id;
    private String title;
    private String content;

    private ProductCategory productCategory;
    private AreaCategory areaCategory;
    private State state;

    private UserDTO user;
}
