package com.consola.lis.util.constans;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Util {

    //states valid
    public static final String VALID_STATE_CUANT_LEND = "AVAILABLE,BROKEN,REPAIRING,LOST,PRESENT,LENDED";
    public static final String VALID_STATES_CUANT_NOTLEND = "BROKEN,REPAIRING,LOST,PRESENT";
    public static final String VALID_STATES_NOT_CUAN_LEND = "AVAILABLE,BROKEN,REPAIRING,LOST,PRESENT";
    public static final String VALID_STATES_NOTCUAN_LEND = "BROKEN,REPAIRING,LOST,AVAILABLE,LENDED,OUT_OF_STOCK";

    //valid wallet owners
    public static final String VALID_WALLET_OWNERS = "DIEGO_ALEJANDRO_BOTIA,ANDRES_MARIN,LUIS_SILVA,NOT_APPLY";

    //roles
    public static final String VALID_ROLE = "ADMIN,AUXPROG, AUXADMI";
}
