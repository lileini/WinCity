package wincity.litao.com.util;

import java.text.DecimalFormat;

/**
 * Created by wentao on 2016/6/13.
 */
public class FormatUtil {
    private static DecimalFormat decimalFormat;

    /**
     * 将金额格式化为99,999.99
     * @param amount double类型金额
     * @return 格式化后的金额字符串
     */
    public static String formatAmount(double amount){
        if (decimalFormat==null){
            decimalFormat=new DecimalFormat("#,##0.00");
        }
        return decimalFormat.format(amount);
    }
    /**
     * 将金额格式化为99,999.99
     * @param amount String类型金额
     * @return 格式化后的金额字符串
     * */
    public static String formatAmount(String amount)
    {
        double dAmount = Double.valueOf(amount == null ? "0" : amount);
        if (decimalFormat==null){
            decimalFormat=new DecimalFormat("#,##0.00");
        }
        return decimalFormat.format(dAmount);
    }


}
