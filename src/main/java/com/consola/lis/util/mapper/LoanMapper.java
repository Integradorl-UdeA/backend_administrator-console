package com.consola.lis.util.mapper;

import com.consola.lis.dto.LoanInfoDTO;
import com.consola.lis.model.entity.InventoryItem;
import com.consola.lis.model.entity.Loan;

import java.util.Optional;

public class LoanMapper {

    private LoanMapper(){
    }

    public static LoanInfoDTO mapLoanToDTO(Loan loan, InventoryItem item){

        return LoanInfoDTO.builder()
                .loan_id(loan.getLoanId())
                .loan_type(loan.getLoanType())
                .loan_state(loan.getLoanState())
                .borrower_user(loan.getBorrowerUser())
                .loan_date(loan.getLoanDate())
                .loan_name(item.getCategory().getCategoryName())
                .return_date(loan.getReturnDate())
                .loan_date(loan.getLoanDate())
                .build();
    }




}
