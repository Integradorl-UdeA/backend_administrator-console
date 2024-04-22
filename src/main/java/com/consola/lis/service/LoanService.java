package com.consola.lis.service;

import com.consola.lis.dto.LoanDTO;
import com.consola.lis.model.entity.InventoryItem;
import com.consola.lis.model.entity.Loan;
import com.consola.lis.model.enums.StateItem;
import com.consola.lis.model.repository.InventoryItemRepository;
import com.consola.lis.model.repository.LoanRepository;
import com.consola.lis.util.exception.AlreadyExistsException;
import com.consola.lis.util.exception.IllegalParameterInRequest;
import com.consola.lis.util.exception.IsEmptyException;
import com.consola.lis.util.exception.NotExistingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final InventoryItemRepository inventoryItemRepository;
    private final InventoryItemService inventoryItemService;

    public void createLoan(LoanDTO loanRequest) {

        if(!inventoryItemService.existItem(loanRequest.getItemId())) {
            throw new AlreadyExistsException("409", HttpStatus.CONFLICT, "Item no exists into inventary");
        }

        InventoryItem generalItem = inventoryItemService.findInventoryItem(loanRequest.getItemId());
        if(!generalItem.getLendable()) {
            throw new IllegalParameterInRequest("400", HttpStatus.BAD_REQUEST, "This item not is lendable");
        }

        if(loanRequest.getQuantity()>generalItem.getTotal()){
            throw new IllegalParameterInRequest("400", HttpStatus.BAD_REQUEST, "The quantity is greater than the stock");
        }

        if (!generalItem.getCategory().getQuantizable()) {
            inventoryItemService.updateInventoryItemState(loanRequest.getItemId(), StateItem.LENDED);
            inventoryItemService.updateInventoryItemTotal(loanRequest);
        }

        Loan loan = Loan.builder()
                .itemId(loanRequest.getItemId())
                .loanType(loanRequest.getLoanType())
                .borrowerId(loanRequest.getBorrowerId())
                .lenderId(loanRequest.getLenderId())
                .quantity(loanRequest.getQuantity())
                .observation(loanRequest.getObservation())
                .returnDate(loanRequest.getReturnDate())
                .build();

        loanRepository.save(loan);

    }

    public void deleteLoan(int loanId){
        existLoan(loanId);
        loanRepository.deleteById(loanId);
    }

    public Loan getOneLoan(int loanId) {
        return loanRepository.findById(loanId)
                .orElseThrow(() -> new NotExistingException("409", HttpStatus.CONFLICT, "Loan not exists "));

    }

    private void existLoan(int loanId){
        loanRepository.findById(loanId)
                .orElseThrow(() -> new NotExistingException("409", HttpStatus.CONFLICT, "Loan not exists "));
    }

    public List<Loan> getAllLoans() {
        List<Loan> allLoans = loanRepository.findAll();
        if(allLoans.isEmpty()){
            throw new IsEmptyException("403", HttpStatus.FORBIDDEN, "No exists loans");
        }
        return allLoans;
    }

//    public Map<String, Object> getAllLoansMapper() {
//
//    }
}
