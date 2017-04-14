package wincity.litao.com.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AssetUtil {
    private static String TAG = "AssetUtil";

    public static String getConfig(Context context, String config){
        String info = "";
        InputStream inputStream = null;
        try {
            inputStream = context.getResources().getAssets().open(config);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String temp = null;
            while ((temp = bufferedReader.readLine()) != null) {
                info += temp;
            }

            LogUtil.i(TAG,"info = " + info);

        } catch (IOException e) {
            e.printStackTrace();
            LogUtil.i(TAG,"getConfig FAIL");
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                LogUtil.i(TAG,"inputStream close");
            }
        }
        return info;
    }
}
