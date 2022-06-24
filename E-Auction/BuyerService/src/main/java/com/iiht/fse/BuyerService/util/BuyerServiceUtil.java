package com.iiht.fse.BuyerService.util;

import com.iiht.fse.BuyerService.constant.BuyerServiceConstants;
import com.iiht.fse.BuyerService.exception.BuyerBusinessException;
import com.iiht.fse.BuyerService.model.BuyerErrorResponse;
import com.iiht.fse.BuyerService.model.BuyerInfo;
import com.iiht.fse.BuyerService.model.BuyerRequest;
import com.iiht.fse.BuyerService.model.BuyerResponse;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
@NoArgsConstructor
public class BuyerServiceUtil {

    private MessageSource messageSource;

    public static BuyerResponse buildSuccessResponse(String message, List<BuyerInfo> buyerInfo)
    {
        BuyerResponse buyerResponse;
        buyerResponse = new BuyerResponse();
        buyerResponse.setStatusCode(HttpStatus.OK.toString());
        buyerResponse.setStatusMessage(HttpStatus.OK.name());
        buyerResponse.setMessage(message);
        buyerResponse.setBuyerInfo(buyerInfo);
        return buyerResponse;
    }

    public static BuyerResponse buildErrorResponse(BuyerErrorResponse error,
                                                   String httpStatusCode)
    {
        BuyerResponse buyerResponse = new BuyerResponse();
        buyerResponse.setStatusCode(httpStatusCode);
        buyerResponse.setStatusMessage(fetchHttpStatusMessage(httpStatusCode).get(httpStatusCode));
        buyerResponse.setErrors(Arrays.asList(error));
        return buyerResponse;
    }

    private static Map<String, String> fetchHttpStatusMessage(String httpStatusCode)
    {
        Map<String, String> httpStatusMap = new HashMap<>();
        if (httpStatusCode.equals(BuyerServiceConstants.INTERNAL_SERVER_HTTP_ERROR_CODE))
        {
            httpStatusMap.put(BuyerServiceConstants.INTERNAL_SERVER_HTTP_ERROR_CODE,
                    HttpStatus.INTERNAL_SERVER_ERROR.name());
        }
        else if (httpStatusCode.equals(BuyerServiceConstants.BAD_REQUEST_HTTP_ERROR_CODE))
        {
            httpStatusMap.put(BuyerServiceConstants.BAD_REQUEST_HTTP_ERROR_CODE, HttpStatus.BAD_REQUEST.name());
        }
        else if (httpStatusCode.equals(BuyerServiceConstants.NOT_FOUND_HTTP_ERROR_CODE))
        {
            httpStatusMap.put(BuyerServiceConstants.NOT_FOUND_HTTP_ERROR_CODE, HttpStatus.NOT_FOUND.name());
        }
        return httpStatusMap;
    }

    public static void logAndThrowBadRequestException(BuyerRequest buyerRequest,
                                                      MessageSource messageSource)
    {
        log.error("Input is not valid." + buyerRequest);
        throw new BuyerBusinessException(
                messageSource.getMessage(BuyerServiceConstants.REQUEST_NOT_VALID_MESSAGE_CODE, null, Locale.getDefault()));
    }

    public static void logAndThrowBadRequestException(MessageSource messageSource)
    {
        throw new BuyerBusinessException(
                messageSource.getMessage(BuyerServiceConstants.REQUEST_NOT_VALID_MESSAGE_CODE, null, Locale.getDefault()));
    }

    public static void logAndThrowBadRequestForDeleteAfterBidEndDate(MessageSource messageSource)
    {
        throw new BuyerBusinessException(
                messageSource.getMessage(BuyerServiceConstants.CANNOT_BID_AFTER_BIDENDDATE_MESSAGE_CODE, null, Locale.getDefault()));
    }
}
