package panhui.cn.bwf.home.ui.frament;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import panhui.cn.bwf.home.adapter.ProductAdater;
import panhui.cn.bwf.home.bean.ProductBean;
import panhui.cn.bwf.home.server.QueryGd;
import panhui.cn.bwf.itemmall.R;
import panhui.cn.bwf.itmmall.confing.base.CallBack;

/**
 * Created by Administrator on 2016/6/15.
 */
public class Home_Product extends Fragment{

    private GridView item_home_other_grid;

    //    网络请求
    private QueryGd server;
    private Gson gson;
    private String key;
    private int valey;
    private Map<String,Object> map;

    public Home_Product( String key,int valey){
        this.key= key;
        this.valey = valey;
    }

    //    数据页的对象
    private List<ProductBean> listpro2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     View view = inflater.inflate(R.layout.item_home_other,null);
        item_home_other_grid = (GridView) view.findViewById(R.id.item_home_other_grid);

        map = new HashMap<String,Object>();
        map.put(key,valey);
        gson = new Gson();
        server = new QueryGd();
       initGridView();
        return view;
    }

    private void initGridView() {
        server.queryProduct(-1,-1,map,new CallBack() {
            @Override
            public void onResponse(String response) {
//                回调的数据，将数据解析之后传给集合
                listpro2 = gson.fromJson(
                        response, new TypeToken<List<ProductBean>>() {
                        }.getType()
                );
//                适配器
                ProductAdater adapter = new ProductAdater(getActivity(), listpro2);
                item_home_other_grid.setAdapter(adapter);
            }
        });

    }
}
