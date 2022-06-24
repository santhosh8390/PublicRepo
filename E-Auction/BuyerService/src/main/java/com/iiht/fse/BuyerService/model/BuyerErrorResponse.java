package com.iiht.fse.BuyerService.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.NonFinal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NonFinal
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BuyerErrorResponse {
    String code;
    String message;
}
