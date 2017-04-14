package wincity.litao.com.http;



import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import wincity.litao.com.util.LogUtil;

/**
 *  Created by globalroam on 2015/9/17.
 */
public class MyHostnameVerify implements HostnameVerifier{

    private static final String TAG = "MyHostnameVerify";

    @Override
    public boolean verify(String hostname, SSLSession session) {

        if(hostname != null){
            if( hostname.equals(ApiManager.BASE_URL)){
                LogUtil.d(TAG, "The environment is  product");
            }else{
                LogUtil.d(TAG,"The environment is not product");
            }
        }
        return true;
    }
}
