package com.consola.lis.model.entity;

import com.consola.lis.model.enums.LoanType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private int loanId;

    @Column(name = "item_id")
    private String itemId;

    private int quantity;

    @Column(name = "loan_type")
    private LoanType loanType;

    @Column(name = "borrower_id")
    private String borrowerId;

    @Column(name = "lender_id")
    private String lenderId;

    @Column(name = "loan_date")
    private Date loanDate;

    @Column(name = "return_date")
    private Date returnDate;

    private String observation;

}
