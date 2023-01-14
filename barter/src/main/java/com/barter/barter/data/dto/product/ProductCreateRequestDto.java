package com.barter.barter.data.dto.product;


import com.barter.barter.data.AreaCategory;
import com.barter.barter.data.ProductCategory;
import com.barter.barter.data.State;
import com.barter.barter.data.entity.ProductEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductCreateRequestDto {

    private String title;
    private String content;
    private String userId;
    private ProductCategory productCategory;
    private AreaCategory areaCategory;

    // userID는 서비스단에서 찾아주기
    public ProductEntity toEntity() {
        return ProductEntity.builder()
                .title(title)
                .content(content)
                .productCategory(productCategory)
                .areaCategory(areaCategory)
                .state(State.SELLING)
                .build();

    }
}
