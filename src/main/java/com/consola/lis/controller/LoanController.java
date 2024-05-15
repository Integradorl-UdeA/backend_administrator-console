package com.consola.lis.controller;


import com.consola.lis.dto.LoanDTO;
import com.consola.lis.model.entity.Loan;
import com.consola.lis.service.LoanService;
import com.consola.lis.util.constans.ApiDescription;
import com.consola.lis.util.constans.EndpointConstant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.List;
import java.util.Map;

@Tag(name = "Manage Loans", description = ApiDescription.DESCRIPTION_CONTROLLER_LOAN)
@RestController
@RequestMapping(EndpointConstant.ENDPOINT_LOAN)
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;
    private final HandlerExceptionResolver handlerExceptionResolver;


    @Operation(summary = ApiDescription.DESCRIPTION_CREATE_LOAN)
    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestBody LoanDTO loanRequest)  {
        return new ResponseEntity<>(loanService.createLoan(loanRequest), HttpStatus.CREATED);
    }

    @Operation(summary = ApiDescription.DESCRIPTION_DELETE_LOAN)
    @DeleteMapping(EndpointConstant.ENDPOINT_DELETE_LOAN)
    public void deleteLoan(@PathVariable("loanId") int loanId){
        loanService.deleteLoan(loanId);
    }

    @Operation(summary = ApiDescription.DESCRIPTION_GET_ONE_LOAN)
    @GetMapping(EndpointConstant.ENDPOINT_GET_ONE_LOAN)
    public ResponseEntity<Loan> getOneLoan(@PathVariable("loanId") int loanId){
        return ResponseEntity.ok(loanService.getOneLoan(loanId));
    }

    @Operation(summary = ApiDescription.DESCRIPTION_LOAN)
    @GetMapping
    public List<Loan> gelAllLoans(){
        return loanService.getAllLoans();
    }

    @Operation(summary = ApiDescription.DESCRIPTION_ALL_LOANS_TABLE)
    @GetMapping(EndpointConstant.ENDPOINT_ALL_LOANS_TABLE)
    public Map<String, Object> loans(Pageable pageable){
        return loanService.getAllLoansMapper(pageable);
    }

    @Operation(summary = ApiDescription.DESCRIPTION_HEADERS_LOAN)
    @GetMapping(EndpointConstant.ENDPOINT_HEADERS_LOAN)
    public List<String> getHeaders(){
        return loanService.getHeaders();
    }
}
