package wincity.litao.com.util;

import android.content.Context;
import android.support.v4.util.LruCache;
import android.widget.Toast;

public class ToastUtil {
    public final int SHOW_LONG = Toast.LENGTH_LONG;
    public final int SHOW_SHORT = Toast.LENGTH_SHORT;
    private static Toast mToast;

    /**
     * @param context
     * @param content
     * @param time    ToastUitl.SHOW_LONG or ToastUitl.SHOW_SHORT
     */
    public static void showToast(Context context, String content, int time) {
        showAToast(context.getApplicationContext(), content);
    }

    public static void showToast(Context context, int resId, int time) {
        showAToast(context.getApplicationContext(), context.getApplicationContext().getString(resId));
    }

    /**
     * RepeatSafeToast
     *
     */
    private static final int DURATION = 2500;

    private static final LruCache<String, Long> lastShown = new LruCache<String, Long>(100);

    private static boolean isRecent(String str) {
        Long last = lastShown.get(str);
        if (last == null) {
            return false;
        }
        long now = System.currentTimeMillis();
        if (last + DURATION < now) {
            return false;
        }
        return true;
    }



    private static synchronized void showAToast(Context context, String msg) {
        if (isRecent(msg)) {
            return;
        }
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        lastShown.put(msg, System.currentTimeMillis());
    }
}
