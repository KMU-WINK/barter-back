package com.barter.barter.data.dto.product;

import com.barter.barter.data.AreaCategory;
import com.barter.barter.data.ProductCategory;
import com.barter.barter.data.State;
import com.barter.barter.data.dto.user.UserDTO;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductResponseDto {

    private Long id;
    private String title;
    private String content;

    private ProductCategory productCategory;
    private AreaCategory areaCategory;
    private State state;

    private List<ProductImageDto> img;

    private UserDTO user;
}
