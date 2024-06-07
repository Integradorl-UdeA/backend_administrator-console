package com.consola.lis.service;

import com.consola.lis.dto.LoanDTO;
import com.consola.lis.model.entity.InventoryItem;
import com.consola.lis.model.entity.Loan;
import com.consola.lis.model.repository.InventoryItemRepository;
import com.consola.lis.model.repository.LoanRepository;
import com.consola.lis.util.exception.IllegalParameterInRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoanServiceTest {

    @Mock
    LoanRepository loanRepository;

    @Mock
    InventoryItemService inventoryItemService;

    @Mock
    InventoryItemRepository inventoryItemRepository;

    @InjectMocks
    LoanService loanService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testCreateLoan_ItemNotLendable() {
        LoanDTO loanRequest = new LoanDTO();
        loanRequest.setItemId(String.valueOf(1));
        loanRequest.setQuantity(1);

        InventoryItem item = new InventoryItem();
        item.setLendable(false);

        when(inventoryItemService.existItem(loanRequest.getItemId())).thenReturn(true);
        when(inventoryItemService.findInventoryItem(loanRequest.getItemId())).thenReturn(item);

        assertThrows(IllegalParameterInRequest.class, () -> loanService.createLoan(loanRequest));
    }

    @Test
    void testCreateLoan_QuantityGreaterThanStock() {
        LoanDTO loanRequest = new LoanDTO();
        loanRequest.setItemId(String.valueOf(1));
        loanRequest.setQuantity(3);

        InventoryItem item = new InventoryItem();
        item.setTotal(2);
        item.setLendable(true);

        when(inventoryItemService.existItem(loanRequest.getItemId())).thenReturn(true);
        when(inventoryItemService.findInventoryItem(loanRequest.getItemId())).thenReturn(item);

        assertThrows(IllegalParameterInRequest.class, () -> loanService.createLoan(loanRequest));
    }


    // ... existing tests ...

    @Test
    void testDeleteLoan_LoanExists() {
        int loanId = 1;
        when(loanRepository.existsById(loanId)).thenReturn(true);

        loanService.deleteLoan(loanId);

        verify(loanRepository, times(1)).deleteById(loanId);
    }

    @Test
    void testGetOneLoan_LoanExists() {
        int loanId = 1;
        Loan loan = new Loan();
        when(loanRepository.findById(loanId)).thenReturn(Optional.of(loan));

        Loan result = loanService.getOneLoan(loanId);

        assertEquals(loan, result);
        verify(loanRepository, times(1)).findById(loanId);
    }

    @Test
    void testExistLoan_LoanExists() {
        int loanId = 1;
        when(loanRepository.existsById(loanId)).thenReturn(false);

        boolean result = loanService.existLoan(loanId);

        assertTrue(result);
        verify(loanRepository, times(1)).existsById(loanId);
    }

    @Test
    void testGetAllLoans() {
        List<Loan> loans = new ArrayList<>();
        when(loanRepository.findAll()).thenReturn(loans);

        List<Loan> result = loanService.getAllLoans();

        assertEquals(loans, result);
        verify(loanRepository, times(1)).findAll();
    }

    @Test
    void testGetAllLoansMapper() {
        Page<Loan> page = mock(Page.class);
        when(loanRepository.findAllLoansByState(any(), any())).thenReturn(page);

        Map<String, Object> result = loanService.getAllLoansMapper(any(), any());

        assertNotNull(result);
        verify(loanRepository, times(1)).findAllLoansByState(any(), any());
    }

    @Test
    void testUpdateReturnLoanState() {
        int loanId = 1;
        Loan loan = new Loan();
        when(loanRepository.findById(loanId)).thenReturn(Optional.of(loan));

        loanService.updateReturnLoanState(loanId, any());

        verify(loanRepository, times(1)).save(loan);
    }
}
