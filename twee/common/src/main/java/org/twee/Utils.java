package org.twee;

import org.vinst.account.AccountKey;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author Lars Velsky
 * @since 04/12/14
 */
public final class Utils {

    private Utils (){}

    private final static NumberFormat numberFormat = new DecimalFormat();

    static {
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
    }

    public static AccountKey getAccountKey(String idString){
        if (idString.startsWith("#")){
            idString = idString.substring(1);
        }

        return AccountKey.of(Long.parseLong(idString));
    }

    public static String formatUSDAmount(double usdAmount){
        return numberFormat.format(usdAmount);
    }
}
