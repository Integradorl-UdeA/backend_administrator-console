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
                .loanId(loan.getLoanId())
                .loanType(loan.getLoanType())
                .loanState(loan.getLoanState())
                .borrowerUser(loan.getBorrowerUser())
                .loanDate(loan.getLoanDate())
                .loanName(item.getCategory().getCategoryName())
                .returnDate(loan.getReturnDate())
                .loanDate(loan.getLoanDate())
                .build();
    }




}
