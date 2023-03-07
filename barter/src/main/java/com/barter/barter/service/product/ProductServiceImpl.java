package com.barter.barter.service.product;

import com.barter.barter.data.ProductCategory;
import com.barter.barter.data.State;
import com.barter.barter.data.dto.product.ProductCreateRequestDto;
import com.barter.barter.data.dto.product.ProductImageDto;
import com.barter.barter.data.dto.product.ProductResponseDto;
import com.barter.barter.data.entity.ProductEntity;
import com.barter.barter.data.entity.ProductImageEntity;
import com.barter.barter.data.entity.UserEntity;
import com.barter.barter.data.repository.ProductImageRepository;
import com.barter.barter.data.repository.ProductRepository;
import com.barter.barter.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final UserRepository userRepository;

    @Override
    public List<ProductResponseDto> serachAll() {
        return productRepository.findAll().stream().map(ProductEntity::toDto).collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto detailProduct(Long id) {
        return productRepository.findById(id).get().toDto();
    }

    @Override
    @Transactional
    public ProductResponseDto createProduct(ProductCreateRequestDto requestDto) {
        UserEntity user = userRepository.findByUser_id(requestDto.getUserId());
        ProductEntity product = ProductEntity.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .user(user)
                .state(State.SELLING)
                .productCategory(requestDto.getProductCategory())
                .areaCategory(requestDto.getAreaCategory())
                .build();
        productRepository.save(product);
        for(int i=0; i<requestDto.getImg().length; i++){
            ProductImageEntity productImage = ProductImageEntity.builder()
                    .src(requestDto.getImg()[i])
                    .product(product)
                    .build();
            productImageRepository.save(productImage);
        }
        List<ProductImageEntity> imageList= productImageRepository.findByProduct_Id(product.getId()).stream().toList();
        product.setProductImageList(imageList);
        productRepository.save(product);
        ProductResponseDto productResponseDto = product.toDto();
        return productResponseDto;
    }

    @Override
    public List<ProductResponseDto> searchProductByCategory(ProductCategory productCategory) {
        return productRepository.findAllByProductCategory(productCategory).stream().map(ProductEntity::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDto> searchProudctByTitle(String title) {
        return productRepository.findAllByTitleContains(title).stream().map(ProductEntity::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDto> searchProductByUserId(String id) {
        UserEntity user = userRepository.findByUser_id(id);
        return productRepository.findAllByUser(user).stream().map(ProductEntity::toDto).collect(Collectors.toList());
    }

    @Override
    public boolean deleteProduct(Long id) {
        productRepository.deleteById(id);
        return true;
    }
}
