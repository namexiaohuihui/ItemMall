package panhui.cn.bwf.home.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import panhui.cn.bwf.home.bean.AdvertBaen;
import panhui.cn.bwf.itmmall.confing.MyConfig;

/**
 * Created by Administrator on 2016/6/14.
 */
public class ViewPagerAdapter extends PagerAdapter{

    private Context context;
    private List<AdvertBaen> list;
    public ViewPagerAdapter(Context context, List<AdvertBaen> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    /**是否使用对象生成页面*/
    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView((View) object);
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        container.addView(imageView);
        ImageLoader.getInstance().displayImage(MyConfig.imageurl+list.get(position).getAdImage(), imageView);
        return imageView;
    }
}
