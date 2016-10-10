package panhui.cn.bwf.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import panhui.cn.bwf.home.bean.CountryBean;
import panhui.cn.bwf.itemmall.R;

/**
 * Created by Administrator on 2016/6/14.
 */
public class ProductAdater2 extends BaseAdapter {
    private Context context;
    private List<CountryBean> list;
    private LayoutInflater mInflater;

    public ProductAdater2(Context context, List<CountryBean> list) {
        this.context = context;
        this.list = list;
        mInflater = LayoutInflater.from(context);
    }

    public void setList(List<CountryBean> list){
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

        TextView dialog2_id,  dialog2_tv;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder view = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.dialog_list2, parent, false);
            view = new ViewHolder();
            view.dialog2_id = (TextView) convertView.findViewById(R.id.dialog2_id);
            view.dialog2_tv = (TextView) convertView.findViewById(R.id.dialog2_tv);
            convertView.setTag(view);
        }
        //
        else {
            view = (ViewHolder) convertView.getTag();
        }
//取出对象，然后通过对象来读取相应的数据并显示出来
        view.dialog2_id.setText(list.get(position).getAreaId() + "");
        view.dialog2_tv.setText(list.get(position).getAreaName() + "");
        return convertView;
    }
}
