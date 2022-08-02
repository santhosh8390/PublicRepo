package com.iiht.fse.BuyerService.controller;

import com.iiht.fse.BuyerService.model.BuyerRequest;
import com.iiht.fse.BuyerService.model.BuyerResponse;
import com.iiht.fse.BuyerService.service.BuyerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/e-auction/api/v1/buyer")
public class BuyerController {

    private final BuyerService buyerService;

    @GetMapping
    public ResponseEntity<BuyerResponse> fetchBidDetails(@RequestParam final String productId) {
        return ResponseEntity.ok(buyerService.fetchBidDetails(productId));
    }
}
