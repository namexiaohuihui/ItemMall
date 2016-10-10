package panhui.cn.bwf.home.ui.frament;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import panhui.cn.bwf.home.adapter.ProductAdater;
import panhui.cn.bwf.home.adapter.ViewPagerAdapter;
import panhui.cn.bwf.home.bean.AdvertBaen;
import panhui.cn.bwf.home.bean.ProductBean;
import panhui.cn.bwf.home.server.QueryGd;
import panhui.cn.bwf.itemmall.R;
import panhui.cn.bwf.itmmall.confing.base.CallBack;

/**
 * Created by Administrator on 2016/6/14.
 */
@SuppressLint("ValidFragment")
public class Home_Recommended extends Fragment {
    private ViewPager item_home_viewpager;
    private LinearLayout item_home_linear;
    private GridView item_home_gridview;
    //    网络请求
    private QueryGd server;
    private Gson gson;
    //    广告页的对象
    private List<AdvertBaen> listadver;
    //    数据页的对象
    private List<ProductBean> listpro;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_home_recommended, null);
        findview(view);
        gson = new Gson();
//        网络请求实例化
        server = new QueryGd();
            initViewPager();
        initGridView();
        return view;
    }

    private void initGridView() {
        server.queryProduct(-1,-1,null,new CallBack() {
            @Override
            public void onResponse(String response) {
//                回调的数据，将数据解析之后传给集合
                listpro = gson.fromJson(
                        response, new TypeToken<List<ProductBean>>() {
                        }.getType()
                );
//                适配器
                ProductAdater adapter = new ProductAdater(getActivity(), listpro);
                item_home_gridview.setAdapter(adapter);
            }
        });

    }


    private void initViewPager() {
        server.queryGd(new CallBack() {
            @Override
            public void onResponse(String response) {
//                gson解析json格式的list数据
                listadver = gson.fromJson(response, new TypeToken<List<AdvertBaen>>() {
                }.getType());
                ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity(), listadver);
                item_home_viewpager.setAdapter(adapter);
//                动态添加viewpager导航点
                for (int i = 0; i < listadver.size(); i++) {
                    CheckBox bt = new CheckBox(getActivity());
                    if (i == 0) {
                        bt.setChecked(true);
                    } else {
                        bt.setChecked(false);
                    }
//                    去掉前面的图片
                    bt.setButtonDrawable(null);
//                    添加背景图片
                    bt.setBackgroundResource(R.drawable.itme_home_gundong);
//                    不可点击和失去焦点
                    bt.setFocusable(false);
                    bt.setClickable(false);
                    item_home_linear.addView(bt, 30, 30);
//                    设置导航点的间距
                    ViewGroup.MarginLayoutParams params = (MarginLayoutParams) bt.getLayoutParams();
                    params.leftMargin = 5;
//                    刷新，必须在addview之后调用
                    bt.requestFocus();
                }
                item_home_viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageSelected(int i) {
                        for (int j = 0; j < listadver.size(); j++) {
                            if (i == j) {
//                                根据下标来读取相应的CheckBox对象
                                CheckBox bx = (CheckBox) item_home_linear.getChildAt(j);
                                bx.setChecked(true);
                            } else {
                                CheckBox bx = (CheckBox) item_home_linear.getChildAt(j);
                                bx.setChecked(false);
                            }
                        }
                    }
                    @Override
                    public void onPageScrolled(int i, float v, int i1) {

                    }
                    @Override
                    public void onPageScrollStateChanged(int i) {

                    }
                });
            }
        });
    }
    private void findview(View view) {
        item_home_viewpager = (ViewPager) view.findViewById(R.id.item_home_viewpager);
        item_home_gridview = (GridView) view.findViewById(R.id.item_home_gridview);
        item_home_linear = (LinearLayout) view.findViewById(R.id.item_home_linear);
    }
}
