package com.iiht.fse.SellerService.exception;

public class SellerSystemException extends RuntimeException {

    public SellerSystemException(Throwable cause, String message)
    {
        super(message, cause);
    }
}
