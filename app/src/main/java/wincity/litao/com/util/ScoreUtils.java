package wincity.litao.com.util;
/**
 * Created by sam on 12/23/16, 15:11$.
 */

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import wincity.litao.com.App;


/***
 * 跳转应用市场评分
 * @author Lucasey
 */
public class ScoreUtils {

    /**
     * 跳转到app详情界面
     *  应用商店包名 ,如果为""则由系统弹出应用商店列表供用户选择,否则调转到目标市场的应用详情界面，某些应用商店可能会失败
     */
    public static void launchAppDetail() {
        Context context = App.getInstance().getApplicationContext();
        try {
            Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
//            ToastUtil.showToast(context, context.getString(R.string.no_martket_for_rate), Toast.LENGTH_SHORT);
        }

    }
}
