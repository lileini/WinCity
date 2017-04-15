package wincity.litao.com.http.interceptor;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import wincity.litao.com.util.LogUtil;

/**
 * created by litao
 **/
public class HostInterceptor implements Interceptor {
    private static final String TAG = "HostInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Request build =null;
        LogUtil.i(TAG, "url intercept: "+chain.request().url().toString());
        HttpUrl url = request.url();
        String urls = "subscription.uservice.gnum.com";
//        Log.i(TAG, "host url intercept: "+url.host());
//        if(ApiManager.product.equals(ApiManager.WORK_BRANCH)) {
//
//            urls = "subscription.uservice.tokuapp.com";
//
//        }
        if (url != null && "subscription.uservice.gnum.com".equals(url.host())) {
            HttpUrl httpUrl = request.url().newBuilder()
                    .host(urls)
                    .build();
            build = request.newBuilder()
                    .url(httpUrl)
                    .build();

        } else {
            build = request.newBuilder().build();
        }
        /***
         * 对@的Header进行移除
         */


        return chain.proceed(build);
    }


}
