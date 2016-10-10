package panhui.cn.bwf.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;

import java.util.List;

import panhui.cn.bwf.home.bean.MenuBean;
import panhui.cn.bwf.itemmall.R;

/**首页列表适配器
 * Created by Administrator on2016/6/13.
 */
public class MenuAdapter extends BaseAdapter{

    private Context context;
    private List<MenuBean> list;
    private LayoutInflater mInflater;
    public MenuAdapter(Context context,List<MenuBean> list){
        this.context = context ;
        this.list = list ;
        this.mInflater = LayoutInflater.from(context);
    }

    public  void setList(List<MenuBean> list){
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.item_home_list,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.item_home_textview =
                    (RadioButton) convertView.findViewById(R.id.item_home_textview);
            convertView.setTag(viewHolder);
        }
        //
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
//      将数据进行显示出来
        viewHolder.item_home_textview.setText(list.get(position).getName());

     if (list.get(position).getState() == 0){
         viewHolder.item_home_textview.setChecked(false);
     }
        //
        else{
         viewHolder.item_home_textview.setChecked(true);
     }
        return convertView;
    }

    class ViewHolder{
        RadioButton item_home_textview;
    }
}
