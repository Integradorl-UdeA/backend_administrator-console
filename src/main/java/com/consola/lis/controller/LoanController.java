package com.consola.lis.controller;


import com.consola.lis.dto.CategoryDTO;
import com.consola.lis.dto.LoanDTO;
import com.consola.lis.service.LoanService;
import com.consola.lis.util.constans.EndpointConstant;
import com.fasterxml.jackson.core.JacksonException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Manage Loans", description = "something")
@RestController
@RequestMapping(EndpointConstant.ENDPOINT_LOAN)
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createLoan(@RequestBody LoanDTO loan)  {
        loanService.createLoan(loan);

    }
}
