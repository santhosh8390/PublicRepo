package com.iiht.fse.BuyerService.service.impl;

import com.iiht.fse.BuyerService.exception.BuyerSystemException;
import com.iiht.fse.BuyerService.model.*;
import com.iiht.fse.BuyerService.repository.BuyerRepository;
import com.iiht.fse.BuyerService.service.BuyerService;
import com.iiht.fse.BuyerService.util.BuyerServiceUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuyerServiceImpl implements BuyerService {

    private final BuyerRepository buyerRepository;

    private final MessageSource messageSource;

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
}
