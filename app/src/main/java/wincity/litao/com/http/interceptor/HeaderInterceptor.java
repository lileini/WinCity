package wincity.litao.com.http.interceptor;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yanwentao on 2017/2/8 0008.
 */

public class HeaderInterceptor implements Interceptor {


    private static final String TAG = "HeaderInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request.Builder builder = chain.request()
                .newBuilder();
//        Log.i(TAG, "url intercept: "+chain.request().url().toString());
        HttpUrl url = chain.request().url();
//        Log.i(TAG, "host url intercept: "+url.host());
        builder.removeHeader("Authorization");
//        if (!TextUtils.isEmpty(SPUtil.getACToken())){
//            builder.addHeader("Authorization",SPUtil.getACToken());
//        }
//        builder.addHeader("x-device-id", getDeviceId());
//        builder.addHeader("x-device-type", "android_fcm");
//        if (!TextUtils.isEmpty(getDeviceToken())){
//            builder.addHeader("x-device-token",getDeviceToken());
//        }
//        builder.addHeader("x-login-id", SPUtil.getCode()+SPUtil.getMobile());
//        builder.addHeader("x-version", "V2");

        /***
         * 对@的Header进行移除
         */
        List<String> values = chain.request().headers().values("@");
        for (String header:values){
            builder.removeHeader(header);
        }
        builder.removeHeader("@");
        //替换header值
        List<String> revalues = chain.request().headers().values("$");
        for (String header:revalues){
            String[] keyvalue = header.split(",");
            builder.removeHeader(keyvalue[0]);
            builder.addHeader(keyvalue[0],keyvalue[1]);
        }
        builder.removeHeader("$");

        Request request = builder.build();
        return chain.proceed(request);
    }

    /*private String getDeviceId(){
        DeviceUuidFactory factory = new DeviceUuidFactory(App.getInstance());
        return factory.getDeviceUuid()!=null?factory.getDeviceUuid().toString():"";
    }

    private String getDeviceToken(){
        if (FirebaseInstanceId.getInstance().getToken()==null)
            return "";
        return FirebaseInstanceId.getInstance().getToken();
    }*/

}
