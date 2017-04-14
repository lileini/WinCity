package wincity.litao.com.util;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;

import org.greenrobot.eventbus.EventBus;

/**
 * created by litao
 **/
public class BusUtil {
    public static void register(Object o) {
        if (o instanceof Activity || o instanceof Fragment || o instanceof android.support.v4.app.Fragment
                || o instanceof android.support.v4.app.ActivityCompat || o instanceof Application)
            EventBus.getDefault().register(o);
    }
    public static void unregister(Object o) {
        if (o instanceof Activity || o instanceof Fragment || o instanceof android.support.v4.app.Fragment
                || o instanceof android.support.v4.app.ActivityCompat || o instanceof Application)
            EventBus.getDefault().unregister(o);
    }


}
