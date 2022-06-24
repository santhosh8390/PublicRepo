package com.iiht.fse.SellerService.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.NonFinal;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NonFinal
@Document(collection = "seller")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class

SellerInfo {

    @Id
    String id;

    @NotNull(message = "first name cannot be empty")
    @Size(min = 5,max = 30)
    String firstName;

    @NotNull(message = "last name cannot be empty")
    @Size(min = 5,max = 25)
    String lastName;

    @NotNull(message = "Address cannot be empty")
    String address;

    String city;

    String state;

    @NotNull(message = "Phone cannot be empty")
    @Pattern(regexp="^\\(?(\\d{10})\\)$",
            message="Mobile number is invalid")
    String phone;

    @NotNull(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    String email;

    @Valid
    List<Product> products;

}
