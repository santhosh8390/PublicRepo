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

    @PostMapping(path= "/place-bid", consumes = "application/json", produces = "application/json")
    public ResponseEntity<BuyerResponse> placeBid(@RequestBody BuyerRequest requestInfo)
    {
        return new ResponseEntity<>(buyerService.placeBid(requestInfo), HttpStatus.OK);
    }

    @PutMapping(value = "update-bid/{productId}/{buyerEmailId}/{newBidAmount}",
                produces = {"application/json"}
                )
    public ResponseEntity updateBid(@PathVariable("productId") final String productId,
                                    @PathVariable("buyerEmailId") final String buyerEmailId,
                                    @PathVariable("newBidAmount") final BigDecimal newBidAmount) {
        return new ResponseEntity<>(buyerService.updateBid(productId, buyerEmailId, newBidAmount), HttpStatus.OK);
    }

    @GetMapping(path= "/{productId}")
    public ResponseEntity<BuyerResponse> fetchBidDetails(@PathVariable("productId") final String productId) {
        return ResponseEntity.ok(buyerService.fetchBidDetails(productId));
    }
}
