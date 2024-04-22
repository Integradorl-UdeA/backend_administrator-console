package com.consola.lis.dto;

import com.consola.lis.model.enums.LoanType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class LoanDTO {
    private String itemId;
    private int quantity;
    private String borrowerId;
    private String lenderId;
    private LoanType loanType;
   // private Date loanDate;
    private Date returnDate;
    private String observation;
}
