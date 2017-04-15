package wincity.litao.com.http;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import wincity.litao.com.BuildConfig;
import wincity.litao.com.http.interceptor.HeaderInterceptor;
import wincity.litao.com.http.interceptor.NetworkInterceptor;
import wincity.litao.com.http.interceptor.TimeOutInterceptor;
import wincity.litao.com.util.CacheDirUtil;
import wincity.litao.com.util.LogUtil;


/**
 * created by litao
 **/
public class ApiManager {
    public static final String dev = "dev";
    public static String BASE_URL = "";
    private static final String TAG = "ApiManager";

    public static final String staging = "staging";
    public static final String product = "product";

    public final static String BASE_URL_DEV = "http://192.168.200.38:8080/api/";
    public final static String BASE_URL_STAGE = "http://61.8.195.50:8080/api/";
    public final static String BASE_URL_PRODUCT = "https://apigw.tokuapp.com/api/";
    public final static String API_ENVIRONMENT = BuildConfig.API_ENVIRONMENT;

    private ApiServer apiServer;

    private static class Singleton {
        private static final ApiManager apiManager = new ApiManager();
    }

    public static ApiManager getInstance() {
        return Singleton.apiManager;
    }
    private ApiManager() {
        //        SSLSocketFactory sslSocketFactory = SSLContextUtils.getSSLContext().getSocketFactory();

        LogUtil.i(TAG, "cacheDir: " + CacheDirUtil.getCacheDir());
        //缓存目录
        Cache cache = new Cache(new File(CacheDirUtil.getCacheDir()), 10 * 1024 * 1024);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()

                //必须是设置Cache目录
                .cache(cache)
                //失败重连
                .retryOnConnectionFailure(true)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addNetworkInterceptor(new StethoInterceptor())//stetho,可以在chrome中查看请求
                .addInterceptor(new TimeOutInterceptor())
                .addInterceptor(new NetworkInterceptor())
                //                .addInterceptor(new HostInterceptor())
                .addInterceptor(new HeaderInterceptor());
//                .addInterceptor(new TokenInterceptor());
        //打印请求的日志
        if (!API_ENVIRONMENT.equalsIgnoreCase(ApiManager.product)) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }

        if (BuildConfig.dev.equals(API_ENVIRONMENT)) {
            BASE_URL = BASE_URL_DEV;
        } else if (BuildConfig.staging.equals(API_ENVIRONMENT)) {
            BASE_URL = BASE_URL_STAGE;
        } else if (BuildConfig.product.equals(API_ENVIRONMENT)) {
            BASE_URL = BASE_URL_PRODUCT;
            //添加证书
            //            builder.sslSocketFactory(SSLContextUtils.getSSLContext().getSocketFactory())
            //                    .hostnameVerifier(new MyHostnameVerify());
        }





        Retrofit.Builder retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        retrofit.client(builder.build());
        apiServer = retrofit.build().create(ApiServer.class);

    }
}
