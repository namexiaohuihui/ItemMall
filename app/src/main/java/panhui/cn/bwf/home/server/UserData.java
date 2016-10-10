package panhui.cn.bwf.home.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import panhui.cn.bwf.itemmall.R;

/**
 * Created by Administrator on 2016/6/17.
 */
public class UserData {

    private String name[] = new String[]{"个人信息管理",
            "收货信息管理", "订单信息管理", "个人收藏管理", "优惠券管理",
            "账户余额查询", "消息记录管理", "通用设置管理",};

    private int imag[] = new int[]{
            R.mipmap.tab_user_normal, R.mipmap.my_address,
            R.mipmap.tab_shopper_normal, R.mipmap.my_order,
            R.mipmap.my_promo, R.mipmap.my_card, R.mipmap.my_money,
            R.mipmap.my_com, R.mipmap.head_right,};

    public List<Map<String, Object>> getDate() {
        int leng = name.length ;
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < leng; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("pic", imag[i]);
            map.put("tv",name[i]);
            map.put("image",imag[leng]);
            list.add(map);
        }
        return list;
    }
}
