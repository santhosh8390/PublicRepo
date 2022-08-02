package com.iiht.fse.SellerService.proxy;

import com.iiht.fse.SellerService.model.BuyerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="BuyerService", url = "https://ygvkus2v15.execute-api.us-west-1.amazonaws.com/default/e-auction-fetch_bids")
public interface BuyerServiceProxy {

    @GetMapping
    public ResponseEntity<BuyerResponse> fetchBidDetails(@RequestParam final String productId);
}
