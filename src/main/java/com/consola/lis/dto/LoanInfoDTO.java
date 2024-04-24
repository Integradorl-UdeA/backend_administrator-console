package com.consola.lis.dto;

import com.consola.lis.model.enums.LoanState;
import com.consola.lis.model.enums.LoanType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanInfoDTO {
    private int loanId;
    private String loanName;
    private LoanState loanState;
    private String borrowerUser;
    private LoanType loanType;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date loanDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date returnDate;
}
