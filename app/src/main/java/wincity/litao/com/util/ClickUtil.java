package wincity.litao.com.util;

public class ClickUtil {

    private final static int DOUBLE_CLICK = 500;
    private final static int SCROLL = 2000;
    private static long lastClickTime;
    private static long lastScollTime;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < DOUBLE_CLICK) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
    public static boolean isFastScroll() {
        long time = System.currentTimeMillis();
        long timeD = time - lastScollTime;
        if (0 < timeD && timeD < SCROLL) {
            return true;
        }
        lastScollTime = time;
        return false;
    }
}