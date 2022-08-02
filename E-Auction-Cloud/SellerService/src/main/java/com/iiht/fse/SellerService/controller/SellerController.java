package com.iiht.fse.SellerService.controller;

import com.iiht.fse.SellerService.model.SellerResponse;
import com.iiht.fse.SellerService.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/e-auction/api/v1/seller")
public class SellerController {

    private final SellerService sellerService;

    @DeleteMapping(path= "/{productId}")
    public ResponseEntity<SellerResponse> deleteProduct(@PathVariable(value = "productId") final String productId) {
        return ResponseEntity.ok(sellerService.deleteProduct(productId));
    }
}
