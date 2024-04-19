package com.consola.lis.util.constans;

import lombok.experimental.UtilityClass;

@UtilityClass
public class EndpointConstant {

    //Endpoints authentication
    public static final String ENDPOINT_LOGIN = "/api/console-lis/auth/login";
    public static final String ENDPOINT_REGISTER = "/api/console-lis/admin/register";


    //Endpoints Category
    public static final String ENDPOINT_CATEGORY = "/api/console-lis/user/category";
    public static final String ENDPOINT_DELETE_CATEGORY = "/{categoryName}";
    public static final String ENDPOINT_ONE_CATEGORY = "/{categoryName}";
    public static final String ENDPOINT_ALL_NAMES_CATEGORYS = "/categoriesNames";


    //Endpoints Inventory Item
    public static final String ENDPOINT_INVENTORY = "/api/console-lis/user/inventory";

    public static final String ENDPOINT_INVENTORY_GENERAL_ITEM = "/generalItem";
    public static final String ENDPOINT_INVENTORY_QUANTIZABLE_ITEM = "/quantizableItem";

    public static final String ENDPOINT_INVENTORY_ALL_GENERAL_ITEM = "/generalItems";
    public static final String ENDPOINT_INVENTORY_ALL_QUANTIZABLE_ITEM = "/quantizableItems";
    public static final String ENDPOINT_DELETE_ITEM="/{itemId}";


    public static final String ENDPOINT_ONE_GENERAL_ITEM = "/generalItem/{itemId}";
    public static final String ENDPOINT_ONE_QUANTIZABLE_ITEM = "/quantizableItem/{itemId}";

    //user

    public static final String ENDPOINT_USER = "/api/console-lis/user";
    public static final String ENDPOINT_ONE_USER ="/{username}";
    public static final String ENDPOINT_USER_LDAP = "/ldapUser/{username}";
    public static final String ENDPOINT_DELETE_USER = "/delete/{username}";
    public static final String ENDPOINT_CHANGE_ROLE_USER = "/role/{username}";










}
