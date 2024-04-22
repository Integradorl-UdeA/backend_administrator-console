package com.consola.lis.util.constans;

import lombok.experimental.UtilityClass;

@UtilityClass
public class EndpointConstant {

    //Endpoints authentication
    public static final String ENDPOINT_LOGIN = "/api/console-lis/auth/login";
    public static final String ENDPOINT_REGISTER = "/api/console-lis/auth/register";


    //Endpoints Category
    public static final String ENDPOINT_CATEGORY = "/api/console-lis/auth/category";
    public static final String ENDPOINT_DELETE_CATEGORY = "/{categoryName}";
    public static final String ENDPOINT_ONE_CATEGORY = "/{categoryName}";
    public static final String ENDPOINT_ALL_NAMES_CATEGORYS = "/categoriesNames";


    //Endpoints Inventory Item
    public static final String ENDPOINT_INVENTORY = "/api/console-lis/auth/inventory";

    public static final String ENDPOINT_INVENTORY_TABLE = "/tableRegisters";
    public static final String ENDPOINT_INVENTORY_ITEM = "/item";

    public static final String ENDPOINT_DELETE_ITEM="/item/delete/{itemId}";
    public static final String ENDPOINT_EDIT_ITEM_STATE="/item/state/{itemId}";

    public static final String ENDPOINT_ONE_ITEM = "/item/{itemId}";
    //user

    public static final String ENDPOINT_USER = "/api/console-lis/auth/user";
    public static final String ENDPOINT_ONE_USER ="/{username}";
    public static final String ENDPOINT_USER_LDAP = "/ldapUser/{username}";
    public static final String ENDPOINT_DELETE_USER = "/delete/{username}";
    public static final String ENDPOINT_CHANGE_ROLE_USER = "/role/{username}";

    //Loan
    public static final String ENDPOINT_LOAN = "/api/console-lis/auth/loan";//traerlos todos
    public static final String ENDPOINT_DELETE_LOAN = "/delete/{loanId}";
    public static final String ENDPOINT_CREATE_LOAN = "/create";

    public static final String ENDPOINT_GET_ONE_LOAN = "/{loanId}";

    public static final String ENDPOINT_ALL_LOANS_TABLE = "/api/console-lis/auth/loan/tableRegisters";

    //Return Loan
    public static final String ENDPOINT_RETURN_LOAN = "/api/console-lis/auth/returnLoan";
    public static final String ENDPOINT_RETURN_ALL_LOANS = "/returnLoans";






}
