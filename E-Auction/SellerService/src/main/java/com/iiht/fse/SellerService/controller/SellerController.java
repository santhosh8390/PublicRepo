package com.iiht.fse.SellerService.controller;

import com.iiht.fse.SellerService.model.SellerRequest;
import com.iiht.fse.SellerService.model.SellerResponse;
import com.iiht.fse.SellerService.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/e-auction/api/v1/seller")
public class SellerController {

    private final SellerService sellerService;

    @PostMapping(path= "/add-product", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SellerResponse> addProduct(@RequestBody SellerRequest sellerRequest)
    {
        return new ResponseEntity<>(sellerService.addNewProduct(sellerRequest), HttpStatus.OK);
    }

    @GetMapping(path= "/show-bids/{productId}")
    public ResponseEntity<SellerResponse> fetchBids(@PathVariable("productId") final String productId) {
        return new ResponseEntity<>(sellerService.fetchBids(productId), HttpStatus.OK);
    }

    @GetMapping(path= "/{productId}")
    public ResponseEntity<SellerResponse> fetchProductDetails(@PathVariable("productId") final String productId) {
        return ResponseEntity.ok(sellerService.fetchProduct(productId));
    }

    @GetMapping(path= "/products")
    public ResponseEntity<SellerResponse> fetchAllProducts() {
        return ResponseEntity.ok(sellerService.fetchAllProducts());
    }

    @DeleteMapping(path= "/delete/{productId}")
    public ResponseEntity<SellerResponse> deleteProduct(@PathVariable("productId") final String productId) {
        return ResponseEntity.ok(sellerService.deleteProduct(productId));
    }
}
