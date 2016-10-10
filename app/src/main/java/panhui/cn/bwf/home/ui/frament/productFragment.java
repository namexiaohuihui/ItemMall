package panhui.cn.bwf.home.ui.frament;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.RadioButton;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import framework.view.PullToRefreshView;
import panhui.cn.bwf.itmmall.confing.xutil.CostomViewUtil;
import framework.xutil.SystemUtil;
import panhui.cn.bwf.home.adapter.ColorSelectAdapter;
import panhui.cn.bwf.home.adapter.PartSelectAdapter;
import panhui.cn.bwf.home.adapter.ProductAdater;
import panhui.cn.bwf.home.adapter.SexSelectAdapter;
import panhui.cn.bwf.home.bean.ColorBean;
import panhui.cn.bwf.home.bean.GendarBean;
import panhui.cn.bwf.home.bean.PartBean;
import panhui.cn.bwf.home.bean.ProductBean;
import panhui.cn.bwf.home.server.QueryGd;
import panhui.cn.bwf.home.ui.WebTbActivity;
import panhui.cn.bwf.itemmall.R;
import panhui.cn.bwf.itmmall.confing.base.CallBack;

/**
 * Created by Administrator on 2016/6/13.
 */
public class productFragment extends Fragment implements View.OnClickListener {

    //头部布局的对象
    LinearLayout product_lin;
    //上拉、下拉、、控件
    private PullToRefreshView product_pullToRefresh;
    //    gridview对象
    private GridView producr_gridview;
    //    按钮对象
    private RadioButton product_default_sort, product_price_sort, product_sales_sort;
    private RadioButton product_sex_select, product_part_select, product_color_select;
    //  gridview对象
    private GridView product_gridview;
    //接口对象以及分页参数
    private QueryGd server;
    private int page = 1; // 从第几个加载
    private int pageSize = 4;  //每次加载几张
    //数据对象
    private Map<String, Object> map;
    //    适配器
    private ProductAdater adater;
    //    js数据集合
    private List<ProductBean> list;
//    弹出的窗口
    private PopupWindow popuwindow;
//    窗口里面的布局方式
    private GridView popup_gridview ;
//    窗口布局的适配器
    private SexSelectAdapter sexsele;
    private PartSelectAdapter part;
    private ColorSelectAdapter color;
//    性别分类数据
    private List<GendarBean> listSex;
    private List<PartBean> listpart;
    private List<ColorBean> listcolor;
//    记录点击那个按钮
    private  int CLICK_BIN = 0 ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product, null);

        initView(view);
        initPopuWindow(inflater);
        init();
        getData();
        return view;
    }

    private void initPopuWindow(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.item_home_other,null);
//          实例化popupwindow对象，将view添加到popupwindow的对象中
        popuwindow = new PopupWindow(view);

//        获取屏幕的宽度和高度
        popuwindow.setWidth(SystemUtil.getWindowWidth(getActivity()));
        popuwindow.setHeight(SystemUtil.getWindowHeight(getActivity())/3);

//实例化下拉列表布局对象
        popup_gridview = (GridView) view.findViewById(R.id.item_home_other_grid);
//        设置每行的数量
        popup_gridview.setNumColumns(4);
        //设置点击事件
        popup_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                关闭popupwindow
                popuwindow.dismiss();
                switch (CLICK_BIN){
                    case 1:
                        if (listSex.get(position).getGendarId() != -1){
                            list.clear();
                            page = 1;
                            map.put("selectedGendarId",listSex.get(position).getGendarId());
                            getData();
                        }
                        break;
                    case 2:
                        if (listpart.get(position).getPartId() != -1){
                            list.clear();
                            page = 1;
                            map.put("selectedPartId",listpart.get(position).getPartId());
                            getData();
                        }
                        break;

                    case 3:
                        if (listcolor.get(position).getColorId() != -1){
                            list.clear();
                            page = 1;
                            map.put("selectedPartId",listcolor.get(position).getColorId());
                            getData();
                        }
                        break;
                }
            }
        });

    }

    /**
     * 设置Fragmen是否可见
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){
            popuwindow.dismiss();
        }
    }

    //实例化参数
    public void init() {
        server = new QueryGd();
        map = new HashMap<>();
        list = new ArrayList<ProductBean>();
        adater = new ProductAdater(getActivity(), list);
        product_gridview.setAdapter(adater);

//        动态设置宽度
        LinearLayout.LayoutParams params_ = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params_.height = SystemUtil.getWindowHeight(getActivity()) / 5;
        product_lin.setLayoutParams(params_);


        product_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SerializeMethod(list.get(position));
                Log.e("***", "onItemClick: " +list.get(position) );
            }
        });
    }
    /**
     * Serializeable传递对象的方法
     */
    private void SerializeMethod(ProductBean mProduct){
        Intent mIntent = new Intent(getActivity(),WebTbActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable("duixiang",mProduct);
        mIntent.putExtras(mBundle);
        startActivity(mIntent);
    }

    private void initView(View view) {
//        实例化对象

        product_default_sort = (RadioButton) view.findViewById(R.id.product_default_sort);
        product_price_sort = (RadioButton) view.findViewById(R.id.product_price_sort);
        product_sales_sort = (RadioButton) view.findViewById(R.id.product_sales_sort);

        product_sex_select = (RadioButton) view.findViewById(R.id.product_sex_select);
        product_part_select = (RadioButton) view.findViewById(R.id.product_part_select);
        product_color_select = (RadioButton) view.findViewById(R.id.product_color_select);

        product_pullToRefresh = (PullToRefreshView) view.findViewById(R.id.product_pullToRefresh);
        product_gridview = (GridView) view.findViewById(R.id.product_gridview);
        product_lin = (LinearLayout) view.findViewById(R.id.product_lin);
//设置点击事件
        view.findViewById(R.id.product_sex_select).setOnClickListener(this);
        view.findViewById(R.id.product_part_select).setOnClickListener(this);
        view.findViewById(R.id.product_color_select).setOnClickListener(this);
        product_default_sort.setOnClickListener(this);
        product_price_sort.setOnClickListener(this);
        product_sales_sort.setOnClickListener(this);

        // 上拉，下拉，初始化时，要动态设置填充宽高
        LinearLayout.LayoutParams para = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        product_pullToRefresh.setLayoutParams(para);
//设置不能往上拉
        product_pullToRefresh.setIsUpLoad(false);
    /*	String time = "当前时间";
        //设置往下拉时间
        product_pullToRefresh.setLastUpdated(time);

        product_pullToRefresh.setOnHeaderRefreshListener(new PullToRefreshView.OnHeaderRefreshListener() {
            @Override
            public void onHeaderRefresh(PullToRefreshView view) {
                //下拉完成
                product_pullToRefresh.onHeaderRefreshComplete();
            }
        });*/
//        往上拉加载
        product_pullToRefresh.setOnFooterRefreshListener(new PullToRefreshView.OnFooterRefreshListener() {
            @Override
            public void onFooterRefresh(PullToRefreshView view) {
                //调用下拉加载完成的方法
                product_pullToRefresh.onFooterRefreshComplete();
                page++;
                getData();
            }
        });

    }

    /**
     * 请求数据
     */
    private void getData() {
        server.queryProduct(page, pageSize, map, new CallBack() {
            @Override
            public void onResponse(String response) {
//                将数据进行解析
                List<ProductBean> list1 = new Gson().fromJson(response,
                        new TypeToken<List<ProductBean>>() {
                        }.getType());
//判断返回的长度
                if (list1.size() > 0) {
                    list.addAll(list1);
                    adater.setList(list);
                } else {
                    new CostomViewUtil().showToastShort(getActivity(),"没有更多数据了");
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.product_default_sort:// 默认排序
                list.clear();//清空数据
                page = 1;
                map.clear();
                product_price_sort.setText(R.string.product_jiage);
                product_sales_sort.setText(R.string.product_xiaoliang);

                product_sex_select.setChecked(false);
                product_part_select.setChecked(false);
                product_color_select.setChecked(false);
                getData();
                break;

            case R.id.product_price_sort:// 价格排序
                list.clear();//清空上次数据
                product_sales_sort.setText(R.string.product_xiaoliang);
                page = 1;
                //接口内容
                map.put("orderby", "productPrice2");
//                  降序
//                map.containsKey("desc"):读取key为desc的在集合里面的valse值
                if (!map.containsKey("desc")) {
                    map.put("desc","true");
                    product_price_sort.setText("价格降序");
                }
//                升序
                if (map.get("desc").equals("true")){
                    map.put("desc","false");
                    product_price_sort.setText("价格升序");
                }//
                else{
                    map.put("desc","true");
                    product_price_sort.setText("价格降序");
                }
//                请求数据。。
                getData();
                break;

            case R.id.product_sales_sort:// 销量排序
                product_price_sort.setText(R.string.product_jiage);
                list.clear();//清空上次数据
                page = 1;
                //接口内容
                map.put("orderby", "productAmount");
//                  降序
//                map.containsKey("desc"):读取key为desc的在集合里面的valse值
                if (!map.containsKey("desc")) {
                    map.put("desc","true");
                    product_sales_sort.setText("销量降序");
                }
//                升序
                if (map.get("desc").equals("true")){
                    map.put("desc","false");
                    product_sales_sort.setText("销量升序");
                }//
                else{
                    map.put("desc","true");
                    product_sales_sort.setText("销量降序");
                }
//                请求数据。。
                getData();
                break;

            case R.id.product_sex_select:// 性别选择
                CLICK_BIN = 1;
                popuwindow.showAsDropDown(v);
                product_sex_select.setChecked(true);
//                如果为空就请求数据
                if (listSex == null){
                    querySex();
                }else {
//                    不为空就加载数据
                    sexsele.setList(listSex);
                    popup_gridview.setAdapter(sexsele);
                }
                break;



            case R.id.product_part_select:// 部位选择
                CLICK_BIN = 2;
                product_part_select.setChecked(true);
                popuwindow.showAsDropDown(v);
                //                如果为空就请求数据
                if (listpart == null){
                    queryParts();
                }else{
                    //                    不为空就加载数据
                 part.setList(listpart);
                    popup_gridview.setAdapter(part);
                }
                break;

            case R.id.product_color_select:// 颜色
                CLICK_BIN = 3;
                product_color_select.setChecked(true);
                popuwindow.showAsDropDown(v);
                if (listcolor == null){
                    queryColor();
                }else{
                    //                    不为空就加载数据
                   part.setList(listpart);
                    popup_gridview.setAdapter(color);
                }
                break;
        }

    }

    /**
     * 颜色转换
     */
    private void queryColor() {
        server.queryColor(new CallBack() {
            @Override
            public void onResponse(String response) {
//                从js数据里面将相应的数据数据分离出来
                listcolor = new Gson().fromJson(response,
                        new TypeToken<List<ColorBean>>(){}.getType());
                if (color == null){
                    ColorBean cancel = new ColorBean();
//                  id随意，防止数据读取
                    cancel.setColorId(-1);
                    cancel.setColorName("取消");
                    listcolor.add(cancel);
                    color = new ColorSelectAdapter(getActivity(),listcolor);
                    popup_gridview.setAdapter(color);
                }else{
                    color.setList(listcolor);
                }
            }
        });

    }

    /**
     * 查询性别分类
     */
    private void querySex() {
        server.querySex(new CallBack() {
            @Override
            public void onResponse(String response) {
//                从js数据里面将相应的数据数据分离出来
                listSex = new Gson().fromJson(response,
        new TypeToken<List<GendarBean>>(){}.getType());
                if (sexsele == null){
                    GendarBean cancel = new GendarBean();
//                  id随意，防止数据读取
                    cancel.setGendarId(-1);
                    cancel.setGendarName("取消");
                    listSex.add(cancel);
                    sexsele = new SexSelectAdapter(getActivity(),listSex);
                    popup_gridview.setAdapter(sexsele);
                }else{
                    sexsele.setList(listSex);
                }
            }
        });
    }

    /**
     * 查询性别分类
     */
    private void queryParts() {
        server.queryParts(new CallBack() {
            @Override
            public void onResponse(String response) {
//                从js数据里面将相应的数据数据分离出来
                listpart = new Gson().fromJson(response,
                        new TypeToken<List<PartBean>>(){}.getType());
                if (part == null){
                    PartBean cancel = new PartBean();
//                  id随意，防止数据读取
                    cancel.setPartId(-1);
                    cancel.setPartName("取消");
                    listpart.add(cancel);
                    part = new PartSelectAdapter(getActivity(),listpart);
                    popup_gridview.setAdapter(part);
                }else{
                    part.setList(listpart);
                }
            }
        });
    }
}
