package wincity.litao.com.util;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import wincity.litao.com.App;

/**
 * Created by ${U3} on 2016/12/13.
 */

public class MCCUtil {
    private static boolean isMCCInSG() {
        TelephonyManager tel = (TelephonyManager) App.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
        String networkOperator = tel.getNetworkOperator();
        String simoperator = tel.getSimOperator();
        int mcc = -1, mccsim = -1;
        if (!TextUtils.isEmpty(networkOperator)) {
            mcc = Integer.parseInt(networkOperator.substring(0, 3));
        }
        if (!TextUtils.isEmpty(simoperator)) {
            mccsim = Integer.parseInt(simoperator.substring(0, 3));
        }
        Log.i("MCCUtil", "mccsim ==" + mccsim + "; mcc==" + mcc);
        if (mccsim == mcc && mcc == 525) {
            Log.i("MCCUtil", "enable");
            return true;
        }
        Log.i("MCCUtil", "disable");
        return false;
    }

    public static boolean isRoaming() {

        boolean result = true;

        if (isMCCInSG() && "65".equals(SPUtil.getCode())) {

            result = false;
        }
        Log.i("MCCUtil", "isMCCInSG: " + isMCCInSG() + ";SPUtil.getCode()== " + SPUtil.getCode() + " ----> isRoaming : " + result);
        return result;
    }

    public static boolean NoSimCard() {
        TelephonyManager tel = (TelephonyManager) App.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
        //return tel.getSimState() == TelephonyManager.SIM_STATE_ABSENT;
        String simoperator = tel.getSimOperator();
        return TextUtils.isEmpty(simoperator);

    }

    /**
     * when no simcard , show roaming; when simcardMcc==NetMecc, no show roaming
     *
     * @return
     */
    public static boolean isSimCardInHomeCountry() {

        TelephonyManager tel = (TelephonyManager) App.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
        String networkOperator = tel.getNetworkOperator();
        String simoperator = tel.getSimOperator();
        int mcc = -1, mccsim = -1;
        try {
            if (!TextUtils.isEmpty(networkOperator)) {
                mcc = Integer.parseInt(networkOperator.substring(0, 3));
            }
            if (!TextUtils.isEmpty(simoperator)) {
                mccsim = Integer.parseInt(simoperator.substring(0, 3));
            }
            Log.i("MCCUtil", "mccsim ==" + mccsim + "; mcc==" + mcc);
            if (mccsim == mcc) {
                Log.i("MCCUtil", "enable");
                return false;
            }
        }catch (Exception e){
            Log.i("MCCUtil", "exception "+e.getMessage());
            return false;
        }
        return true;
    }
}
