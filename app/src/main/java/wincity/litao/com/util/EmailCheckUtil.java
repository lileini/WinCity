package wincity.litao.com.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ${U3} on 2016/11/1.
 */

public class EmailCheckUtil {
    public static boolean checkEmail(String email){
        String check = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(email);
        boolean isMatched = matcher.matches();
        return isMatched;
    }
}
