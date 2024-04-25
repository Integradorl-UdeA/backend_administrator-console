package com.consola.lis.model.repository;

import com.consola.lis.model.entity.Loan;
import com.consola.lis.model.enums.LoanState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan,Integer> {
    List<Loan> findByLoanState(LoanState loanState);
}
