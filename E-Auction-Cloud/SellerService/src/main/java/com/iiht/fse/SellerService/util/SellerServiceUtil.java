package com.iiht.fse.SellerService.util;

import com.iiht.fse.SellerService.constant.SellerServiceConstants;
import com.iiht.fse.SellerService.exception.SellerBusinessException;
import com.iiht.fse.SellerService.model.*;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
@NoArgsConstructor
public class SellerServiceUtil {

    private MessageSource messageSource;

    public static SellerResponse buildSuccessResponse(String message, List<SellerInfo> sellerInfo)
    {
        SellerResponse sellerResponse;
        sellerResponse = new SellerResponse();
        sellerResponse.setStatusCode(HttpStatus.OK.toString());
        sellerResponse.setStatusMessage(HttpStatus.OK.name());
        sellerResponse.setMessage(message);
        sellerResponse.setSellerInfo(sellerInfo);
        if(null != sellerInfo && !sellerInfo.isEmpty()) {
            List<String> products = sellerInfo
                    .stream()
                    .map(seller -> seller.getProducts())
                    .collect(Collectors.toList())
                    .stream()
                    .flatMap(List::stream)
                    .map(product -> product.getId() + ',' +product.getName())
                    .collect(Collectors.toList());
            sellerResponse.setProducts(products);
        }
        return sellerResponse;
    }

    public static SellerResponse buildErrorResponse(SellerErrorResponse error,
                                                    String httpStatusCode)
    {
        SellerResponse sellerResponse = new SellerResponse();
        sellerResponse.setStatusCode(httpStatusCode);
        sellerResponse.setStatusMessage(fetchHttpStatusMessage(httpStatusCode).get(httpStatusCode));
        sellerResponse.setErrors(Arrays.asList(error));
        return sellerResponse;
    }

    private static Map<String, String> fetchHttpStatusMessage(String httpStatusCode)
    {
        Map<String, String> httpStatusMap = new HashMap<>();
        if (httpStatusCode.equals(SellerServiceConstants.INTERNAL_SERVER_HTTP_ERROR_CODE))
        {
            httpStatusMap.put(SellerServiceConstants.INTERNAL_SERVER_HTTP_ERROR_CODE,
                    HttpStatus.INTERNAL_SERVER_ERROR.name());
        }
        else if (httpStatusCode.equals(SellerServiceConstants.BAD_REQUEST_HTTP_ERROR_CODE))
        {
            httpStatusMap.put(SellerServiceConstants.BAD_REQUEST_HTTP_ERROR_CODE, HttpStatus.BAD_REQUEST.name());
        }
        else if (httpStatusCode.equals(SellerServiceConstants.NOT_FOUND_HTTP_ERROR_CODE))
        {
            httpStatusMap.put(SellerServiceConstants.NOT_FOUND_HTTP_ERROR_CODE, HttpStatus.NOT_FOUND.name());
        }
        return httpStatusMap;
    }

    public static void logAndThrowBadRequestException(SellerRequest sellerRequest,
                                                      MessageSource messageSource)
    {
        log.error("Input is not valid." + sellerRequest);
        throw new SellerBusinessException(
                messageSource.getMessage(SellerServiceConstants.REQUEST_NOT_VALID_MESSAGE_CODE, null, Locale.getDefault()));
    }

    public static void logAndThrowBadRequestException(MessageSource messageSource)
    {
        throw new SellerBusinessException(
                messageSource.getMessage(SellerServiceConstants.REQUEST_NOT_VALID_MESSAGE_CODE, null, Locale.getDefault()));
    }

    public static void logAndThrowBadRequestForDeleteAfterBidEndDate(MessageSource messageSource)
    {
        throw new SellerBusinessException(
                messageSource.getMessage(SellerServiceConstants.CANNOT_DELETE_AFTER_BIDENDDATE_MESSAGE_CODE, null, Locale.getDefault()));
    }
}
