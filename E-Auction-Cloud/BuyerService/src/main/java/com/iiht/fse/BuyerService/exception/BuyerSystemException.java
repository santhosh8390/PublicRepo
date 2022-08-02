package com.iiht.fse.BuyerService.exception;

public class BuyerSystemException extends RuntimeException {

    public BuyerSystemException(Throwable cause, String message)
    {
        super(message, cause);
    }
}
