package com.barter.barter.data.dto.product;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageDto {
    private Long id;
    private String src;
}
