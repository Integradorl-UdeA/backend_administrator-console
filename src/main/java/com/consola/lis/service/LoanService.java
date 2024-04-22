package com.consola.lis.service;

import com.consola.lis.dto.LoanDTO;
import com.consola.lis.model.entity.Category;
import com.consola.lis.model.entity.GeneralItem;
import com.consola.lis.model.entity.Loan;
import com.consola.lis.model.enums.StateItem;
import com.consola.lis.model.repository.GeneralItemRepository;
import com.consola.lis.model.repository.LoanRepository;
import com.consola.lis.util.exception.AlreadyExistsException;
import com.consola.lis.util.exception.IllegalParameterInRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final GeneralItemRepository generalItemRepository;
    private final InventoryItemService inventoryItemService;

    public void createLoan(LoanDTO loanRequest) {

        if(!inventoryItemService.existItem(loanRequest.getItemId())) {
            throw new AlreadyExistsException("409", HttpStatus.CONFLICT, "Item no exists into inventary");
        }

        GeneralItem generalItem = inventoryItemService.findGeneralItem(loanRequest.getItemId());
        if(!generalItem.getLendable())
            throw new IllegalParameterInRequest("400", HttpStatus.BAD_REQUEST, "This item not is lendable");
        //falta corroborar el total pero eso va con el quantizable, mientras se arregla entonces lo se pone
        //sería generalItem.getTotal() > 0 and generalItem.getTotal()-loan.getQuantity() >= 0 así no se puede prestar
        //más de lo disponible y tampoco se puede prestar si no hay disponible

        //también si es prestable pero no quantizable entonces el estado del item debe pasar a LENDED
        if (!generalItem.getCategory().getQuantizable()) {
            inventoryItemService.updateGeneralItemState(loanRequest.getItemId(), StateItem.LENDED);
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

}
