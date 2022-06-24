package com.iiht.fse.SellerService.proxy;

import com.iiht.fse.SellerService.model.BuyerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "BUYERSERVICE", url = "http://localhost:8081/")
public interface BuyerServiceProxy {

    @GetMapping(path= "/e-auction/api/v1/buyer/{productId}")
    public ResponseEntity<BuyerResponse> fetchBidDetails(@PathVariable("productId") final String productId);
}
