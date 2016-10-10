package panhui.cn.bwf.home.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import panhui.cn.bwf.home.bean.ProductBean;
import panhui.cn.bwf.itemmall.R;
import panhui.cn.bwf.itmmall.confing.MyConfig;

/**
 * Created by Administrator on 2016/6/14.
 */
public class ProductAdater extends BaseAdapter {
    private Context context;
    private List<ProductBean> list;
    private LayoutInflater mInflater;

    public ProductAdater(Context context, List<ProductBean> list) {
        this.context = context;
        this.list = list;
        mInflater = LayoutInflater.from(context);
    }

    public void setList(List<ProductBean> list){
        this.list = list;
        notifyDataSetChanged();
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
        ImageView layout_pic;
        TextView layout_title, layout_yuanjia, layout_xianjia;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder view = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.layout, parent, false);
            view = new ViewHolder();
            view.layout_pic = (ImageView) convertView.findViewById(R.id.layout_pic);

            view.layout_title = (TextView) convertView.findViewById(R.id.layout_title);
            view.layout_yuanjia = (TextView) convertView.findViewById(R.id.layout_yuanjia);
            view.layout_xianjia = (TextView) convertView.findViewById(R.id.layout_xianjia);
            convertView.setTag(view);
        }
        //
        else {
            view = (ViewHolder) convertView.getTag();
        }
//        传入网址和图片对象就可以实现显示图片
        ImageLoader.getInstance().displayImage(
                MyConfig.imageurl + list.get(position).getProductImage(),
                view.layout_pic);
//取出对象，然后通过对象来读取相应的数据并显示出来
        view.layout_title.setText(list.get(position).getProductName());
        view.layout_yuanjia.setText(list.get(position).getProductPrice1() + "");
//        设置下划线
        view.layout_yuanjia.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
        view.layout_xianjia.setText(list.get(position).getProductPrice2() + "");
        return convertView;
    }
}
