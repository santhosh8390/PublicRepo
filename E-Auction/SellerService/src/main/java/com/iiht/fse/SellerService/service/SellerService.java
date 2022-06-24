package com.iiht.fse.SellerService.service;

import com.iiht.fse.SellerService.model.ProductBids;
import com.iiht.fse.SellerService.model.SellerRequest;
import com.iiht.fse.SellerService.model.SellerResponse;

import java.util.List;

public interface SellerService {

    SellerResponse addNewProduct(final SellerRequest sellerRequest);

    SellerResponse deleteProduct(final String productId);

    SellerResponse fetchProduct(final String productId);

    SellerResponse fetchBids(final String productId);
}
