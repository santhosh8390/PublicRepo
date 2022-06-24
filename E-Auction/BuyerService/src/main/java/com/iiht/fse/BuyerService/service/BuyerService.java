package com.iiht.fse.BuyerService.service;

import com.iiht.fse.BuyerService.model.BuyerRequest;
import com.iiht.fse.BuyerService.model.BuyerResponse;

import java.math.BigDecimal;

public interface BuyerService {

    BuyerResponse placeBid(BuyerRequest buyerRequest);

    BuyerResponse fetchBidDetails(final String productId);

    BuyerResponse updateBid(final String productId, final String buyerEmail, final BigDecimal newBidAmount);

}
