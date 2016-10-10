package panhui.cn.bwf.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.utils.L;

import java.util.List;

import panhui.cn.bwf.home.bean.ProductBean;
import panhui.cn.bwf.itemmall.R;
import panhui.cn.bwf.itmmall.confing.MyConfig;
import panhui.cn.bwf.itmmall.confing.sql.SQLHolder;

/**
 * 该类用于购物车的显示，是购物车的适配器类
 * Created by Administrator on 2016/6/14.
 */
public class CartAdater extends BaseAdapter {
    private Context context;
    private List<ProductBean> list;
    private LayoutInflater mInflater;
    private SQLHolder sql;


    public CartAdater(Context context, List<ProductBean> list) {

        this.context = context;
        this.list = list;

        mInflater = LayoutInflater.from(context);
        sql = new SQLHolder();
    }

    //    刷新数据
    public void setList(List<ProductBean> list) {
        if (list != null) {
            this.list = list;
            this.notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        ImageView cart_imageview;
        TextView cart_layout_title, cart_layout_num, cart_layout_xianjia;
        ImageButton cart_layout_jian, cart_layout_jia;
        Button cart_layout_delete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder view = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.cart_layout, parent, false);
            view = new ViewHolder();
            view.cart_imageview = (ImageView) convertView.findViewById(R.id.cart_imageview);
            view.cart_layout_num = (TextView) convertView.findViewById(R.id.cart_layout_num);
            view.cart_layout_title = (TextView) convertView.findViewById(R.id.cart_layout_title);
            view.cart_layout_xianjia = (TextView) convertView.findViewById(R.id.cart_layout_xianjia);
            view.cart_layout_jian = (ImageButton) convertView.findViewById(R.id.cart_layout_jian);
            view.cart_layout_jia = (ImageButton) convertView.findViewById(R.id.cart_layout_jia);
            view.cart_layout_delete = (Button) convertView.findViewById(R.id.cart_layout_delete);

            view.cart_layout_delete.setText("删除");

            convertView.setTag(view);
        }
        //
        else {
            view = (ViewHolder) convertView.getTag();
        }

//        传入网址和图片对象就可以实现显示图片
        ImageLoader.getInstance().displayImage(
                MyConfig.imageurl + list.get(position).getProductImage(),
                view.cart_imageview);

//取出对象，然后通过对象来读取相应的数据并显示出来
        view.cart_layout_title.setText((String) list.get(position).getProductName());

//        获取金钱的大小
        float number = list.get(position).getProductPrice2();
//            四舍五入的方法
        number = (float) Math.rint(number);

        //设置物品的数量和物品的单额
        view.cart_layout_xianjia.setText(number + "");
        view.cart_layout_num.setText(list.get(position).getCount() + "");

//        减少的按钮
        view.cart_layout_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = list.get(position).getCount();
                if (count > 0) {
//                    如果大于0就告诉自减一
                    count--;
                    if (count == 0) {
                        try {
                            // 如果数量为0就将这个对象删除
                            sql.deleteCard(context, list.get(position).getProductId());
                            // 重新获取数据库的值
                            list.remove(position);
                            setList(list);
                            context.sendBroadcast(new Intent("haha"));
                        } catch (Exception e) {
                            L.e("card 减 error,此时数量为零" + e.toString());
                        }
                    } else {
                        try {
//                    通知数据库更新数据
                            sql.upDataCard(context, list.get(position).getProductId(), count);
//                    重新设置数据
                            list.get(position).setCount(count);
//                    重新获取数据库的值
                            list = sql.getCard(context);
                            setList(list);
                            context.sendBroadcast(new Intent("haha"));
                        } catch (Exception e) {
                            L.e("card 减 error,此时数量大于零" + e.toString());
                        }
                    }
                }
            }
        });

//        增加的按钮
        view.cart_layout_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
//                  数量增加
                    int count = list.get(position).getCount();
                    count++;
//                    数据库要变化
                    sql.upDataCard(context, list.get(position).getProductId(), count);
//                    对象容器要变化
                    list.get(position).setCount(count);
//                    重新获取数据
                    setList(list);
//                    發送广播
                    context.sendBroadcast(new Intent("haha"));
                } catch (Exception e) {
                    L.e("card 加 error" + e.toString());
                }
            }
        });
//        单个删除
        view.cart_layout_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                数据库删除对象
                sql.deleteCard(context, list.get(position).getProductId());
//                列表删除对象
                list.remove(position);
//                更新数据
                setList(list);
                context.sendBroadcast(new Intent("haha"));
            }
        });
        return convertView;
    }
}
