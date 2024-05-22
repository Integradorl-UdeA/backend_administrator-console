package com.consola.lis.dto;

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
    private String loanState;
    private String borrowerUser;
    private String loanType;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date loanDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date returnDate;
}
