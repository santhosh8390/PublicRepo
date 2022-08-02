package com.iiht.fse.BuyerService.model;

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
public class BuyerRequest {

    BuyerInfo buyerInfo;
}
