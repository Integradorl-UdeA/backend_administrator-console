package com.consola.lis.service;

import com.consola.lis.dto.LoanDTO;
import com.consola.lis.dto.LoanInfoDTO;
import com.consola.lis.model.entity.InventoryItem;
import com.consola.lis.model.entity.Loan;
import com.consola.lis.model.enums.LoanState;
import com.consola.lis.model.enums.LoanType;
import com.consola.lis.model.enums.ItemState;
import com.consola.lis.model.repository.InventoryItemRepository;
import com.consola.lis.model.repository.LoanRepository;
import com.consola.lis.util.exception.AlreadyExistsException;
import com.consola.lis.util.exception.IllegalParameterInRequest;
import com.consola.lis.util.exception.IsEmptyException;
import com.consola.lis.util.exception.NotExistingException;
import com.consola.lis.util.mapper.LoanMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final InventoryItemRepository inventoryItemRepository;
    private final InventoryItemService inventoryItemService;

    public Loan createLoan(LoanDTO loanRequest) {

        if(!inventoryItemService.existItem(loanRequest.getItemId())) {
            throw new AlreadyExistsException("409", HttpStatus.CONFLICT, "Item no exists into inventory");
        }

        InventoryItem item = inventoryItemService.findInventoryItem(loanRequest.getItemId());
        if(!item.getLendable()) {
            throw new IllegalParameterInRequest("400", HttpStatus.BAD_REQUEST, "This item not is lendable");
        }

        if(loanRequest.getQuantity()>item.getTotal()){
            throw new IllegalParameterInRequest("400", HttpStatus.BAD_REQUEST, "The quantity is greater than the stock");
        }

        if (!item.getCategory().getQuantizable()) {
            inventoryItemService.updateInventoryItemState(loanRequest.getItemId(), ItemState.LENDED);
        }

        if (item.getCategory().getQuantizable() && item.getTotal() - loanRequest.getQuantity() == 0) {
            inventoryItemService.updateInventoryItemState(loanRequest.getItemId(), ItemState.OUT_OF_STOCK);
        }

        inventoryItemService.updateInventoryItemTotal(loanRequest);
        if(loanRequest.getLoanType()==null) loanRequest.setLoanType(LoanType.GENERAL);

        Loan loan = Loan.builder()
                .itemId(loanRequest.getItemId())
                .loanType(LoanType.valueOf(loanRequest.getLoanType().name()))
                .borrowerUser(loanRequest.getBorrowerUser())
                .lenderUser(loanRequest.getLenderUser())
                .quantity(loanRequest.getQuantity())
                .loanState(LoanState.valueOf(LoanState.ACTIVE.name()))
                .observation(loanRequest.getObservation())
                .returnDate(loanRequest.getReturnDate())
                .build();

        return  loanRepository.save(loan);


    }

    public void deleteLoan(int loanId){
        if(!existLoan(loanId)){
            throw new NotExistingException("409", HttpStatus.CONFLICT, "Loan not exists ");
        }
        loanRepository.deleteById(loanId);
    }

    public Loan getOneLoan(int loanId) {
        return loanRepository.findById(loanId)
                .orElseThrow(() -> new NotExistingException("409", HttpStatus.CONFLICT, "Loan not exists "));

    }

    private boolean existLoan(int loanId){
        return loanRepository.existsById(loanId);
    }

    public List<Loan> getAllLoans() {
        List<Loan> allLoans = loanRepository.findAll();
        if(allLoans.isEmpty()){
            throw new IsEmptyException("403", HttpStatus.FORBIDDEN, "No exists loans");
        }
        return allLoans;
    }



    public Map<String, Object> getAllLoansMapper() {
        List<LoanInfoDTO> loans = getAllLoansMap();
        Map<String, Object> result = new HashMap<>();
        result.put("header", createHeader());
        result.put("allRegisters", loans);
        return result;
    }

    private List<String> createHeader() {
        List<String> header = new ArrayList<>();
        header.add("Id");
        header.add("Elemento");
        header.add("Estado");
        header.add("Usuario");
        header.add("Tipo");
        header.add("Fecha Prestamo");
        header.add("Fecha Devolución");
        return header;
    }

    public List<LoanInfoDTO> getAllLoansMap() {
        List<Loan> allLoans = getAllLoans();
        List<LoanInfoDTO> loans = new ArrayList<>();

        allLoans.forEach(loan -> {
            InventoryItem item = inventoryItemService.findInventoryItem(loan.getItemId());
            loans.add(LoanMapper.mapLoanToDTO(loan,item));

        });

        return loans;
    }



}
