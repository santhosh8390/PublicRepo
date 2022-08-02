package com.iiht.fse.SellerService.controller;

import com.iiht.fse.SellerService.constant.SellerServiceConstants;
import com.iiht.fse.SellerService.exception.SellerBusinessException;
import com.iiht.fse.SellerService.exception.SellerSystemException;
import com.iiht.fse.SellerService.model.SellerErrorResponse;
import com.iiht.fse.SellerService.model.SellerResponse;
import com.iiht.fse.SellerService.util.SellerServiceUtil;
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

    @ExceptionHandler(SellerSystemException.class)
    public ResponseEntity<SellerResponse> systemException(SellerSystemException exception)
    {
        ResponseEntity<SellerResponse> responseEntity = null;
        SellerErrorResponse error = new SellerErrorResponse();
        error.setMessage(exception.getMessage());
        if (exception.getCause() instanceof NullPointerException)
        {
            error.setCode(messageSource.getMessage(SellerServiceConstants.RECORDS_NOT_FOUND_CODE, null, Locale.getDefault()));
            responseEntity = new ResponseEntity<>(
                    SellerServiceUtil.buildErrorResponse(error, SellerServiceConstants.NOT_FOUND_HTTP_ERROR_CODE),
                    HttpStatus.NOT_FOUND);
        }
        else if (exception.getCause() instanceof RuntimeException
                || exception.getCause() instanceof MongoException)
        {
            error.setCode(messageSource.getMessage(SellerServiceConstants.DATABASE_FETCH_MESSAGE_CODE, null, Locale.getDefault()));
            responseEntity = new ResponseEntity<>(
                    SellerServiceUtil.buildErrorResponse(error, SellerServiceConstants.INTERNAL_SERVER_HTTP_ERROR_CODE),
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
    @ExceptionHandler(SellerBusinessException.class)
    public ResponseEntity<SellerResponse> businessException(SellerBusinessException exception)
    {
        SellerErrorResponse error = new SellerErrorResponse();
      //  error.setCode(SellerServiceConstants.FIELD_EMPTY_CODE);
        error.setMessage(exception.getMessage());
        return new ResponseEntity<>(
                SellerServiceUtil.buildErrorResponse(error, SellerServiceConstants.BAD_REQUEST_HTTP_ERROR_CODE),
                HttpStatus.BAD_REQUEST);
    }
}
