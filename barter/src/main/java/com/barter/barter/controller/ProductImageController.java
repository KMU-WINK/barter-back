package com.barter.barter.controller;

import com.barter.barter.data.dto.product.ProductImageDto;
import com.barter.barter.service.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product/img")
public class ProductImageController {

    private final ProductImageService productImageService;

    @GetMapping(value = "/search")
    public ResponseEntity<List<ProductImageDto>> searchAll(@RequestParam Long product_id){
        try{
            List<ProductImageDto> imageList= productImageService.searchAll(product_id);
            return new ResponseEntity<List<ProductImageDto>>(imageList, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteImg(@PathVariable Long id){
        try{
            productImageService.deleteImg(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProductImageDto> postImg(@RequestBody ProductImageDto imageDto){
        try {
            ProductImageDto productImageDto = productImageService.postImg(imageDto.getId(), imageDto.getSrc());
            return new ResponseEntity<ProductImageDto>(productImageDto, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
}
