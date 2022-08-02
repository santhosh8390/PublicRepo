package com.iiht.fse.SellerService.service.impl;

import com.iiht.fse.SellerService.constant.SellerServiceConstants;
import com.iiht.fse.SellerService.exception.SellerSystemException;
import com.iiht.fse.SellerService.model.*;
import com.iiht.fse.SellerService.proxy.BuyerServiceProxy;
import com.iiht.fse.SellerService.repository.SellerRepository;
import com.iiht.fse.SellerService.service.SellerService;
import com.iiht.fse.SellerService.util.SellerServiceUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;

    private final MessageSource messageSource;

    private final BuyerServiceProxy buyerServiceProxy;

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
            return SellerServiceUtil.buildSuccessResponse("Given product removed successfully", Arrays.asList(sellerInfo));
        } catch (RuntimeException exception) {
            throw new SellerSystemException(exception, exception.getMessage());
        }
    }

    private void logAndThrowBadRequest(String productId) {
        if (StringUtils.isEmpty(productId)) {
            SellerServiceUtil.logAndThrowBadRequestException(messageSource);
        }
    }

    private SellerResponse buildErrorResponse(final String errorCode, final String messageCode) {
        SellerErrorResponse response = new SellerErrorResponse();
        response.setCode(errorCode);
        response.setMessage(messageSource.getMessage(messageCode, null, Locale.getDefault()));
        return SellerServiceUtil.buildErrorResponse(response, HttpStatus.BAD_REQUEST.toString());
    }
}

