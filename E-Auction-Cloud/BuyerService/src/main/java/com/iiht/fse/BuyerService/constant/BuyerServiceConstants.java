package com.iiht.fse.BuyerService.constant;

import org.springframework.http.HttpStatus;

public class BuyerServiceConstants {

    public final static String INTERNAL_SERVER_HTTP_ERROR_CODE = HttpStatus.INTERNAL_SERVER_ERROR.toString();

    public final static String NOT_FOUND_HTTP_ERROR_CODE       = HttpStatus.NOT_FOUND.toString();

    public final static String BAD_REQUEST_HTTP_ERROR_CODE     = HttpStatus.BAD_REQUEST.toString();

    public final static String REQUEST_NOT_VALID_MESSAGE_CODE  = "input.request.not.valid";

    public final static String CANNOT_BID_AFTER_BIDENDDATE_MESSAGE_CODE  = "cannot.bid.after.bidenddate";

    public final static String CANNOT_BID_FOR_SAME_USER_MESSAGE_CODE  = "cannot.bid.for.same.user";

    public final static String CANNOT_UPDATE_BID_AMOUNT_BIDENDDATE_MESSAGE_CODE  = "cannot.update.bid.amount";

    public final static String RECORDS_NOT_FOUND_CODE = "records.not.found";

    public static final String DATABASE_FETCH_MESSAGE_CODE = "database.fetch.issue";

    public final static String CANNOT_BID_AFTER_BIDENDDATE_ERROR_CODE    = "BUY90001";

    public final static String CANNOT_BID_FOR_SAME_BUYER    = "BUY90002";

    public final static String CANNOT_UPDATE_BID_AMOUNT_BIDENDDATE_ERROR_CODE    = "BUY90001";
}
