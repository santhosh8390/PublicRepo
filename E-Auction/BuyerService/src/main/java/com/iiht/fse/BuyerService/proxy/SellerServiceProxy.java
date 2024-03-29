package com.iiht.fse.BuyerService.proxy;

import com.iiht.fse.BuyerService.model.SellerResponse;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="SellerService", url = "https://xwvm7xjpaj.execute-api.us-west-1.amazonaws.com/dev")
public interface SellerServiceProxy {

    @GetMapping(path= "/e-auction/api/v1/seller/{productId}")
    public ResponseEntity<SellerResponse> fetchProductDetails(@PathVariable("productId") final String productId);
}
