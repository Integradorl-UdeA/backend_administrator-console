package com.consola.lis.controller;


import com.consola.lis.dto.ReturnLoanDTO;
import com.consola.lis.model.entity.ReturnLoan;
import com.consola.lis.service.ReturnLoanService;
import com.consola.lis.util.constans.ApiDescription;
import com.consola.lis.util.constans.EndpointConstant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Manage Return Loans", description = ApiDescription.DESCRIPTION_CONTROLLER_RETURN_LOAN)
@RestController
@RequestMapping(EndpointConstant.ENDPOINT_RETURN_LOAN)
@RequiredArgsConstructor
public class ReturnLoanController {

    private final ReturnLoanService returnLoanService;

    @Operation(summary = ApiDescription.DESCRIPTION_GET_RETURN_LOAN)
    @PostMapping
    public void returnLoan(ReturnLoanDTO returnLoanDTO){
        returnLoanService.createReturnLoan(returnLoanDTO);
    }

    @Operation(summary = ApiDescription.DESCRIPTION_GET_RETURN_ALL_LOANS)
    @GetMapping
    public List<ReturnLoan> getAllReturnsLoans(){
        return returnLoanService.getAllReturnsLoans();
    }


}
