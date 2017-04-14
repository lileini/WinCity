package wincity.litao.com.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;

import java.util.Calendar;

/**
 * Created by ${U3} on 2017/3/21.
 */

public class EmailSendUtils {
    public static void sendOtpNotRe(Context context,String phone,int time){
        String versionName = "Not get";
		try {

            versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;


		} catch (PackageManager.NameNotFoundException e) {

			e.printStackTrace();
		}
        Intent data=new Intent(Intent.ACTION_SENDTO);
        data.setData(Uri.parse("mailto:support@tokuapp.com"));
        data.putExtra(Intent.EXTRA_SUBJECT, "Can't get Toku verification code");
        data.putExtra(Intent.EXTRA_TITLE, "Contact Toku Support");
        String times = time > 1?"times":"time";
        data.putExtra(Intent.EXTRA_TEXT,
                "Dear Toku Support Team,\n" +
                " \n" +
                "I did not get a Toku verification code. I have tried "+time+" "+times+" ...\n\n" +
                "Attached are the screenshots (if any):\n" +
                " \n" +
                "Just so you know, I'm\n\n" +
                "Running on "+ Build.MODEL+"\n" +
                "OS version: "+Build.VERSION.RELEASE+" \n" +
                "App version: "+ versionName+"\n" +
                "My mobile number is: "+phone+"\n" +
                "Timestamp: "+ Calendar.getInstance().getTime().toString());
        context.startActivity(data);
    }
}
