package wincity.litao.com.bus;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;

import org.greenrobot.eventbus.EventBus;

import wincity.litao.com.BuildConfig;

/**
 * created by litao
 **/
public class BusUtil {
    public static void register(Object o) {
        if (o instanceof Activity || o instanceof Fragment || o instanceof android.support.v4.app.Fragment
                || o instanceof android.support.v4.app.ActivityCompat || o instanceof Application)
            getEventBust().register(o);
    }
    public static void unregister(Object o) {
        if (o instanceof Activity || o instanceof Fragment || o instanceof android.support.v4.app.Fragment
                || o instanceof android.support.v4.app.ActivityCompat || o instanceof Application)
            getEventBust().unregister(o);
    }
    public static EventBus getEventBust(){
        return EventBus.builder().sendNoSubscriberEvent(false)
                .sendSubscriberExceptionEvent(false)
                .throwSubscriberException(BuildConfig.DEBUG) //只有在debug模式下，会抛出错误异常
                .build();
    }

}
