package wincity.litao.com.http.interceptor;
/**
 * Created by sam on 16/11/2, 18:03$.
 * <p>
 * Create by：sam
 * Create time：16/11/2 18:03
 * Create note：
 * Revise：sam
 * Revise time：16/11/2 18:03
 * Revise note：
 */

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import wincity.litao.com.util.LogUtil;

/**
 * 全局自动刷新Token的拦截器
 * <p>
 * 作者：余天然 on 16/9/5 下午3:31
 */
public class TokenInterceptor implements Interceptor {

    private static final String TAG = "TokenInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        LogUtil.i(TAG, "response.code=" + response.code());

        if (isTokenExpired(response)) {//根据和服务端的约定判断token过期
            LogUtil.i(TAG, "静默自动刷新Token,然后重新请求数据");
            //同步请求方式，获取最新的Token
//            String newSession = getNewToken();
//            //  save this new token into sputil
//            if(TextUtils.isEmpty(newSession)){
//                SPUtil.saveACToken(newSession);
//            }
            //使用新的Token，创建新的请求
            Request newRequest = chain.request()
                    .newBuilder()
//                    .header("Authorization", newSession)
                    .build();
            //重新请求
            return chain.proceed(newRequest);
        }
        return response;
    }

    /**
     * 根据Response，判断Token是否失效
     *
     * @param response
     * @return
     */
    private boolean isTokenExpired(Response response) {
        if (response.code() == 401) {
            return true;
        }
        return false;
    }

    /**
     * 同步请求方式，获取最新的Token
     *
     * @return
     */
    /*private String getNewToken(){
        try {
            // 通过一个特定的接口获取新的token，此处要用到同步的retrofit请求

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiManager.BASE_URL)
                    .build();
            ApiService service = retrofit.create(ApiService.class);

            String number = SPUtil.getCode() + SPUtil.getMobile();

            String deviceId = new DeviceUuidFactory(App.getInstance().getApplicationContext()).getDeviceUuid().toString();

            Log.i(TAG, " number " + number + " deviceId: " + deviceId);

            Call<ResponseBody> call = service.checkLogin(number, "GLOBALROAM", deviceId, ApiManager.DEVICE_TYPE);
            retrofit2.Response<ResponseBody> response = call.execute();


            String newToken = SPUtil.getACToken();

            if (200 == response.code()) {
                *//**刷新actoken SUCCESS, 返回结果
                 * {
                 "access_token": "1118eeacde0be4842311fbf2b501e7fc",
                 "user_id": "3EA664F52B0C21F0E0530100007F946E",
                 "token_type": "Bearer",
                 "expires_in": 709644
                 }
                 *//*
                ResponseBody body = response.body();

                try {
                    JSONObject obj = new JSONObject(body.string());
                    newToken = obj.getString("token_type") + " " + obj.getString("access_token");
                    Log.i(TAG, "getNewToken " + newToken);
                } catch (JSONException e) {
                    e.printStackTrace();

                } finally {
                    try {
                        body.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            } else {
                *//**
                 *刷新actoken失败, 要求踢出用户, 重新登录.
                 * {
                 "code": "10401002",
                 "message": " No privilege to access the API.",
                 "more_info": ""
                 }
                 *//*
                ResponseBody errorBody = response.errorBody();
                try {
                    JSONObject obj = new JSONObject(errorBody.string());
                    String code = obj.getString("code");
                    String message = obj.getString("message");
                    Log.i(TAG, "error code " + code + " ; message " + message);

                    if ("10401002".equals(code)) {
                        //token expired, need to relogin
                        BusProvider.getInstance().post(new App.GetProfileEvent("10401002 Token Expired", null));

                    } else if ("10409009".equals(code)) {
                        //account loggin on other device, force logout,
                        BusProvider.getInstance().post(new App.GetProfileEvent("10409009 Force logout", null));
                    }

                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }


            return newToken;
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.i(TAG,"request newToken failed");
        }
        return SPUtil.getACToken();
    }*/
}
