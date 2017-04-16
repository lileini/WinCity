package wincity.litao.com.http;

import com.google.gson.Gson;

import java.net.SocketTimeoutException;

import retrofit2.HttpException;
import wincity.litao.com.util.LogUtil;


/**
 * Created by shang guangneng on 2016/7/12 0012.
 */
public class HttpErrorException {

    private static String TAG = "HttpErrorException";
    /**
     * code : 400
     * description : InputError:domain not in system,No row was found for one()
     * message : 400: Bad Request
     */

    private int code;
    private String description;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String handErrorMessage(Throwable throwable){
        String error;
        if(throwable instanceof HttpException){
            HttpException exception = (HttpException) throwable;
            try {
                error=exception.getMessage();

            } catch (Exception e) {
                e.printStackTrace();
                error=e.getMessage();

            }
        }else{
            throwable.printStackTrace();
            error=throwable.getMessage();
            LogUtil.e("HttpErrorException",error);
        }
        return error;
    }


    public static String getErrorBody(Throwable throwable){
        String error;
        if(throwable instanceof HttpException){
            HttpException exception = (HttpException) throwable;
            try {

                error = ((HttpException) throwable).response().errorBody().string();
            } catch (Exception e) {
                e.printStackTrace();
                error=e.getMessage();
            }
        }else{
            throwable.printStackTrace();
            error=throwable.getMessage();
            if (error != null){
                LogUtil.e("HttpErrorException",error);
            }
        }
        return error;
    }

    public static APIErrorEntity getErrorEntity(Throwable throwable){
        APIErrorEntity errorEntity=null;
        String error;
        if(throwable instanceof HttpException){
            HttpException exception = (HttpException) throwable;
            try {
                error = ((HttpException) throwable).response().errorBody().string();
                Gson gson = new Gson();
                errorEntity = gson.fromJson(error, APIErrorEntity.class);
                return errorEntity;
            } catch (Exception e) {
                e.printStackTrace();
                error=e.getMessage();
            }
        }else if (throwable instanceof SocketTimeoutException){
            LogUtil.i(TAG,"Request Time out!");
//            BusProvider.getInstance().post(new RequestTimeOutEvent());
//            Toast.makeText(App.getInstance().getApplicationContext(),"Request Time out!",Toast.LENGTH_SHORT).show();
        }else{
            throwable.printStackTrace();
            error=throwable.getMessage();
            LogUtil.e("HttpErrorException",error);
        }
        return errorEntity;
    }


    public static int getStatusCode(Throwable throwable){
        if (!(throwable instanceof HttpException)) {
            try {
                if (throwable != null){
                    LogUtil.e(TAG,throwable.getMessage());
                }

            }catch (Exception e){
                e.printStackTrace();
            }
//            throw new IllegalArgumentException("not is HttpException");
            return Integer.MIN_VALUE;
        }
        HttpException exception = (HttpException) throwable;

        return exception.code();
    }
}
