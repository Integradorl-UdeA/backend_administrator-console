package com.consola.lis.service;

import com.consola.lis.dto.LoanDTO;
import com.consola.lis.dto.LoanInfoDTO;
import com.consola.lis.model.entity.InventoryItem;
import com.consola.lis.model.entity.Loan;
import com.consola.lis.model.enums.LoanState;
import com.consola.lis.model.enums.LoanType;
import com.consola.lis.model.enums.ItemState;
import com.consola.lis.model.repository.LoanRepository;
import com.consola.lis.util.exception.AlreadyExistsException;
import com.consola.lis.util.exception.IllegalParameterInRequest;
import com.consola.lis.util.exception.IsEmptyException;
import com.consola.lis.util.exception.NotExistingException;
import com.consola.lis.util.mapper.LoanMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final InventoryItemService inventoryItemService;


    public Loan createLoan(LoanDTO loanRequest) {
        validateLoanRequest(loanRequest);

        InventoryItem item = inventoryItemService.findInventoryItem(loanRequest.getItemId());
        updateItemStateAndTotal(item, loanRequest.getQuantity());

        Loan loan = buildLoanFromRequest(loanRequest);
        return loanRepository.save(loan);
    }

    private void validateLoanRequest(LoanDTO loanRequest) {
        if (!inventoryItemService.existItem(loanRequest.getItemId())) {
            throw new AlreadyExistsException("409", HttpStatus.CONFLICT, "Item does not exist in inventory");
        }

        InventoryItem item = inventoryItemService.findInventoryItem(loanRequest.getItemId());

        if (Boolean.FALSE.equals(item.getLendable())) {
            throw new IllegalParameterInRequest("400", HttpStatus.BAD_REQUEST, "This item is not lendable");
        }

        if (loanRequest.getQuantity() > item.getTotal()) {
            throw new IllegalParameterInRequest("400", HttpStatus.BAD_REQUEST, "The quantity is greater than the stock");
        }
    }

    private void updateItemStateAndTotal(InventoryItem item, int quantity) {
        inventoryItemService.changeStateNoQuantizableItem(item, ItemState.LENDED);

        if (Boolean.TRUE.equals(item.getCategory().getQuantizable()) && item.getTotal() - quantity == 0) {
            inventoryItemService.updateInventoryItemState(item.getItemId(), ItemState.OUT_OF_STOCK);
        }

        inventoryItemService.updateInventoryItemTotal(item.getItemId(), -quantity);
    }

    private Loan buildLoanFromRequest(LoanDTO loanRequest) {
        if (loanRequest.getLoanType() == null) {
            loanRequest.setLoanType(LoanType.GENERAL);
        }

        return Loan.builder()
                .itemId(loanRequest.getItemId())
                .loanType(LoanType.valueOf(loanRequest.getLoanType().name()))
                .borrowerUser(loanRequest.getBorrowerUser())
                .lenderUser(loanRequest.getLenderUser())
                .quantity(loanRequest.getQuantity())
                .loanState(LoanState.ACTIVE)
                .observation(loanRequest.getObservation())
                .returnDate(loanRequest.getReturnDate())
                .build();
    }

    public void deleteLoan(int loanId){
        if(existLoan(loanId)){
            throw new NotExistingException("409", HttpStatus.CONFLICT, "Loan not exists ");
        }
        loanRepository.deleteById(loanId);
    }

    public Loan getOneLoan(int loanId) {
        return loanRepository.findById(loanId)
                .orElseThrow(() -> new NotExistingException("409", HttpStatus.CONFLICT, "Loan not exists "));

    }

    public boolean existLoan(int loanId){
        return !loanRepository.existsById(loanId);
    }

    public List<Loan> getAllLoans() {
        List<Loan> allLoans = loanRepository.findAll();
        if(allLoans.isEmpty()){
            throw new IsEmptyException("403", HttpStatus.FORBIDDEN, "No exists loans");
        }
        return allLoans;
    }



    public Map<String, Object> getAllLoansMapper(Pageable pageable) {
        Page<Loan> loanPage = getAllLoans(pageable);

        Map<String, Object> result = new HashMap<>();
        result.put("totalElements", loanPage.getTotalElements());
        result.put("totalPages", loanPage.getTotalPages());
        result.put("currentPage", loanPage.getNumber());
        result.put("items", mapToLoanInfoList(loanPage.getContent()));
        return result;
    }

    private List<LoanInfoDTO> mapToLoanInfoList(List<Loan> loans) {
        return loans.stream()
                .map(loan -> LoanMapper.mapLoanToDTO(loan, inventoryItemService.findInventoryItem(loan.getItemId())))
                .collect(Collectors.toList());
    }

    public List<String> getHeaders() {
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
    private Page<Loan> getAllLoans(Pageable pageable) {
        return loanRepository.findAllLoans(pageable);
    }

    public void updateReturnLoanState (int loanId, LoanState state) {
        Loan existingLoan = loanRepository.findById(loanId)
                .orElseThrow(() -> new NotExistingException("409", HttpStatus.CONFLICT, "Item not exists into inventary"));

        existingLoan.setLoanState(state);
        loanRepository.save(existingLoan);
    }



}
