package panhui.cn.bwf.itmmall.confing.base;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import framework.xutil.SystemUtil;
import panhui.cn.bwf.home.adapter.AddrAdapter;
import panhui.cn.bwf.home.bean.AddrBean;
import panhui.cn.bwf.home.bean.OrderBean;
import panhui.cn.bwf.home.bean.OrderDetailBean;
import panhui.cn.bwf.home.bean.ProductBean;
import panhui.cn.bwf.home.server.ShipingGb;
import panhui.cn.bwf.itemmall.R;
import panhui.cn.bwf.itmmall.confing.xutil.CostomViewUtil;

/**
 * Created by Administrator on 2016/6/18.
 */
public class Receiving {
    private List<AddrBean> list_add;
    private ListView shipping_list;
    private Context context;
    ShipingGb serler;
    private List<ProductBean> list;
    private boolean delete= false;
    public Receiving(Context context,List<ProductBean> list,boolean delete) {
        this.list = list;
        this.context = context;
        this.delete = delete;
        this.serler = new ShipingGb();
    }

    public CallBack getCallBack() {
//    读取收货人的地址信息
        CallBack callBack = new CallBack() {
            @Override
            public void onResponse(String response) {
//            数据源
                list_add = new Gson().fromJson(
                        response,
                        new TypeToken<List<AddrBean>>() {
                        }.getType()
                );
//自定义对话框，加载布局
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//            列表对象
                View view = LayoutInflater.from(context).inflate(
                        R.layout.shipping_address, null);

                builder.setView(view);
                builder.setTitle("选择收货地址");
                shipping_list = (ListView) view.findViewById(R.id.shipping_list);
//            显示
                AlertDialog dialog = builder.show();
                if (delete){
                    //适配器
                    AddrAdapter addrAdapter = new AddrAdapter(context, list_add,delete);
                    shipping_list.setAdapter(addrAdapter);
//            点击收获列表支付成功
                    getListing(dialog);
                }
            }
        };

        return callBack;
    }

    //            点击收获列表支付成功
    public void getListing(final AlertDialog dialog){
        shipping_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                点击之后隐藏对话框
                dialog.dismiss();
//                获取点击地址的对象
                AddrBean addrBean = list_add.get(position);
//                    将訂單上传服务器
                OrderBean bean = new OrderBean();
                bean.setBookState(2);
//                訂單生成时间
                bean.setBookTime(SystemUtil.getCurrentDate());
//                訂單收货地址
                bean.setReceiveAddress(addrBean.getReceiveAddress());
//                地区ID
                bean.setReceiveAreaId(addrBean.getReceiveAreaId());
//                收货人电话
                bean.setReceiveTel(addrBean.getReceiveTel());
//                收货人姓名
                bean.setReceiver(addrBean.getReceiver());
//                收货时间
                bean.setReceiveTime("");
//                訂單提交的时间
                bean.setSendTime("");

                //            点击收获收货人生成訂單
                getOrder(bean);
            }
        });
    }


    //                点击收获收货人生成訂單
    public void getOrder(final OrderBean bean){

        serler.addOrder(bean, new CallBack() {
            //            返回訂單号
            @Override
            public void onResponse(String response) {
                new CostomViewUtil().showToastShort(context,"訂單号"+ response);
                int bookId = Integer.parseInt(response);
                bean.setBookId(bookId);
                getDetails(bookId);
            }
        });
    }
    //    訂單详情提交成功支付成功
    public void getDetails(int bookId){
        List<OrderDetailBean> list_de = new ArrayList<>();
        for (ProductBean addrBean : list){
            OrderDetailBean detailBean = new OrderDetailBean();
//            选中的訂單商品数量
            detailBean.setAmount(addrBean.getProductAmount());
//            选中的訂單商品详情id
            detailBean.setDetailId(1);
//            选中的訂單商品id
            detailBean.setProductId(addrBean.getProductId());
//              选中的訂單商品价格（现价）
            detailBean.setProductPrice(addrBean.getProductPrice2());
            list_de.add(detailBean);
        }

        serler.addOrderDetail(list_de, bookId, new CallBack() {
            @Override
            public void onResponse(String response) {
                String string = response.equals("Y")?"支付成功":"訂單失败";
                new CostomViewUtil().showToastShort(context,string);

            }
        });
    }
}
