package com.consola.lis.model.repository;

import com.consola.lis.model.entity.Loan;
import com.consola.lis.model.enums.LoanState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan,Integer> {
    List<Loan> findByLoanState(LoanState loanState);

    @Query("SELECT p FROM Loan p ")
    Page<Loan> findAllLoans(Pageable pageable);


    @Query("SELECT p FROM Loan p WHERE (:loanState IS NULL OR p.loanState = :loanState) ORDER BY CASE WHEN p.loanState = 'ACTIVE' THEN 0 ELSE 1 END, p.loanId ASC")
    Page<Loan> findAllLoansByState(@Param("loanState") LoanState loanState, Pageable pageable);
}
