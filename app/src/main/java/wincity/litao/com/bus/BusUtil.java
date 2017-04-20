package wincity.litao.com.bus;

import org.greenrobot.eventbus.EventBus;

/**
 * created by litao
 **/
public class BusUtil {
    public static void register(Object o) {

        EventBus.getDefault().register(o);
    }

    public static void unregister(Object o) {
        EventBus.getDefault().unregister(o);
    }
    public static void post(Object o) {
        EventBus.getDefault().post(o);
    }
    public static void postSticky(Object o) {
        EventBus.getDefault().postSticky(o);
    }



}
