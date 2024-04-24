package com.consola.lis.model.entity;

import com.consola.lis.model.enums.LoanState;
import com.consola.lis.model.enums.LoanType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private int loanId;

    @Column(name = "item_id")
    private String itemId;

    private int quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "loan_type")
    private LoanType loanType;

    @Column(name = "borrower_user")
    private String borrowerUser;

    @Column(name = "lender_user")
    private String lenderUser;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="UTC")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "loan_date")
    private Date loanDate;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="UTC")
    @Column(name = "return_date")
    private Date returnDate;

    private String observation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", referencedColumnName = "item_id", insertable = false, updatable = false)
    @JsonIgnore
    private InventoryItem inventoryItem;

    @Enumerated(EnumType.STRING)
    @Column(name = "loan_state")
    private LoanState loanState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "borrower_user", referencedColumnName = "username", insertable = false, updatable = false)
    @JsonIgnore
    private User userB;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lender_user", referencedColumnName = "username", insertable = false, updatable = false)
    @JsonIgnore
    private User userL;
}
