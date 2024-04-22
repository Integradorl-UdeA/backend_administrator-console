package com.consola.lis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ReturnLoanDTO {
    private int loanId;
    private String borrowerId;
    private String lenderId;
    private String observation;

}
