package com.consola.lis.controller;

import com.consola.lis.model.enums.*;
import com.consola.lis.util.constans.ApiDescription;
import com.consola.lis.util.constans.EndpointConstant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Manage Select List", description = ApiDescription.DESCRIPTION_CONTROLLER_SELECT_LIST)
@RequestMapping(EndpointConstant.ENDPOINT_SELECT_LIST_ENUM)
@RequiredArgsConstructor
public class ListSelectController {

    @Operation(summary = ApiDescription.DESCRIPTION_SELECT_LIST_ENUM_WALLET)
    @GetMapping(EndpointConstant.ENDPOINT_SELECT_LIST_ENUM_WALLET)
    public List<String> getWalletOwnersList() {
        return WalletOwners.getLowerCaseValuesList();
    }

    @Operation(summary = ApiDescription.DESCRIPTION_SELECT_LIST_ENUM_LOAN_TYPE)
    @GetMapping(EndpointConstant.ENDPOINT_SELECT_LIST_ENUM_LOAN_TYPE)
    public List<String> getLoanTypeList() {
        return LoanType.getLowerCaseValuesList();
    }

    @Operation(summary = ApiDescription.DESCRIPTION_SELECT_LIST_ENUM_ROLE)
    @GetMapping(EndpointConstant.ENDPOINT_SELECT_LIST_ENUM_ROLE)
    public List<String> getUserRoleList() {
        return UserRole.getLowerCaseValuesList();
    }

    @Operation(summary = ApiDescription.DESCRIPTION_SELECT_LIST_ENUM_LOAN_STATE)
    @GetMapping(EndpointConstant.ENDPOINT_SELECT_LIST_ENUM_LOAN_STATE)
    public List<String> getLoanStateList() {
        return LoanState.getLowerCaseValuesList();
    }

    @Operation(summary = ApiDescription.DESCRIPTION_SELECT_LIST_ENUM_ITEM_STATE)
    @GetMapping(EndpointConstant.ENDPOINT_SELECT_LIST_ENUM_ITEM_STATE)
    public List<String> getitemStateList() {
        return ItemState.getLowerCaseValuesList();
    }
}
