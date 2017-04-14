package wincity.litao.com.util;

import android.app.Activity;
import android.app.ProgressDialog;

/**
 * Created by yanwentao on 2017/4/12.
 */

public class ProgressDialogUtil {

    private static ProgressDialog progressDialog;

    public static void showProgressDialog(Activity activity, String message, boolean cancel){
        progressDialog=new ProgressDialog(activity);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(cancel);
        progressDialog.show();
    }

    public static void dismissProgressDialog(){
        if (progressDialog != null&&progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog=null;
        }
    }
}
