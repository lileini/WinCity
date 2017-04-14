package wincity.litao.com.util;

import com.orhanobut.logger.Logger;
import com.orhanobut.logger.Settings;

/**
 * created by litao
 **/
public class LogUtil {
    public static void i(String tag,String message){
        Settings setting = Logger.init(tag);
//        setting.logLevel(LogLevel.FULL); //  显示全部日志，LogLevel.NONE不显示日志，默认是Full
//                .methodCount(5)         //  方法栈打印的个数，默认是2
//                .methodOffset(0)        //  设置调用堆栈的函数偏移值，0的话则从打印该Log的函数开始输出堆栈信息，默认是0
//                .hideThreadInfo();      //  隐藏线程信息

        Logger.i(message);
    }
    public static void e(String tag,String message){
//        if (!BuildConfig.DEBUG)
//            return;
        Logger.init(tag);
        Logger.e(message);
    }
    public static void d(String tag,String message){

        Logger.init(tag);
        Logger.d(message);
    }
    public static void i(String tag,String message,Object... args){
        Logger.init(tag);
        Logger.i(message,args);
    }
    public static void e(String tag,String message,Object... args){
        Logger.init(tag);
        Logger.e(message,args);
    }
    public static void d(String tag,String message,Object... args){
        Logger.init(tag);
        Logger.d(message,args);
    }
    public static void json(String tag,String json){
        Logger.init(tag);
        Logger.json(json);
    }

}
