package wincity.litao.com.util;

import java.text.DecimalFormat;

/**
 * created by litao
 **/
public class FormatNumberUtil {
    /**
     *
     * @param number
     * @param formats "#.##" -> 41.001 - 41, 41.00 - 41
     *                or "0.00"-> 41.001 - 41.00, 41.00 - 41.00
     * @return
     */
    public static String format(double number,String formats){
        DecimalFormat decimalFormat = new DecimalFormat(formats);
        return decimalFormat.format(number);
    }
}
