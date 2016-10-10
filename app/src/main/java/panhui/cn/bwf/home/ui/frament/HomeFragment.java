package panhui.cn.bwf.home.ui.frament;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.List;

import framework.xutil.LLog;
import panhui.cn.bwf.home.adapter.MenuAdapter;
import panhui.cn.bwf.home.bean.MenuBean;
import panhui.cn.bwf.home.server.MenuData;
import panhui.cn.bwf.itemmall.R;

/*这是首页的Frament的作用类
 * Created by Administrator on 2016/6/13.
 */
public class HomeFragment extends Fragment  {

    private ListView item_listview;
    private List<MenuBean> list;
    private MenuAdapter menuAdapter;
    private int curr = -1;
    //推荐
    private Home_Recommended recomm;
    //男装，女装，上衣，裤子，帽子，裙子，连衣裙，鞋子，帽子
private Home_Product mensClothing ,womensClothing,coat,trousers,skirt,dress,shoes,hat ;
    private Home_Product  home[] = {mensClothing,womensClothing,coat,trousers,skirt,dress,shoes,hat};
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_home, container, false);
        item_listview = (ListView) view.findViewById(R.id.item_listview);
//  将数据类里面的数据给集合
        list = new MenuData().getDate();
        list.get(0).setState(1);
        menuAdapter = new MenuAdapter(getActivity(), list);
        item_listview.setAdapter(menuAdapter);
           show(0);
        item_listview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setState(0);
                }
                LLog.e("position:" + position);
                list.get(position).setState(1);
                menuAdapter.setList(list);
                show(position);
            }
        });
        return view;
    }

    private void show(int i) {
        if (curr == i) {
            return;
        }
        FragmentTransaction mTrans = getActivity().getSupportFragmentManager().beginTransaction();

        switch (i) {
            case 0:
                if (recomm == null) {
                    recomm = new Home_Recommended();
                    mTrans.add(R.id.item_framelayout, recomm);
                }
                mTrans.show(recomm);
                break;
            case 1:
                if (mensClothing == null) {
                    mensClothing = new Home_Product("selectedGendarId",1);
                    mTrans.add(R.id.item_framelayout, mensClothing);
                }
                mTrans.show(mensClothing);
                break;
            case 2:
                if (womensClothing == null) {
                    womensClothing = new Home_Product("selectedGendarId",2);
                    mTrans.add(R.id.item_framelayout, womensClothing);
                }
                mTrans.show(womensClothing);
                break;

            case 3:
                if (coat == null) {
                    coat = new Home_Product("selectedPartId",1);
                    mTrans.add(R.id.item_framelayout, coat);
                }
                mTrans.show(coat);
                break;
            case 4:
                if (trousers == null) {
                    trousers = new Home_Product("selectedPartId",2);
                    mTrans.add(R.id.item_framelayout, trousers);
                }
                mTrans.show(trousers);
                break;

            case 5:
                if (skirt == null) {
                    skirt = new Home_Product("selectedPartId",3);
                    mTrans.add(R.id.item_framelayout, skirt);
                }
                mTrans.show(skirt);
                break;
            case 6:
                if (dress == null) {
                    dress = new Home_Product("selectedPartId",4);
                    mTrans.add(R.id.item_framelayout, dress);
                }
                mTrans.show(dress);
                break;
            case 7:
                if (shoes == null) {
                    shoes = new Home_Product("selectedPartId",5);
                    mTrans.add(R.id.item_framelayout, shoes);
                }
                mTrans.show(shoes);
                break;
            case 8:
                if (hat == null) {
                    hat = new Home_Product("selectedPartId",6);
                    mTrans.add(R.id.item_framelayout, hat);
                }
                mTrans.show(hat);
                break;
        }
        switch (curr) {
            case 0:
                mTrans.hide(recomm);
                break;
            case 1:
                mTrans.hide(mensClothing);
                break;
            case 2:
                mTrans.hide(womensClothing);
                break;
            case 3:
                mTrans.hide(coat);
                break;
            case 4:
                mTrans.hide(trousers);
                break;
            case 5:
                mTrans.hide(skirt);
                break;
            case 6:
                mTrans.hide(dress);
                break;
            case 7:
                mTrans.hide(shoes);
                break;
            case 8:
                mTrans.hide(hat);
                break;
        }
        mTrans.commit();
        curr = i;
    }
}
