package panhui.cn.bwf.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import panhui.cn.bwf.home.bean.AddrBean;
import panhui.cn.bwf.itemmall.R;

/**
 * Created by Administrator on 2016/6/17.
 */
public class AddrAdapter  extends BaseAdapter{


    Context context;
    List<AddrBean> list;
    LayoutInflater mInflater;
    private boolean delete= false;
    public AddrAdapter(Context context, List<AddrBean> list,boolean delete){
        this.context = context;
        this.list = list;
        this.delete = delete;
        mInflater = LayoutInflater.from(context);

    }

    public void setList(List<AddrBean> list) {
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


    class ViewHolder{
        TextView shipping_list_shr,shipping_list_tlb,shipping_list_add,shipping_list_mr,shipping_list_delete;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null ){
            convertView = mInflater.inflate(R.layout.shipping_list,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.shipping_list_shr = (TextView) convertView.findViewById(R.id.shipping_list_shr);
            viewHolder.shipping_list_tlb = (TextView) convertView.findViewById(R.id.shipping_list_tlb);
            viewHolder.shipping_list_add = (TextView) convertView.findViewById(R.id.shipping_list_add);
            viewHolder.shipping_list_mr = (TextView) convertView.findViewById(R.id.shipping_list_mr);
            viewHolder.shipping_list_delete = (TextView) convertView.findViewById(R.id.shipping_list_delete);
        convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        AddrBean bean = list.get(position);
        viewHolder.shipping_list_shr.setText(bean.getReceiver());
        viewHolder.shipping_list_tlb.setText(bean.getReceiveTel());
        viewHolder.shipping_list_add.setText(bean.getReceiveAddress());
        if (!delete){
            viewHolder.shipping_list_delete.setVisibility(View.VISIBLE);
        }else{
            viewHolder.shipping_list_delete.setVisibility(View.GONE);
        }
//如果是默认收获地址就显示出来
        if(bean.getIsDefault() == 1){
            viewHolder.shipping_list_mr.setVisibility(View.VISIBLE);
        }else{
            viewHolder.shipping_list_mr.setVisibility(View.GONE);
        }
        return convertView;
    }
}
