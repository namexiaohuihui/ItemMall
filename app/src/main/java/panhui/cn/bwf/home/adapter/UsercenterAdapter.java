package panhui.cn.bwf.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import panhui.cn.bwf.itemmall.R;

/**
 * Created by Administrator on 2016/6/17.
 */
public class UsercenterAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String,Object>> list;
    private LayoutInflater mInflater;

//    构造方法
    public UsercenterAdapter(Context context,List<Map<String,Object>> list){
        this.context = context;
        this.list = list;
        this.mInflater = LayoutInflater.from(context);
    }

//    返回集合的大小
    @Override
    public int getCount() {
        return list.size();
    }
//返回某个元素
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

//    返回某个元素的ID
    @Override
    public long getItemId(int position) {
        return position;
    }

//    内部类用于定义列表里面的数据
    class ViewHolder{
    ImageView user_pic,user_image;
    TextView user_tv;
}
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.usercente_list,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.user_image = (ImageView) convertView.findViewById(R.id.user_image);
            viewHolder.user_pic = (ImageView) convertView.findViewById(R.id.user_pic);
            viewHolder.user_tv = (TextView) convertView.findViewById(R.id.user_tv);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.user_pic.setImageResource((Integer) list.get(position).get("pic"));

        viewHolder.user_image.setImageResource((Integer) list.get(position).get("image"));

        viewHolder.user_tv.setText((String)list.get(position).get("tv"));

        return convertView;
    }
}
