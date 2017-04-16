package wincity.litao.com.http;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * created by litao
 **/
public interface ApiServer  {
    @GET("v2/Notifications/otp")
    Flowable<ResponseBody> otpRequest(@Query("to") String to, @Query("sendType") String sendType, @Query("providerId") String providerId);
}
