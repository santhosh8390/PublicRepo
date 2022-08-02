package com.iiht.fse.BuyerService.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.NonFinal;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NonFinal
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SellerResponse {
    String message;
    String statusCode;
    String statusMessage;
    SellerInfo sellerInfo;
    List<SellerErrorResponse> errors;
}
