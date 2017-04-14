package wincity.litao.com.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 使用正则表达式进行表单验证
 */
public class RegexValidateUtils {

    static boolean flag = false;
    static String regex = "";

    private RegexValidateUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean check(String str, String regex) {
        try {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(str);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 验证非空
     *
     * @param notEmputy
     * @return
     */
    public static boolean checkNotEmputy(String notEmputy) {
        regex = "^\\s*$";
        return check(notEmputy, regex) ? false : true;
    }

    /**
     * 验证邮箱
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        return email.contains("@");
    }

    /**
     * 验证手机号码
     * <p/>
     * 移动号码段:139、138、137、136、135、134、150、151、152、157、158、159、182、183、187、188、147
     * 联通号码段:130、131、132、136、185、186、145
     * 电信号码段:133、153、180、189、177
     *
     * @param cellphone
     * @return
     */
    public static boolean checkCellphone(String cellphone) {
        String regex="^\\+?\\d{9,18}$";
        return check(cellphone, regex);
    }

    /**
     * 验证固话号码
     *
     * @param telephone
     * @return
     */
    public static boolean checkTelephone(String telephone) {
        String regex = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
        return check(telephone, regex);
    }

    /**
     * 验证传真号码
     *
     * @param fax
     * @return
     */
    public static boolean checkFax(String fax) {
        String regex = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
        return check(fax, regex);
    }

    /**
     * 验证QQ号码
     *
     * @param QQ
     * @return
     */
    public static boolean checkQQ(String QQ) {
        String regex = "^[1-9][0-9]{4,} $";
        return check(QQ, regex);
    }

    /**
     * 过滤特殊字符
     *
     * @param data
     * @return
     */
    public static boolean StringFilter(String data) {
        if (data.replaceAll("[\u4e00-\u9fa5]*[a-z]*[A-Z]*\\d*-*_*\\s*", "").length() == 0) {
            //不包含特殊字符
            return false;
        }
        return true;
    }
}
