package com.consola.lis.controller;


import com.consola.lis.dto.CategoryDTO;
import com.consola.lis.dto.LoanDTO;
import com.consola.lis.model.entity.Loan;
import com.consola.lis.service.LoanService;
import com.consola.lis.util.constans.EndpointConstant;
import com.fasterxml.jackson.core.JacksonException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "Manage Loans", description = "something")
@RestController
@RequestMapping(EndpointConstant.ENDPOINT_LOAN)
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(EndpointConstant.ENDPOINT_CREATE_LOAN)
    public void createLoan(@RequestBody LoanDTO loan)  {
        loanService.createLoan(loan);

    }
    @DeleteMapping(EndpointConstant.ENDPOINT_DELETE_LOAN)
    public void deleteLoan(@PathVariable("loanId") int loanId){
        loanService.deleteLoan(loanId);
    }

    @GetMapping(EndpointConstant.ENDPOINT_GET_ONE_LOAN)
    public Loan getOneLoan(@PathVariable("loanId") int loanId){
        return loanService.getOneLoan(loanId);
    }

    @GetMapping
    public List<Loan> gelAllLoans(){
        return loanService.getAllLoans();
    }

//    @GetMapping(EndpointConstant.ENDPOINT_ALL_LOANS_TABLE)
//    public Map<String, Object> loans(){
//        return loanService.getAllLoansMapper();
//    }
}
