package panhui.cn.bwf.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;

import java.util.List;

import panhui.cn.bwf.home.bean.PartBean;
import panhui.cn.bwf.itemmall.R;

/**
 * Created by Administrator on 2016/6/15.
 */
public class PartSelectAdapter extends BaseAdapter {

    private Context context;
    private List<PartBean> list;
    private LayoutInflater mInfla;

    //构造方法
    public PartSelectAdapter(Context context, List<PartBean> list){
        this.context =context;
        this.list = list;
        mInfla = LayoutInflater.from(context);
    }
//用于刷新数据
    public void setList(List<PartBean> list){
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


    class ViewHodle{
        RadioButton popup_item_textview;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodle viewHodle = null;
        if(convertView == null){
            convertView = mInfla.inflate(R.layout.popup_item_list,parent,false);
            viewHodle = new ViewHodle();
            viewHodle.popup_item_textview = (RadioButton) convertView.findViewById(R.id.popup_item_textview);
            convertView.setTag(viewHodle);
        }else {
            viewHodle = (ViewHodle) convertView.getTag();
        }
        viewHodle.popup_item_textview.setText(list.get(position).getPartName());
        return convertView;
    }
}
