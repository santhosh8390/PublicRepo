package com.iiht.fse.SellerService.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.NonFinal;
import org.bson.types.ObjectId;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NonFinal
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {

    String id;

    @NotNull(message = "product Name cannot be empty")
    String name;

    @NotNull(message = "Short description cannot be empty")
    String shortDesc;

    @NotNull(message = "detailed description cannot be empty")
    String detailedDesc;

    @NotNull(message = "Category cannot be empty")
    Category category;

    @NotNull(message = "Starting price cannot be empty")
    BigDecimal startingPrice;

    @NotNull(message = "Bid end date cannot be empty")
    @Future(message = "Bid end date must be future date")
    Date bidEndDate;

    public Product(){
        id = new ObjectId().toString();
    }

}
