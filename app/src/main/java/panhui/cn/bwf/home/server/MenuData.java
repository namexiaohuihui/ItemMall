package panhui.cn.bwf.home.server;

import java.util.ArrayList;
import java.util.List;

import panhui.cn.bwf.home.bean.MenuBean;

/**
 * 首页列表数据源
 * Created by Administrator on 2016/6/13.
 */
public class MenuData {

    public List<MenuBean> getDate(){
        List<MenuBean> list = new ArrayList<MenuBean>();
        MenuBean menu1 = new MenuBean("推荐");
        MenuBean menu2 = new MenuBean("男装");
        MenuBean menu3 = new MenuBean("女装");
        MenuBean menu4 = new MenuBean("上衣");
        MenuBean menu5 = new MenuBean("裤子");
        MenuBean menu6 = new MenuBean("裙子");
        MenuBean menu7 = new MenuBean("连衣裙");
        MenuBean menu8 = new MenuBean("鞋子");
        MenuBean menu9 = new MenuBean("帽子");
        list.add(menu1);
        list.add(menu2);
        list.add(menu3);
        list.add(menu4);
        list.add(menu5);
        list.add(menu6);
        list.add(menu7);
        list.add(menu8);
        list.add(menu9);

        return list;
    }

}
