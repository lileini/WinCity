package wincity.litao.com.util;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wentao on 2016/10/11.
 */
public class NumberUtil {
    private static final String TAG = "NumberUtil";
    /**
     * format to +65 8888 8888
     *
     * @param number
     * @return
     */
    static PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

    public static String phoneNumberFormat(String number) {
        if (number == null || number.equalsIgnoreCase("")) return "";
        try {
            Phonenumber.PhoneNumber phoneNumber = phoneNumberUtil.parse("+" + number, "SG");
            boolean isValid = phoneNumberUtil.isValidNumber(phoneNumber); // returns true
            if (isValid) {
                return phoneNumberUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL);
            } else {
                return "+" + phoneNumber.getCountryCode() + " " + addSpace(phoneNumber.getNationalNumber() + "");
            }

        } catch (NumberParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Phonenumber.PhoneNumber checkMobile(String number) throws NumberParseException {
        if (!number.startsWith("+"))
            number = "+" + number;
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        return phoneUtil.parse(number, "");
    }

    private static String addSpace(String number) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            if ((i + 1) % 4 == 0) {
                builder.append(c + " ");
            } else {
                builder.append(c);
            }
        }
        return builder.toString();
    }

    private static String connect(String codeStr, String numberStr) {

        String NumberStr = codeStr + numberStr;

        if (NumberStr != null && NumberStr.startsWith("00") && NumberStr.length() > 3) {

            NumberStr = "+" + NumberStr.substring(2);
        }

        if (NumberStr == null || !NumberStr.startsWith("+")) {

            throw new IllegalArgumentException("illegal parameter, must begin with +");
        }
        return NumberStr;
    }

    private static String connect(String NumberStr) {

        if (NumberStr != null && NumberStr.startsWith("00") && NumberStr.length() > 3) {

            NumberStr = "+" + NumberStr.substring(2);
        }

        if (NumberStr == null || !NumberStr.startsWith("+")) {

            throw new IllegalArgumentException("illegal parameter, must begin with +");
        }
        return NumberStr;
    }

    public static boolean isNumber(String number) {

        Pattern p = Pattern.compile("^\\d+$");

        Matcher m = p.matcher(number);
        LogUtil.i(TAG,"number:"+number +" matches :"+m.matches());
        return m.matches();

    }
}
