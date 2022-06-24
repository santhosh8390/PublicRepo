package com.iiht.fse.BuyerService.service.impl;

import com.iiht.fse.BuyerService.avro.BidDetails;
import com.iiht.fse.BuyerService.constant.BuyerServiceConstants;
import com.iiht.fse.BuyerService.exception.BuyerBusinessException;
import com.iiht.fse.BuyerService.exception.BuyerSystemException;
import com.iiht.fse.BuyerService.model.*;
import com.iiht.fse.BuyerService.proxy.SellerServiceProxy;
import com.iiht.fse.BuyerService.repository.BuyerRepository;
import com.iiht.fse.BuyerService.service.BuyerService;
import com.iiht.fse.BuyerService.util.BuyerServiceUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class BuyerServiceImpl implements BuyerService {

    private final BuyerRepository buyerRepository;

    private final MessageSource messageSource;

    private final SellerServiceProxy sellerServiceProxy;

    private final EventProducer eventProducer;

    private final ModelMapper modelMapper;

    @Override
    public BuyerResponse placeBid(BuyerRequest buyerRequest) {
        try {
            BuyerInfo buyerInfo = buyerRequest.getBuyerInfo();
            Product product = getProductById(buyerInfo.getProductId());
            if (product.getBidEndDate().before(new Date())) {
                return buildErrorResponse(BuyerServiceConstants.CANNOT_BID_AFTER_BIDENDDATE_ERROR_CODE, BuyerServiceConstants.CANNOT_BID_AFTER_BIDENDDATE_MESSAGE_CODE);
            }

            if (buyerRepository.findBuyerByProductIdAndEmail(buyerInfo.getProductId(), buyerInfo.getEmail()).isPresent()) {
                return buildErrorResponse(BuyerServiceConstants.CANNOT_BID_FOR_SAME_BUYER, BuyerServiceConstants.CANNOT_BID_FOR_SAME_USER_MESSAGE_CODE);
            }
            buyerRepository.save(buyerInfo);
            eventProducer.sendMessage(modelMapper.map(buyerInfo, BidDetails.class));
            return BuyerServiceUtil.buildSuccessResponse("New bid has been placed successfully", Arrays.asList(buyerInfo));
        } catch (ConstraintViolationException exception) {
            throw new BuyerBusinessException(exception.getMessage());
        } catch (RuntimeException exception) {
            throw new BuyerSystemException(exception, exception.getMessage());
        }
    }

    @Override
    public BuyerResponse fetchBidDetails(String productId) {
        if (StringUtils.isEmpty(productId)) {
            BuyerServiceUtil.logAndThrowBadRequestException(messageSource);
        }
        try {
            List<BuyerInfo> buyerInfo = buyerRepository.findBuyerByProductId(productId);
            return BuyerServiceUtil.buildSuccessResponse("Bid on the product fetched successfully", buyerInfo);
        } catch (RuntimeException exception) {
            throw new BuyerSystemException(exception, exception.getMessage());
        }
    }

    @Override
    public BuyerResponse updateBid(final String productId, final String buyerEmail, final BigDecimal newBidAmount) {
        try {
            BuyerInfo buyerInfo = buyerRepository.findBuyerByProductIdAndEmail(productId, buyerEmail).orElse(null);
            Product product = getProductById(productId);
            if (product.getBidEndDate().before(new Date())) {
                return buildErrorResponse(BuyerServiceConstants.CANNOT_UPDATE_BID_AMOUNT_BIDENDDATE_ERROR_CODE, BuyerServiceConstants.CANNOT_UPDATE_BID_AMOUNT_BIDENDDATE_MESSAGE_CODE);
            }
            buyerInfo.setBidAmount(newBidAmount);
            buyerRepository.save(buyerInfo);
            return BuyerServiceUtil.buildSuccessResponse("Bid has been updated successfully", Arrays.asList(buyerInfo));
        } catch (RuntimeException exception) {
            throw new BuyerSystemException(exception, exception.getMessage());
        }
    }

    private BuyerResponse buildErrorResponse(String errorCode, String messageCode) {
        BuyerErrorResponse response = new BuyerErrorResponse();
        response.setCode(errorCode);
        response.setMessage(messageSource.getMessage(messageCode, null, Locale.getDefault()));
        return BuyerServiceUtil.buildErrorResponse(response, HttpStatus.BAD_REQUEST.toString());
    }

    private Product getProductById(String productId) {
        if (StringUtils.isEmpty(productId)) {
            BuyerServiceUtil.logAndThrowBadRequestException(messageSource);
        }
        SellerInfo sellerInfo = sellerServiceProxy.fetchProductDetails(productId).getBody().getSellerInfo();
        return sellerInfo.getProducts().stream().findFirst().orElse(null);
    }
}
