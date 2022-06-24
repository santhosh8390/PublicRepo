package com.iiht.fse.BuyerService.controller;

import com.iiht.fse.BuyerService.constant.BuyerServiceConstants;
import com.iiht.fse.BuyerService.exception.BuyerBusinessException;
import com.iiht.fse.BuyerService.exception.BuyerSystemException;
import com.iiht.fse.BuyerService.model.BuyerErrorResponse;
import com.iiht.fse.BuyerService.model.BuyerResponse;
import com.iiht.fse.BuyerService.util.BuyerServiceUtil;

import com.mongodb.MongoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class ExceptionHandlingController {

    @Autowired
    MessageSource messageSource;

    @ExceptionHandler(BuyerSystemException.class)
    public ResponseEntity<BuyerResponse> systemException(BuyerSystemException exception)
    {
        ResponseEntity<BuyerResponse> responseEntity = null;
        BuyerErrorResponse error = new BuyerErrorResponse();
        error.setMessage(exception.getMessage());

        if (exception.getCause() instanceof NullPointerException)
        {
            error.setCode(messageSource.getMessage(BuyerServiceConstants.RECORDS_NOT_FOUND_CODE, null, Locale.getDefault()));
            responseEntity = new ResponseEntity<>(
                    BuyerServiceUtil.buildErrorResponse(error, BuyerServiceConstants.NOT_FOUND_HTTP_ERROR_CODE),
                    HttpStatus.NOT_FOUND);
        }
        else if (exception.getCause() instanceof RuntimeException
                || exception.getCause() instanceof MongoException)
        {
            error.setCode(messageSource.getMessage(BuyerServiceConstants.DATABASE_FETCH_MESSAGE_CODE, null, Locale.getDefault()));
            responseEntity = new ResponseEntity<>(
                    BuyerServiceUtil.buildErrorResponse(error, BuyerServiceConstants.INTERNAL_SERVER_HTTP_ERROR_CODE),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * Used to handle the RaboBusinessException occurs while processing the
     * input file. This will helps to frame the error response and return
     * contains the generic and service specific error code and error message
     * explaining the cause
     *
     * @param exception the exception object that has been passed from the
     *        occurrence
     * @return the error response contains the error cause
     */
    @ExceptionHandler(BuyerBusinessException.class)
    public ResponseEntity<BuyerResponse> businessException(BuyerBusinessException exception)
    {
        BuyerErrorResponse error = new BuyerErrorResponse();
      //  error.setCode(SellerServiceConstants.FIELD_EMPTY_CODE);
        error.setMessage(exception.getMessage());
        return new ResponseEntity<>(
                BuyerServiceUtil.buildErrorResponse(error, BuyerServiceConstants.BAD_REQUEST_HTTP_ERROR_CODE),
                HttpStatus.BAD_REQUEST);
    }
}
