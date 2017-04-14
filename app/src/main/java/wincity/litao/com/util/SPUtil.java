package wincity.litao.com.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

import wincity.litao.com.App;

/**
 * Created by shang guangneng on 2016/6/8 0008.
 * GRUC Shareprefrence 管理类
 */
public class SPUtil {
    private static final String PROFILE_JSON = "profile_json";
    private static final String CMASK_INFO_JSON = "cmask_json";
    private static final String DAY = "day";
    private static final String THRESHOLD = "threshold";
    private static String REMAIND_DAY = "remaind_day";
    private static String GOOGLE_SERVICE_COUNTRL = "google_service_countrl";


    private SPUtil() {
    }

    /**
     * 保存在手机里面的文件名
     */
    private static String FULL_COUNTRY_NAME = "full_country_name";
    private static final String FILE_NAME = "share_data";
    private static final String USERNAME = "username";
    private static final String ACTOKEN = "access token with type";
    private static String USER_INFO = "user_info";
    private static final String PASSWORD = "password";
    private static final String TOKEN = "access_token";
    private static final String CODE = "country_code";
    private static final String ADD_CODE = "add_country_code";
    private static final String MOBILE = "mobile";
    private static final String ADD_MOBILE = "add_mobile";
    private static final String USER_ID = "user_id";
    private static final String USER_PRIVATE_PSD = "user_private_psd";
    private static final String DELETE_NUMBER_ID = "delete_number_id";
    private static final String INVITE_MESSAGE = "invite_message";
    private static final String TAG = "SPUtil";
    private static final String COUNTRY = "country";
    private static final String ISFIRST = "first open";
    private static final SharedPreferences sp = App.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

    public static int LOGIN_BY_PSD = 1;
    public static int LOGIN_BY_OTP = 2;
    private static String LOGIN_MODE = "login_mode";

    public static void saveLoginModel(int style) {
        sp.edit().putInt(LOGIN_MODE, style).apply();
    }

    public static int getLoginModel() {
        return sp.getInt(LOGIN_MODE, -1);
    }

    public static void saveCode(String code) {
        if (code.startsWith("+")) {
            code = code.substring(1, code.length());
        }
        sp.edit().putString(CODE, code).commit();
    }

    public static void saveAddCode(String code) {
        if (code.startsWith("+")) {
            code = code.substring(1, code.length());
        }
        sp.edit().putString(ADD_CODE, code).commit();
    }

    public static String getAddCode() {
        String code = sp.getString(ADD_CODE, "65");
        return code.startsWith("+") ? code.substring(1, code.length()) : code;
    }
    public static boolean getIsFirst(){
        return sp.getBoolean(ISFIRST,true);
    }
      public static void setIsFirst(boolean b){
        sp.edit().putBoolean(ISFIRST,b).commit();
    }
    public static String getCode() {
        String code = sp.getString(CODE, "65");
        return code.startsWith("+") ? code.substring(1, code.length()) : code;
    }
    public static void saveFullCountryName(String fullName) {
        sp.edit().putString(FULL_COUNTRY_NAME, fullName).commit();
    }
    public static String getFullCountryName() {
        return sp.getString(FULL_COUNTRY_NAME, "");
    }

    public static void saveCountry(String country) {
        sp.edit().putString(COUNTRY, country).commit();
    }

    public static String getCountry() {
        return sp.getString(COUNTRY, "sg");
    }

    public static void saveMobile(String code) {
        sp.edit().putString(MOBILE, code).commit();
    }

    public static void saveAddMobile(String code) {
        sp.edit().putString(ADD_MOBILE, code).commit();
    }

    public static String getAddMobile() {
        return sp.getString(ADD_MOBILE, "");
    }

    public static String getMobile() {
        return sp.getString(MOBILE, "");
    }





    public static String getUsername() {
        return sp.getString(USERNAME, "username");
    }

    public static void saveACToken(String actoken) {
        sp.edit().putString(ACTOKEN, actoken).commit();
    }

    public static String getACToken() {
        return sp.getString(ACTOKEN, "");
    }

    public static void saveUserID(String userID) {
        sp.edit().putString(USER_ID, userID).commit();
    }

    public static String getUserId() {
        return sp.getString(USER_ID, "");
    }

    public static void savePassword(String password) {
        sp.edit().putString(PASSWORD, password).commit();
    }

    public static String getPassword() {
        return sp.getString(PASSWORD, "password");
    }

    public static void saveToken(String token) {
        sp.edit().putString(TOKEN, token).commit();
    }

    /*public static String getToken() {
        String token = "JWT " + sp.getString(TOKEN, "token");
        Log.i(TAG, token);
        return token;
    }*/

    public static void saveUserPrivatePsd(String userInfo) {
        sp.edit().putString(USER_PRIVATE_PSD, userInfo).commit();

    }

    public static String getUserPrivatePsd() {
        return sp.getString(USER_PRIVATE_PSD, "");
    }




    /**
     * 移除某个key值已经对应的值
     *
     * @param key
     */
    public static void remove(String key) {
        sp.edit().remove(key).commit();
    }

    /**
     * 清除所有数据
     */
    public static void clear() {
        sp.edit().clear().commit();
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param key
     * @return
     */
    public static boolean contains(String key) {
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @return
     */
    public static Map<String, ?> getAll() {
        return sp.getAll();
    }

    /**
     * save user profile
     *
     * @param JSON json string from service
     */
    public static void saveProfile(String JSON) {
        sp.edit().putString(PROFILE_JSON, JSON).apply();
//        try {
//            Gson gson = new Gson();
//            ProfileEntity profileEntity = gson.fromJson(JSON, ProfileEntity.class);
//            Phonenumber.PhoneNumber phoneNumber = NumberUtil.checkMobile("+"+profileEntity.getMobile_phone());
//            saveCode(""+phoneNumber.getCountryCode());
//            saveMobile(""+phoneNumber.getNationalNumber());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
     public static void saveCMaskInfo(String JSON) {
        sp.edit().putString(CMASK_INFO_JSON, JSON).apply();
    }

    public static void saveRegisterName(String registerName) {

        sp.edit().putString("register_name", registerName).commit();
    }

    /**
     * call throught number from api
     * @param callthrough
     */
    public static void saveCallThroughNumber(String callthrough) {

        sp.edit().putString("call_through_number", callthrough).commit();
    }

    public static String getCallThroughNumber() {
        return sp.getString("call_through_number", "");
    }

    /**
     * call back number from api
     * @param callback
     */
    public static void saveCallBackNumber(String callback) {

        sp.edit().putString("call_back_number", callback).commit();
    }

    public static String getCallbackNumber() {
        return sp.getString("call_back_number", "");
    }

    public static String getRegisterName() {
        return sp.getString("register_name", "");
    }

    public static void saveDeleteId(String delete_id) {
        sp.edit().putString(DELETE_NUMBER_ID, delete_id).commit();
    }

    public static String getDeleteId() {
        return sp.getString(DELETE_NUMBER_ID, "");
    }
    public static void saveInivteMessage(String msg){
        sp.edit().putString(INVITE_MESSAGE, msg).commit();
    }

    public static String getInviteMessage() {
        return sp.getString(INVITE_MESSAGE, "");
    }

    public static void saveRemainedDay(int remainedDay){
        sp.edit().putInt(REMAIND_DAY, remainedDay).commit();
    }
    public static int getRemainedDay() {
        return sp.getInt(REMAIND_DAY,-1);
    }

    public static String getDay() {
        return sp.getString(DAY,"");
    }
     public static void saveDay(String day) {
        sp.edit().putString(DAY,day).commit();
    }

    public static void saveThreshold(float threshold) {
        sp.edit().putFloat(THRESHOLD,threshold).commit();
    }
     public static float getThreshold() {
        return sp.getFloat(THRESHOLD,0.2f);
    }

    public static void saveGoogleServiceHint(boolean b) {
        sp.edit().putBoolean(GOOGLE_SERVICE_COUNTRL,b).commit();
    }
    public static boolean getGoogleServiceHint() {
        return sp.getBoolean(GOOGLE_SERVICE_COUNTRL,true);
    }
}
