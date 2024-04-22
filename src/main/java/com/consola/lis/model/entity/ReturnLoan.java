package com.consola.lis.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "return_loan")
public class ReturnLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "return_id")
    private int returnId;

    @Column(name = "loan_id")
    private int loanId;

    @Column(name = "borrower_id")
    private String borrowerId;

    @Column(name = "lender_id")
    private String lenderId;

    @Column(name = "return_date")
    private Date returnDate;

    private String observation;


}
