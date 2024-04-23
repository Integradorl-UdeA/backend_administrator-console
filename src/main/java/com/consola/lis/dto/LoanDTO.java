package com.consola.lis.dto;

import com.consola.lis.model.enums.LoanType;
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
    private int quantity;
    private String borrowerUser;
    private String lenderUser;
    private LoanType loanType;
    private Date returnDate;
    private String observation;


}
