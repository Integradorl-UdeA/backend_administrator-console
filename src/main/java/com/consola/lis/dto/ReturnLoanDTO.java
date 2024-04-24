package com.consola.lis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReturnLoanDTO {
    private int loanId;
    private String borrowerUser;
    private String lenderUser;
    private String observation;

}
