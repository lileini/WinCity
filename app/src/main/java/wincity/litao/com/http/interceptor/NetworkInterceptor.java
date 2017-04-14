package wincity.litao.com.http.interceptor;

import android.os.Looper;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import wincity.litao.com.App;
import wincity.litao.com.R;
import wincity.litao.com.util.LogUtil;
import wincity.litao.com.util.NetUtil;
import wincity.litao.com.util.ToastUtil;

/**
 * Created by yanwentao on 2017/2/9 0009.
 */

public class NetworkInterceptor implements Interceptor {
    private String TAG = "NetworkInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        boolean connected = NetUtil.isConnected();
        if (connected) {
            return chain.proceed(chain.request());
        } else {
            LogUtil.i(TAG, "Please check Network is MainProcess:" + (Looper.myLooper() == Looper.getMainLooper()));
            Observable.just(null).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Object>() {
                @Override
                public void call(Object o) {
                    if (!isFastOperation()){
                        ToastUtil.showToast(App.getInstance(), App.getInstance().getString(R.string.network_error), Toast.LENGTH_SHORT);
                    }
                }
            });

            return null;
        }
    }

    private final static int DOUBLE_CLICK = 500;
    private static long lastClickTime;

    public static boolean isFastOperation() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < DOUBLE_CLICK) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
