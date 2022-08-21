package com.iiht.fse.SellerService.proxy;

import com.iiht.fse.SellerService.model.BuyerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="BuyerService", url = "https://5qloikojgd.execute-api.ap-south-1.amazonaws.com/sit/fetchProduct")
public interface BuyerServiceProxy {

    @GetMapping
    public ResponseEntity<BuyerResponse> fetchBidDetails(@RequestParam final String productId);
}
