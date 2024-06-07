package com.consola.lis.service;

import com.consola.lis.dto.ReturnLoanDTO;
import com.consola.lis.model.repository.LoanRepository;
import com.consola.lis.model.repository.ReturnLoanRepository;
import com.consola.lis.util.exception.NotExistingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReturnLoanServiceTest {

    @Mock
    ReturnLoanRepository returnLoanRepository;

    @Mock
    LoanRepository loanRepository;

    @Mock
    LoanService loanService;

    @Mock
    InventoryItemService inventoryItemService;

    @InjectMocks
    ReturnLoanService returnLoanService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void createReturnLoan_WhenLoanDoesNotExist() {
        ReturnLoanDTO returnLoanRequest = new ReturnLoanDTO();
        returnLoanRequest.setLoanId(1);

        when(loanService.existLoan(returnLoanRequest.getLoanId())).thenReturn(true);

        assertThrows(NotExistingException.class, () -> returnLoanService.createReturnLoan(returnLoanRequest));
    }

    @Test
    void getAllReturnsLoans_ReturnsAllLoans() {
        returnLoanService.getAllReturnsLoans();

        verify(returnLoanRepository, times(1)).findAll();
    }
}