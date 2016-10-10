package panhui.cn.bwf.home.ui.frament;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import panhui.cn.bwf.home.adapter.CartAdater;
import panhui.cn.bwf.home.bean.AddrBean;
import panhui.cn.bwf.home.bean.ProductBean;
import panhui.cn.bwf.home.server.ShipingGb;
import panhui.cn.bwf.itemmall.R;
import panhui.cn.bwf.itmmall.confing.base.CallBack;
import panhui.cn.bwf.itmmall.confing.base.Receiving;
import panhui.cn.bwf.itmmall.confing.sql.SQLHolder;

/**
 * Created by Administrator on 2016/6/13.
 */
public class CartFragment extends Fragment {


    private ListView item_cart_listview;

    private CartAdater caeradater;

    private List<ProductBean> list;

    //    添加尾部局
    private View footView;
    //    查询数据的对象
    private SQLHolder sql;

    private Gson gson;

    private ShipingGb serler;
    //    价钱对象
    private TextView view_card_qian;

//    收货地址的列表对象
    private ListView shipping_list;
    private List<AddrBean> list_add;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//布局
        View view = inflater.inflate(R.layout.item_cart, container, false);
//列表对象
        item_cart_listview = (ListView) view.findViewById(R.id.item_cart_listview);
//尾部局
        footView = inflater.inflate(R.layout.view_card_foot, null);
//金钱对象
        view_card_qian = (TextView) footView.findViewById(R.id.view_card_qian);
//        购买
        footView.findViewById(R.id.view_card_delete).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Receiving receiving = new Receiving(getActivity(),list,true);
                CallBack callBack = receiving.getCallBack();
                serler.queryGd(callBack);
            }
        });

//添加尾部局
        item_cart_listview.addFooterView(footView);
//数据库对象
        sql = new SQLHolder();
//接口对象
        serler = new ShipingGb();
//gs对象
        gson = new Gson();
//广播
        registerBroadcast();

//读取数据
        try {
            list = sql.getCard(getActivity());
            caeradater = new CartAdater(getActivity(), list);
            item_cart_listview.setAdapter(caeradater);
            getTotal();
        } catch (Exception e) {
        }

        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            try {
                list = sql.getCard(getActivity());
                caeradater.setList(list);
                getTotal();
            } catch (Exception e) {

            }
        }
    }

    //注册广播
    private void registerBroadcast() {

        IntentFilter filter = new IntentFilter();
        filter.addAction("haha");
        getActivity().registerReceiver(receiver, filter);
    }

    //声明广播对象
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("haha")) {
                getTotal();
            }
        }
    };

    public void getTotal() {

        Log.e("數組长度", "getTotal: " + list.size());
        if (list.size() > 0) {
            footView.setVisibility(View.VISIBLE);
            //价钱总数
            float TOTAL_NUMBER = 0;
            //遍历集合里面的对象
            for (ProductBean bean : list) {
                if (bean.getCount() == 0) {
                    break;
                } else {
                    float number = bean.getProductPrice2();
//                    四舍五入的方法
                    number = (float) Math.rint(number);
                    TOTAL_NUMBER = TOTAL_NUMBER + number * bean.getCount();
                }
            }
            view_card_qian.setText(TOTAL_NUMBER + "");
        } else {
            footView.setVisibility(View.GONE);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);
    };

}
