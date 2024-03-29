package com.consola.lis.constans;

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
    public static final String ENDPOINT_ALL_NAMES_CATEGORYS = "/CategoriesNames";


    //Endpoints Inventory Item
    public static final String ENDPOINT_INVENTORY = "/api/console-lis/auth/inventory";


}
