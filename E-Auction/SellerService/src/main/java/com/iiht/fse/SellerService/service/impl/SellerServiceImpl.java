package com.iiht.fse.SellerService.service.impl;

import com.iiht.fse.SellerService.constant.SellerServiceConstants;
import com.iiht.fse.SellerService.exception.SellerBusinessException;
import com.iiht.fse.SellerService.exception.SellerSystemException;
import com.iiht.fse.SellerService.model.*;
import com.iiht.fse.SellerService.proxy.BuyerServiceProxy;
import com.iiht.fse.SellerService.repository.BidRepository;
import com.iiht.fse.SellerService.repository.SellerRepository;
import com.iiht.fse.SellerService.service.SellerService;
import com.iiht.fse.SellerService.util.SellerServiceUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;

    private final BidRepository bidRepository;

    private final MessageSource messageSource;

    private final BuyerServiceProxy buyerServiceProxy;

    @Override
    public SellerResponse addNewProduct(SellerRequest sellerRequest) {
        try {
            sellerRepository.save(sellerRequest.getSellerInfo());
            return SellerServiceUtil.buildSuccessResponse("New product added successfully", sellerRequest.getSellerInfo());
        } catch (ConstraintViolationException exception) {
            throw new SellerBusinessException(exception.getMessage());
        } catch (RuntimeException exception) {
            throw new SellerSystemException(exception, exception.getMessage());
        }
    }

    @Override
    public SellerResponse deleteProduct(final String productId) {
        logAndThrowBadRequest(productId);
        try {
            if (buyerServiceProxy.fetchBidDetails(productId).getBody().getBuyerInfo().size() != 0) {
                return buildErrorResponse(SellerServiceConstants.CANNOT_DELETE_WHEN_PRODUCT_BID_AVAILABLE_ERROR_CODE, SellerServiceConstants.CANNOT_DELETE_WHEN_PRODUCT_BID_AVAILABLE_MESSAGE_CODE);
            }
            SellerInfo sellerInfo = sellerRepository.findSellerByProductsId(productId);
            Predicate<Product> condition2 = product -> product.getBidEndDate().before(new Date());
            Predicate<Product> condition1 = product -> product.getId().equals(productId);
            List<Product> tempList = sellerInfo.getProducts().stream().filter(condition1.and(condition2)).collect(Collectors.toList());
            if (!tempList.isEmpty()) {
                return buildErrorResponse(SellerServiceConstants.CANNOT_DELETE_AFTER_BIDENDDATE_ERROR_CODE, SellerServiceConstants.CANNOT_DELETE_AFTER_BIDENDDATE_MESSAGE_CODE);
            }
            sellerInfo.getProducts().removeIf(condition1);
            sellerRepository.save(sellerInfo);
            return SellerServiceUtil.buildSuccessResponse("Given product removed successfully", sellerInfo);
        } catch (RuntimeException exception) {
            throw new SellerSystemException(exception, exception.getMessage());
        }
    }

    @Override
    public SellerResponse fetchProduct(final String productId) {
        logAndThrowBadRequest(productId);
        try {
            SellerInfo sellerInfo = sellerRepository.findSellerByProductsId(productId);
            return SellerServiceUtil.buildSuccessResponse("product Details fetched successfully", sellerInfo);
        } catch (RuntimeException exception) {
            throw new SellerSystemException(exception, exception.getMessage());
        }
    }

    private void logAndThrowBadRequest(String productId) {
        if (StringUtils.isEmpty(productId)) {
            SellerServiceUtil.logAndThrowBadRequestException(messageSource);
        }
    }

    @Override
    public SellerResponse fetchBids(final String productId) {
        logAndThrowBadRequest(productId);
        try {
            SellerInfo sellerInfo = sellerRepository.findSellerByProductsId(productId);
            Product product = sellerInfo.getProducts().stream().findFirst().orElse(null);
            if (product == null) {
                return buildErrorResponse(SellerServiceConstants.CANNOT_FETCH_BIDS_FOR_INVALID_PRODUCT_ERROR_CODE, SellerServiceConstants.CANNOT_FETCH_BIDS_FOR_INVALID_PRODUCT_MESSAGE_CODE);
            }

            Comparator<ProductBids> compareByBidAmount =
                    (ProductBids o1, ProductBids o2) -> o1.getBidAmount().compareTo(o2.getBidAmount());

            List<ProductBids> productBids = bidRepository.findByProductId(productId)
                    .stream()
                    .map(bid -> ProductBids.ProductBidsMapper.productBidsMapper()
                            .setProductId(productId)
                            .setName(product.getName())
                            .setShortDesc(product.getShortDesc())
                            .setDetailedDesc(product.getDetailedDesc())
                            .setCategory(product.getCategory())
                            .setStartingPrice(product.getStartingPrice())
                            .setBidEndDate(product.getBidEndDate())
                            .setBidAmount(bid.getBidAmount())
                            .setEmail(bid.getEmail())
                            .setPhone(bid.getPhone()).build())
                    .collect(Collectors.toList());
            Collections.sort(productBids, compareByBidAmount.reversed());
            if (productBids == null || productBids.isEmpty()) {
                return buildErrorResponse(SellerServiceConstants.CANNOT_FETCH_BIDS_FOR_INVALID_PRODUCT_ERROR_CODE, SellerServiceConstants.CANNOT_FETCH_BIDS_FOR_INVALID_PRODUCT_MESSAGE_CODE);
            }
            SellerResponse sellerResponse = SellerServiceUtil.buildSuccessResponse("Bids against the product fetched successfully", null);
            sellerResponse.setProductBids(productBids);
            return sellerResponse;
        } catch (RuntimeException exception) {
            throw new SellerSystemException(exception, exception.getMessage());
        }
    }

    @Override
    public SellerResponse fetchAllProducts() {
        try {
            List<SellerInfo> sellerInfoList = sellerRepository.findAll();
            SellerInfo sellerInfo = new SellerInfo();
            List<Product> products = new ArrayList();
            for(SellerInfo seller : sellerInfoList) {
                products.addAll(seller.getProducts());
            }
            sellerInfo.setProducts(products);
            return SellerServiceUtil.buildSuccessResponse("product Details fetched successfully", sellerInfo);
        } catch (RuntimeException exception) {
            throw new SellerSystemException(exception, exception.getMessage());
        }
    }

    private SellerResponse buildErrorResponse(final String errorCode, final String messageCode) {
        SellerErrorResponse response = new SellerErrorResponse();
        response.setCode(errorCode);
        response.setMessage(messageSource.getMessage(messageCode, null, Locale.getDefault()));
        return SellerServiceUtil.buildErrorResponse(response, HttpStatus.BAD_REQUEST.toString());
    }
}

