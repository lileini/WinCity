package wincity.litao.com.http.interceptor;

import java.io.IOException;
import java.net.SocketTimeoutException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yanwentao on 2017/3/31.
 */

public class TimeOutInterceptor  implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response proceed = null;
        try {
            Request request = chain.request();
            proceed = chain.proceed(request);
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
//            BusProvider.getInstance().post(new RequestTimeOutEvent());
        }
        return proceed;
    }
}
