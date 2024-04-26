package com.consola.lis.service;

import com.consola.lis.dto.ReturnLoanDTO;
import com.consola.lis.model.entity.InventoryItem;
import com.consola.lis.model.entity.Loan;
import com.consola.lis.model.entity.ReturnLoan;
import com.consola.lis.model.enums.ItemState;
import com.consola.lis.model.enums.LoanState;
import com.consola.lis.model.repository.LoanRepository;
import com.consola.lis.model.repository.ReturnLoanRepository;
import com.consola.lis.util.exception.NotExistingException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Builder
@Data
@Service
@AllArgsConstructor
public class ReturnLoanService {
    private final ReturnLoanRepository returnLoanRepository;
    private final LoanRepository loanRepository;
    private final LoanService loanService;
    private final InventoryItemService inventoryItemService;


    public void createReturnLoan(ReturnLoanDTO returnLoanRequest) {

        if (loanService.existLoan(returnLoanRequest.getLoanId())) {
            throw new NotExistingException("409", HttpStatus.CONFLICT, "Loan not exists ");
        }

        loanService.updateReturnLoanState(returnLoanRequest.getLoanId(), LoanState.RETURNED);
        Loan loan = loanService.getOneLoan(returnLoanRequest.getLoanId());
        InventoryItem item = inventoryItemService.findInventoryItem(loan.getItemId());

        if (Boolean.TRUE.equals(item.getCategory().getQuantizable()) && item.getTotal() == 0) {
            inventoryItemService.updateInventoryItemState(loan.getItemId(), ItemState.AVAILABLE);
        }

        inventoryItemService.updateInventoryItemTotal(loan.getItemId(), loan.getQuantity());
        inventoryItemService.changeStateNoQuantizableItem(item, ItemState.AVAILABLE);


        ReturnLoan returnLoan = ReturnLoan.builder()
                .loanId(returnLoanRequest.getLoanId())
                .borrowerUser(returnLoanRequest.getBorrowerUser())
                .lenderUser(returnLoanRequest.getLenderUser())
                .observation(returnLoanRequest.getObservation())
                .build();

        returnLoanRepository.save(returnLoan);
    }

    public List<ReturnLoan> getAllReturnsLoans() {
        return returnLoanRepository.findAll();
    }


}
