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
    private int loan_id;
    private String loan_name;
    private LoanState loan_state;
    private String borrower_user;
    private LoanType loan_type;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date loan_date;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date return_date;
}
