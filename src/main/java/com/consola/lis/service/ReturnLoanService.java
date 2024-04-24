package com.consola.lis.service;

import com.consola.lis.dto.ReturnLoanDTO;
import com.consola.lis.model.entity.ReturnLoan;
import com.consola.lis.model.repository.LoanRepository;
import com.consola.lis.model.repository.ReturnLoanRepository;
import com.consola.lis.util.exception.IsEmptyException;
import com.consola.lis.util.exception.NotExistingException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Builder
@Data
@Service
@AllArgsConstructor
public class ReturnLoanService {
    private final ReturnLoanRepository returnLoanRepository;
    private final LoanRepository loanRepository;


    public void createReturnLoan(ReturnLoanDTO returnLoanRequest){
        loanRepository.findById(returnLoanRequest.getLoanId()).orElseThrow(() -> new NotExistingException("409", HttpStatus.CONFLICT, "Loan not exists "));

        ReturnLoan returnLoan = ReturnLoan.builder()
                .loanId(returnLoanRequest.getLoanId())
                .borrowerUser(returnLoanRequest.getBorrowerUser())
                .lenderUser(returnLoanRequest.getLenderUser())
                .observation(returnLoanRequest.getObservation())
                .build();

        returnLoanRepository.save(returnLoan);
    }

    public List<ReturnLoan> getAllReturnsLoans(){
            List<ReturnLoan> allReturnLoans = returnLoanRepository.findAll();
            if(allReturnLoans.isEmpty()){
                throw new IsEmptyException("403", HttpStatus.FORBIDDEN, "No exists loans");
            }
            return allReturnLoans;

    }

}
