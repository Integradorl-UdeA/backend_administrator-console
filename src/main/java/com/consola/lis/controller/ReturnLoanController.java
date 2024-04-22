package com.consola.lis.controller;


import com.consola.lis.dto.ReturnLoanDTO;
import com.consola.lis.model.entity.ReturnLoan;
import com.consola.lis.service.ReturnLoanService;
import com.consola.lis.util.constans.EndpointConstant;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Manage Return Loans", description = "something")
@RestController
@RequestMapping(EndpointConstant.ENDPOINT_RETURN_LOAN)
@RequiredArgsConstructor
public class ReturnLoanController {

    private final ReturnLoanService returnLoanService;
    @PostMapping
    public void returnLoan(ReturnLoanDTO returnLoanDTO){
        returnLoanService.createReturnLoan(returnLoanDTO);
    }

    @GetMapping(EndpointConstant.ENDPOINT_RETURN_ALL_LOANS)
    public List<ReturnLoan> getAllReturnsLoans(){
        return returnLoanService.getAllReturnsLoans();
    }
}
