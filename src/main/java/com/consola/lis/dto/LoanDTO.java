package com.consola.lis.dto;

import com.consola.lis.model.enums.LoanType;
import com.consola.lis.util.deserializer.LoanTypeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class LoanDTO {
    private String itemId;


    @Min(value = 1, message = "The quantity must be at least 1")
    private int quantity;

    @NotBlank(message = "The borrower user is required")
    private String borrowerUser;

    @NotBlank(message = "The lender user is required")
    private String lenderUser;

    @JsonDeserialize(using = LoanTypeDeserializer.class)
    private LoanType loanType;

    @NotBlank(message = "The return date is required")
    private Date returnDate;

    private String observation;


}
