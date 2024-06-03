package com.consola.lis.dto;

import com.consola.lis.model.enums.LoanState;
import com.consola.lis.util.deserializer.LoanStateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TableRegistersLoanDTO {
    private int page;
    private int size;
    @JsonDeserialize(using = LoanStateDeserializer.class)
    private LoanState loanState;
}
