package panhui.cn.bwf.home.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import panhui.cn.bwf.home.adapter.AddrAdapter;
import panhui.cn.bwf.home.bean.AddrBean;
import panhui.cn.bwf.home.bean.ProductBean;
import panhui.cn.bwf.home.server.ShipingGb;
import panhui.cn.bwf.itemmall.R;
import panhui.cn.bwf.itmmall.confing.base.CallBack;

// WelComeActivity.this.finish();
public class ReceivingActivity extends Activity {

    private List<ProductBean> list;
    private ListView shipping_list;
    private List<AddrBean> list_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.shipping_address);
        shipping_list = (ListView) findViewById(R.id.shipping_list);
        //    读取收货人的地址信息

//        数据进行反馈
        new ShipingGb().queryGd(callBack);

        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_list,null);
        TextView textView = (TextView) view.findViewById(R.id.dialog_tv);

        //添加尾部局
        shipping_list.addFooterView(view);
        textView.setText("添加收货地址");
        textView.setBackgroundResource(R.drawable.product_dot);

//        点击收货地址
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReceivingActivity.this,RegionActivity.class);
                startActivity(intent);
            }
        });
    }

    CallBack callBack = new CallBack() {
        @Override
        public void onResponse(String response) {
//            L.i(response);
//            数据源
            list_add = new Gson().fromJson(
                    response,
                    new TypeToken<List<AddrBean>>() {
                    }.getType()
            );
            AddrAdapter addrAdapter = new AddrAdapter(getApplicationContext(), list_add, false);
            shipping_list.setAdapter(addrAdapter);
        }
    };
}