package wincity.litao.com.util;

import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by ${U3} on 2016/12/30.
 */

public class FontUtil {


    public static void setTextTypeface(TextView textView) {
        Typeface typeface50 = Typeface.createFromAsset(textView.getContext().getAssets(), "fonts/Montserrat-Light.otf");
        textView.setTypeface(typeface50);
    }

    public static void setTitleTypeface(TextView textView) {
        Typeface typeface55 = Typeface.createFromAsset(textView.getContext().getAssets(), "fonts/Montserrat-Regular.otf");
        textView.setTypeface(typeface55);
    }

    public static void setMediumTypeface(TextView textView) {
        Typeface typeface55 = Typeface.createFromAsset(textView.getContext().getAssets(), "fonts/Montserrat-Medium.otf");
        textView.setTypeface(typeface55);
    }
}
