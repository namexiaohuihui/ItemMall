package panhui.cn.bwf.itmmall.confing.xutil;

import android.content.Context;
import android.content.SharedPreferences;

import panhui.cn.bwf.itmmall.confing.Constant;
import panhui.cn.bwf.itmmall.confing.MyApplication;

/**
 * 该类用于保存用户的id、密码以及用户名
 * Created by Administrator on 2016/6/17.
 */
public class SharedPreferencesUtils {

//    创建SharedPreferences兑对象
    private static SharedPreferences sharedPreferences ;

    /**
     * 实行单例模式。如果存在SharedPreferences对象就直接返回，不存在就创建
     * @return  SharedPreferences对象
     */
    public static SharedPreferences  getSp(){

        if (sharedPreferences == null){
            sharedPreferences = MyApplication.getContext().
                    getSharedPreferences(Constant.BANGGOUSP,Context.MODE_PRIVATE);
        }
            return sharedPreferences;
    }


    /***
     * 保存ID
     * @param userId
     */
    public static void savaUserId(int userId){
//        sharedPreferences没有保存的方法，需要通过
        getSp().edit().putInt("userId",userId).commit();
        }

    /**
     * 获取ID
     * @return 返回用户的id
     */
    public static  int getId(){
        return getSp().getInt("userId",0);
    }

    /**
     * 保存密码和用户名
     * @param password
     * @param userName
     */
    public static  void savaUserId(String password,String userName){
        getSp().edit().putString("password",password).commit();
        getSp().edit().putString("userName",userName).commit();
    }


    /**
     * 获取用户名
     * @return  返回用户的名字
     */
    public static String getName(){
        return getSp().getString("userName","用户名获取成功");
    }

    /**
     * 获取密码
     * @return  返回用户的密码
     */
    public static String getPassword(){
        return getSp().getString("password","mimashibai");
    }
}
