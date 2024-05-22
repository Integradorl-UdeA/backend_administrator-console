package com.consola.lis.controller;

import com.consola.lis.model.enums.*;
import com.consola.lis.util.constans.EndpointConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/console-lis/auth/selectList/")
@RequiredArgsConstructor
public class ListSelectController {

    @GetMapping("/walletOwners")
    public List<String> getWalletOwnersList() {
        return WalletOwners.getLowerCaseValuesList();
    }

    @GetMapping("/loanType")
    public List<String> getLoanTypeList() {
        return LoanType.getLowerCaseValuesList();
    }

    @GetMapping("/userRole")
    public List<String> getUserRoleList() {
        return UserRole.getLowerCaseValuesList();
    }

    @GetMapping("/loanState")
    public List<String> getLoanStateList() {
        return LoanState.getLowerCaseValuesList();
    }

    @GetMapping("/itemState")
    public List<String> getitemStateList() {
        return ItemState.getLowerCaseValuesList();
    }
}
