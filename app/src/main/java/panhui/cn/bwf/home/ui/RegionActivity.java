package panhui.cn.bwf.home.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import panhui.cn.bwf.home.adapter.ProductAdater2;
import panhui.cn.bwf.home.bean.CountryBean;
import panhui.cn.bwf.home.bean.ReceiveBean;
import panhui.cn.bwf.home.server.QueryGd;
import panhui.cn.bwf.itemmall.R;
import panhui.cn.bwf.itmmall.confing.base.CallBack;
import panhui.cn.bwf.itmmall.confing.xutil.CostomViewUtil;

/**
 * 要显示地区
 * 1. 拿到数据
 * 2. 显示的对话框
 * 3. 适配器
 */
public class RegionActivity extends Activity implements OnClickListener {

    List<CountryBean> list_coun;

    AlertDialog.Builder builder = null;

    private TextView goujiaxuanze, shengfen, add_tijiao;
    private EditText receiver, receiveTel, receiveAddress;
    private CheckBox add_box;

    int index = 1;

    String name, ipone, dizhi;

    private GoogleApiClient client;

    private int isDefault = 0;

    private int id1, id2, id3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_address_activity);

        init();

//        点击国家
        goujiaxuanze.setOnClickListener(this);


//        点击省份
        shengfen.setOnClickListener(this);

//        点击提交
        add_tijiao.setOnClickListener(this);
    }

    private void init() {
        add_tijiao = (TextView) findViewById(R.id.add_tijiao);
        goujiaxuanze = (TextView) findViewById(R.id.goujiaxuanze);
        shengfen = (TextView) findViewById(R.id.shengfen);

        receiver = (EditText) findViewById(R.id.add_name);
        receiveTel = (EditText) findViewById(R.id.add_ipone);
        receiveAddress = (EditText) findViewById(R.id.add_dizhi);
        add_box = (CheckBox) findViewById(R.id.add_box);
    }

    public void provinces() {
        index = 2;
        new QueryGd().queryCountry(callback, index);
    }


    public void dioglv() {
        index = 1;
        new QueryGd().queryCountry(callback, index);
    }

    CallBack callback = new CallBack() {
        @Override
        public void onResponse(String response) {
//                回调的数据，将数据解析之后传给集合
            list_coun = new Gson().fromJson(response, new TypeToken<List<CountryBean>>() {
            }.getType());

            builder = new AlertDialog.Builder(RegionActivity.this);
            View view_dialog = LayoutInflater.from(RegionActivity.this).inflate(R.layout.dialog_layout, null);
            builder.setView(view_dialog);
//                    显示对话框
//                    对话框的列表
            ListView dialog_lv = (ListView) view_dialog.findViewById(R.id.dialog_lv);
//                    对话框标题
            TextView dialog_name = (TextView) view_dialog.findViewById(R.id.dialog_name);
            dialog_name.setText("信息选择");
            //                适配器
            ProductAdater2 adapter = new ProductAdater2(getApplicationContext(), list_coun);
            dialog_lv.setAdapter(adapter);
            final AlertDialog dialog = builder.show();


            dialog_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String name = null;
                    switch (index) {
                        case 1:
                            id2 = 0;
                            name = list_coun.get(position).getAreaName().toString();
                            goujiaxuanze.setText(name);
                            id2 = list_coun.get(position).getAreaId();
                            break;
                        case 2:
                            name = list_coun.get(position).getAreaName().toString();
                            shengfen.setText(name);
                            id3 = list_coun.get(position).getAreaId();
                            break;
                    }
                    dialog.dismiss();
                }
            });
        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goujiaxuanze:
                dioglv();
                break;
            case R.id.shengfen:
                provinces();
                break;
            case R.id.add_tijiao:
                Receiving();
                break;
        }
    }
    // name,ipone,dizhi;

    private void Receiving() {
//        获取数据
        name = receiver.getText().toString();
        ipone = receiveTel.getText().toString();
        dizhi = receiveAddress.getText().toString();
        String goujia = goujiaxuanze.getText().toString();
        String sf = shengfen.getText().toString();

//        判断是否有数据
        boolean x1 = name != null && name.length() > 0;
        boolean x2 = ipone != null && ipone.length() > 0;
        boolean x3 = dizhi != null && dizhi.length() > 0;
        boolean x4 = goujia != null && goujia.length() > 0;
        boolean x5 = sf != null && sf.length() > 0;
//        查看是否都为真

        if (!x1) {
            new CostomViewUtil().showToast(getApplicationContext(), "名字不能为空");
        } else if (!x2) {
            new CostomViewUtil().showToast(getApplicationContext(), "手机不能为空");
        } else if (!x3) {
            new CostomViewUtil().showToast(getApplicationContext(), "地址不能为空");
        } else if (!x4) {
            new CostomViewUtil().showToast(getApplicationContext(), "国家不能为空");
        } else if (!x5) {
            new CostomViewUtil().showToast(getApplicationContext(), "省份不能为空");
        }
        if (x1 && x2 && x3 && x4 && x5) {
            if (add_box.isChecked()) {
                isDefault = 1;
            } else {
                isDefault = 0;
            }
            ReceiveBean receiveBean = new ReceiveBean();
            id1 = id2 * 100 + id3;
//            是否默认
            receiveBean.setIsDefault(isDefault);
//            接收地址
            receiveBean.setReceiveAddress(dizhi);
//            区域id
            receiveBean.setReceiveAreaId(1);
//            接收人电话
            receiveBean.setReceiveTel(ipone);
//            接收人姓名
            receiveBean.setReceiver(name);

            new QueryGd().queryReceive(receiveBean,callBack);
        }
    }


    CallBack callBack = new CallBack() {
        @Override
        public void onResponse(String response) {
            new CostomViewUtil().showToast(getApplicationContext(),"收货单号：-->" + response);


        }
    };
}
