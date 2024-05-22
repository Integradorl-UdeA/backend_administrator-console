package com.consola.lis.util.constans;

import lombok.experimental.UtilityClass;

@UtilityClass
public class EndpointConstant {

    //Endpoints authentication
    public static final String ENDPOINT_LOGIN = "/api/console-lis/auth/login";
    public static final String ENDPOINT_REGISTER = "/api/console-lis/admin/register";
    public static final String ENDPOINT_REGISTER_USER_HELLO_LIS= "/userHelloLis";
    public static final String ENDPOINT_REGISTER_USER_LIS= "/user-lis";

    //Endpoints Category
    public static final String ENDPOINT_CATEGORY = "/api/console-lis/user/category";
    public static final String ENDPOINT_DELETE_CATEGORY = "/{categoryId}";
    public static final String ENDPOINT_ONE_CATEGORY = "/{categoryId}";
    public static final String ENDPOINT_ALL_NAMES_CATEGORIES = "/categoriesNames";


    //Endpoints Inventory Item
    public static final String ENDPOINT_INVENTORY = "/api/console-lis/user/inventory/item";
    public static final String ENDPOINT_INVENTORY_TABLE = "/tableRegisters";
    public static final String ENDPOINT_DELETE_ITEM="/delete/{itemId}";
    public static final String ENDPOINT_EDIT_ITEM_STATE="/item/state/{itemId}";
    public static final String ENDPOINT_ONE_ITEM = "/{itemId}";
    public static final String ENDPOINT_EDIT_QUANTITY = "/quantity/{itemId}";
    public static final String ENDPOINT_HEADERS_ITEM = "/tableHeaders/";

    //user
    public static final String ENDPOINT_USER = "/api/console-lis/user";
    public static final String ENDPOINT_ONE_USER ="/{username}";
    public static final String ENDPOINT_EXISTING_USER = "/existUser/{username}";
    public static final String ENDPOINT_DELETE_USER = "/delete/{username}";
    public static final String ENDPOINT_CHANGE_ROLE_USER = "/role/{username}";

    //Loan
    public static final String ENDPOINT_LOAN = "/api/console-lis/user/loan";
    public static final String ENDPOINT_DELETE_LOAN = "/delete/{loanId}";
    public static final String ENDPOINT_CREATE_LOAN = "/create";

    public static final String ENDPOINT_GET_ONE_LOAN = "/{loanId}";

    public static final String ENDPOINT_ALL_LOANS_TABLE = "/tableRegisters";
    public static final String ENDPOINT_HEADERS_LOAN = "/tableHeaders/";

    //Return Loan
    public static final String ENDPOINT_RETURN_LOAN = "/api/console-lis/user/returnLoan";
    public static final String ENDPOINT_RETURN_ALL_LOANS = "/returnLoans";






}
