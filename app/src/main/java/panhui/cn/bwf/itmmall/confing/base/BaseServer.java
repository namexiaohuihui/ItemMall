package panhui.cn.bwf.itmmall.confing.base;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import framework.xutil.LLog;
import panhui.cn.bwf.itmmall.confing.MyApplication;
import panhui.cn.bwf.itmmall.confing.MyConfig;

/**
 * 网络请求类：post和get
 * Created by Administrator on 2016/6/14.
 */
public class BaseServer {
    /**
     * post请求：通过json数据请求
     *
     * @param post
     * @param map
     * @param callBack
     */
    public void post(String post, Map<String, Object> map, final CallBack callBack) {
        //服务器地址已经在MyConfig类里面定义好了，直接调用就可以了
        String url = MyConfig.url + post;
//        RequestQueue对象在MyApplication里面已经实例化了，直接调用就可以使用了。
        RequestQueue queue = MyApplication.getQueue();
//        将map数据解析成json数据
        JSONObject object = new JSONObject(map);
//        开始解析
        JsonObjectRequest request = new JsonObjectRequest(Method.POST, url, object,
                new Response.Listener<JSONObject>() {
                    //    数据从服务器返回数据时调用
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        if (jsonObject != null) {
                            callBack.onResponse(jsonObject.toString());
                        }
                    }
                }, new ErrorListener() {
            //            返回错误时调用
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                LLog.e("post error " + volleyError.toString());
            }
        });
        queue.add(request);
    }


    /**
     * post2请求:通过string数据请求
     *
     * @param post
     * @param map
     * @param callBack
     */
    public void post2(String post, Map<String, Object> map, final CallBack callBack) {
        //服务器地址已经在MyConfig类里面定义好了，直接调用就可以了
        String url = MyConfig.url + post;
//        RequestQueue对象在MyApplication里面已经实例化了，直接调用就可以使用了。
        RequestQueue queue = MyApplication.getQueue();
//        将map数据解析成json数据
        JSONObject object = new JSONObject(map);
//        开始解析
        StringRequest_Str request = new StringRequest_Str(Method.POST, url, object,
                new Response.Listener<String>() {
                    //    数据从服务器返回数据时调用
                    @Override
                    public void onResponse(String jsonObject) {
                            callBack.onResponse(jsonObject.toString());
                    }
                }, new ErrorListener() {
            //            返回错误时调用
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                LLog.e("post error " + volleyError.toString());
            }
        });
        queue.add(request);
    }


    public void get(String post, Map<String, Object> map, final CallBack callBack) {
        String url = MyConfig.url + post + "?";
        String params = "";
        if (map != null) {
            Set<?> entries = map.entrySet();
            if (entries != null) {
                Iterator<?> iterator = entries.iterator();
                while (iterator.hasNext()) {
                    Entry<?, ?> entry = (Entry<?, ?>) iterator.next();
                    Object key = entry.getKey();
                    Object value = entry.getValue();
                    params += "&" + key.toString() + "=" + value.toString();
                }
                if (params.length() > 1) {
                    params = params.substring(1, params.length());
                    url += params;
                }
            }
        }
        LLog.e("request------>",url);
        RequestQueue queue = MyApplication.getQueue();
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if (s != null) {
                    callBack.onResponse(s);
                }
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                LLog.e("post error------> " + volleyError.toString());
            }
        });
        queue.add(request);
    }
}
