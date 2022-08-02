package com.iiht.fse.SellerService.constant;

import org.springframework.http.HttpStatus;

public class SellerServiceConstants {

    public final static String INTERNAL_SERVER_HTTP_ERROR_CODE = HttpStatus.INTERNAL_SERVER_ERROR.toString();

    public final static String NOT_FOUND_HTTP_ERROR_CODE       = HttpStatus.NOT_FOUND.toString();

    public final static String BAD_REQUEST_HTTP_ERROR_CODE     = HttpStatus.BAD_REQUEST.toString();

    public final static String REQUEST_NOT_VALID_MESSAGE_CODE  = "input.request.not.valid";

    public final static String CANNOT_DELETE_AFTER_BIDENDDATE_MESSAGE_CODE  = "cannot.delete.after.bidenddate";

    public final static String CANNOT_DELETE_WHEN_PRODUCT_BID_AVAILABLE_MESSAGE_CODE  = "cannot.delete.product.bid.available";

    public final static String CANNOT_FETCH_BIDS_FOR_INVALID_PRODUCT_MESSAGE_CODE  = "cannot.fetch.bids.for.invalid.product";

    public final static String RECORDS_NOT_FOUND_CODE = "records.not.found";

    public static final String DATABASE_FETCH_MESSAGE_CODE = "database.fetch.issue";

    public final static String CANNOT_DELETE_AFTER_BIDENDDATE_ERROR_CODE    = "SEL90001";

    public final static String CANNOT_DELETE_WHEN_PRODUCT_BID_AVAILABLE_ERROR_CODE    = "SEL90002";

    public final static String CANNOT_FETCH_BIDS_FOR_INVALID_PRODUCT_ERROR_CODE    = "SEL90003";
}
