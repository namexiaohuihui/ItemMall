package framework.xutil;

import android.util.Log;

/**
 * Created by Administrator on 2016/6/13.
 */
public class LLog {

    private static boolean isDABoolean = true;
    private static String TAG = "aa";
    /**传入字符串让日志猫显示*/
    public static void i(String msg){
        Log.i(TAG,msg);
    }
    public static void w(String msg){
        Log.i(TAG,msg);
    }
    public static void d(String msg){
        Log.i(TAG,msg);
    }
    public static void e(String msg){
        Log.i(TAG,msg);
    }

    /**传入int让日志猫显示*/
    public static void i(int msg){
        Log.i(TAG,msg + "");
    }
    public static void w(int msg){
        Log.i(TAG,msg + "");
    }
    public static void d(int msg){
        Log.i(TAG,msg + "");
    }
    public static void e(int msg){
        Log.i(TAG,msg + "");
    }

    /**传入double让日志猫显示*/
    public static void i(double msg){
        Log.i(TAG,msg + "");
    }
    public static void w(double msg){
        Log.i(TAG,msg + "");
    }
    public static void d(double msg){
        Log.i(TAG,msg + "");
    }
    public static void e(double msg){
        Log.i(TAG,msg + "");
    }
    /**传入两个数让日志猫显示*/
    public static void i(Object msg,Object data){
        Log.i(TAG,msg.toString() + "------>" + data.toString());
    }
    public static void w(Object msg,Object data){
        Log.i(TAG,msg.toString() + "------>" + data.toString());
    }
    public static void d(Object msg,Object data){
        Log.i(TAG,msg.toString() + "------>" + data.toString());
    }
    public static void e(Object msg,Object data){
        Log.i(TAG,msg.toString() + "------>" + data.toString());
    }
}
