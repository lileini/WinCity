package wincity.litao.com.util;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;

/**
 * created by litao
 * 对string进行过滤并标记颜色
 **/
public class SpanStringUtil {
    public static SpannableStringBuilder markStringWithKeyword(String content, String filterKeyword, int colorResId) {

        String content2 = content + "";

        SpannableStringBuilder style = new SpannableStringBuilder(content2);

        if (TextUtils.isEmpty(filterKeyword)) {
            return style;
        }

        int index = content2.toLowerCase().indexOf(filterKeyword, 0);
        int len = filterKeyword.length();
        if (index >= 0) {
            style.setSpan(new ForegroundColorSpan(colorResId), index, index + len, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            index = content2.indexOf(filterKeyword, index + 1);
        }
        return style;
    }
}
