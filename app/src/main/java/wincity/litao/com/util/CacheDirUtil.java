package wincity.litao.com.util;

import wincity.litao.com.App;

/**
 * created by litao
 **/
public class CacheDirUtil {
    public static String getCacheDir(){
        String cacheDir = null;
        if (App.getInstance().getApplicationContext().getExternalCacheDir() != null && ExistSDCard()) {
            cacheDir = App.getInstance().getApplicationContext().getExternalCacheDir().toString();
        } else {
            cacheDir = App.getInstance().getApplicationContext().getCacheDir().toString();
        }
        return cacheDir;
    }
    private static boolean ExistSDCard() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }
}
