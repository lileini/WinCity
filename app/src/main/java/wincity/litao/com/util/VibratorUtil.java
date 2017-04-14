package wincity.litao.com.util;


import android.app.Service;
import android.content.Context;
import android.media.AudioManager;
import android.os.Vibrator;
import android.util.Log;

/**
 * Created by yanwentao on 2017/1/19 0019.
 */

public class VibratorUtil {

    private Context context;
    private String TAG="VibratorUtil";
    // This example will cause the phone to vibrate "SOS" in Morse Code
    // In Morse Code, "s" = "dot-dot-dot", "o" = "dash-dash-dash"
    // There are pauses to separate dots/dashes, letters, and words
    // The following numbers represent millisecond lengths
    int dot = 200;      // Length of a Morse Code "dot" in milliseconds
    int dash = 500;     // Length of a Morse Code "dash" in milliseconds
    int short_gap = 200;    // Length of Gap Between dots/dashes
    int medium_gap = 500;   // Length of Gap Between Letters
    int long_gap = 1000;    // Length of Gap Between Words

//    long[] pattern = {
//            0,  // Start immediately
//            dot, short_gap, dot, short_gap, dot,    // s
//            medium_gap,
//            dash, short_gap, dash, short_gap, dash, // o
//            medium_gap,
//            dot, short_gap, dot, short_gap, dot,    // s
//            long_gap
//    };
    long[] pattern = {
            0,  // Start immediately
            200,800
    };

    private static VibratorUtil instance;
    private final Vibrator vibrator;

    public static VibratorUtil getInstance(Context context) {
        if (instance == null) {
            synchronized (VibratorUtil.class) {
                if (instance == null) {
                    instance = new VibratorUtil(context);
                }
            }
        }
        return instance;
    }

    private VibratorUtil(Context context) {
        this.context=context;
        vibrator = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
    }

    public void vibratorStart() {
        if (vibrator != null&&vibrator.hasVibrator())
            if (getRingerMode()!=AudioManager.RINGER_MODE_SILENT)    //silence mode
                vibrator.vibrate(pattern, 0);
        else
                LogUtil.e(TAG, "no Vibrator");
    }

    public void vibratorStop() {
        if (vibrator != null&&vibrator.hasVibrator())
            vibrator.cancel();
        else
            LogUtil.e(TAG, "no Vibrator");
    }

    private int getRingerMode(){
        if (context!=null){
            AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            if (audioManager!=null)
               return audioManager.getRingerMode();
        }
        return -1;
    }

}
