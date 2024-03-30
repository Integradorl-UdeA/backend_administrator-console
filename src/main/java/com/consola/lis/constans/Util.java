package com.consola.lis.constans;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Util {

    //states valid
    public static final String VALID_STATE_CUANT_LEND = "AVAILABLE, BROKEN, REPAIRING, LOST, PRESENT,LENDED";
    public static final String VALID_STATES_CUANT_NOTLEND = "BROKEN, REPARING, LOST, PRESENT";
    public static final String VALID_STATES_NOT_CUAN_LEND = "AVAILABLE, BROKEN, REPARING, LOST, PRESENT, LENDED, OUT OF STOCK";
    public static final String VALID_STATES_NOTCUAN_LEND = "BROKEN,REPAIRING,LOST,PRESENT";
}
