package panhui.cn.bwf.home.server;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import panhui.cn.bwf.home.bean.ReceiveBean;
import panhui.cn.bwf.home.bean.UnameAndUpwdBean;
import panhui.cn.bwf.itmmall.confing.base.BaseServer;
import panhui.cn.bwf.itmmall.confing.base.CallBack;

/**
 * Created by Administrator on 2016/6/14.
 */
public class QueryGd extends BaseServer {


    /**
     * 查询广告
     */
    public void queryGd(CallBack callBack) {
        get("GetAdvance.action", null, callBack);
    }

    /**
     * 查询商品
     */
    public void queryProduct(int page, int pageSize, Map<String, Object> map, CallBack callBack) {

        if (page == -1 && pageSize == -1) {
            get("GetProduct.action", map, callBack);
        } else {
            //将页面和数据传入
            map.put("page", page);
            map.put("pageSize", pageSize);
            get("GetProduct.action", map, callBack);
        }
    }

    /**
     * 查询性别
     */
    public void querySex(CallBack callBack) {
        get("GetGendar.action", null, callBack);
    }


    /**
     * 查询部位
     */
    public void queryParts(CallBack callBack) {
        get("GetPart.action", null, callBack);
    }

    /**
     * 查询颜色
     */
    public void queryColor(CallBack callBack) {
        get("GetColor.action", null, callBack);
    }

    /**
     * 查询国家和地区
     */
    public void queryCountry(CallBack callBack,int index) {
        Map<String,Object> map = new HashMap<>();
        map.put("parentId",index);
        get("GetAreaChildren.action", map, callBack);
    }

    /**
     * 登陆的信息
     */
    public void queryUnameAndUpwd(UnameAndUpwdBean bean, CallBack callBack) {
        Map<String,Object> user = new HashMap<>();
        user.put("uname",bean.getUname());
        user.put("upwd",bean.getUpwd());
        Log.e("uname" ,user.get("uname") + "");
        Log.e("upwd" ,user.get("upwd") + "");
        if (user!=null){
            Log.e("map", "onResponse: "  + user.toString());
        }else{
            Log.e("user", "onResponse: "  + "kongdee ");
        }
        Map<String,Object> map = new HashMap<>();
        map.put("user",user);
       get("GetUserByUnameAndUpwd.action", map, callBack);
    }


    /**
     * 注册
     */
    public void queryAddUser(UnameAndUpwdBean bean, CallBack callBack) {

        Map<String,Object> user = new HashMap<>();
        user.put("nickName",bean.getNickName());
        user.put("uname",bean.getUname());
        user.put("upwd",bean.getUpwd());
        Map<String,Object> map = new HashMap<>();
        map.put("user",user);
        post2("AddUser.action", map, callBack);
    }

    /**
     * 添加收货地址
     */
    public void queryReceive(ReceiveBean receiveBean, CallBack callBack) {
        Map<String,Object> user = new HashMap<>();
        user.put("userId",1);

        Map<String,Object> receive = new HashMap<>();
        receive.put("isDefault",receiveBean.getIsDefault());
        receive.put("receiveAddress",receiveBean.getReceiveAddress());
        receive.put("receiveAreaId",receiveBean.getReceiveAreaId());
        receive.put("receiveTel",receiveBean.getReceiveTel());
        receive.put("receiver",receiveBean.getReceiver());

        Map<String,Object> map = new HashMap<>();
        map.put("user",user);
        map.put("receive",receive);

        post2("AddReceive.action", map, callBack);
    }

}

