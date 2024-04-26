package com.consola.lis.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "return_loan")
public class ReturnLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "return_id")
    private int returnId;

    @Column(name = "loan_id")
    private int loanId;

    @Column(name = "borrower_user")
    private String borrowerUser;

    @Column(name = "lender_user")
    private String lenderUser;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="GMT-5")
    @CreationTimestamp
    @Column(name = "return_date")
    private Date returnDate;

    private String observation;


}
