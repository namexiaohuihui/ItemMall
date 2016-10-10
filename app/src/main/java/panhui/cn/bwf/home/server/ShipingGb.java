package panhui.cn.bwf.home.server;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import panhui.cn.bwf.home.bean.OrderBean;
import panhui.cn.bwf.home.bean.OrderDetailBean;
import panhui.cn.bwf.itmmall.confing.base.BaseServer;
import panhui.cn.bwf.itmmall.confing.base.CallBack;

/**
 * Created by Administrator on 2016/6/17.
 */
public class ShipingGb extends BaseServer {

    /**
     * 查询收货地址
     */
    public void queryGd(CallBack callBack) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", 1);
        get("GetReceiversByUserId.action", map, callBack);
    }

    /**
     * 添加订单
     * 需要訂單对象和回调对象
     */
    public void addOrder(OrderBean bean,CallBack callBack){
//        用户ID对象
        Map<String,Object> userMap = new HashMap<>();
        userMap.put("userId",1);

//        book对象的数据
        Map<String,Object> bookMap = new HashMap<>();
        bookMap.put("user",userMap);
        bookMap.put("bookState",2);
        bookMap.put("bookTime",bean.getBookTime());
        bookMap.put("receiveAddress",bean.getReceiveAddress());
        bookMap.put("receiveAreaId",bean.getReceiveAreaId());
        bookMap.put("receiveTel",bean.getReceiveTel());
        bookMap.put("receiveTime",bean.getReceiveTime());
        bookMap.put("receiver",bean.getReceiver());
        bookMap.put("sendTime",bean.getSendTime());

//        用一个map存着对象，通过key和valey来区分对象
        Map<String,Object> map = new HashMap<>();
        map.put("book",bookMap);
        Log.e("添加订单参数",map.toString());
        post2("AddBook.action",map,callBack);
    }


    /**
     * 添加訂單详情
     * 需要訂單详情对象和訂單号以及回调对象
     */
    public void addOrderDetail(List<OrderDetailBean> list,
                               int bookID,CallBack callBack){
//        訂單id集合
        Map<String,Object> map_id = new HashMap<>();
        map_id.put("bookId",bookID);

//        添加的商品信息的集合
        List<Map<String,Object>> _list = new ArrayList<>();
        for (OrderDetailBean bt : list){
            Map<String,Object> map_list = new HashMap<>();
            map_list.put("amount",bt.getAmount());
            map_list.put("detailId",bt.getDetailId());
            map_list.put("productId",bt.getProductId());
            map_list.put("productPrice",bt.getProductPrice());
            _list.add(map_list);
        }

//        最终一级的对象
        Map<String,Object> map = new HashMap<>();
        map.put("list",_list);
        map.put("book",map_id);

        Log.e("添加訂單详情参数",map.toString());
        post2("AddDetail.action",map,callBack);

    }
}
