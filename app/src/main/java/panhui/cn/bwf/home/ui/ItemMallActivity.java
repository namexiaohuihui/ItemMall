package panhui.cn.bwf.home.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;

import panhui.cn.bwf.home.ui.frament.CartFragment;
import panhui.cn.bwf.home.ui.frament.HomeFragment;
import panhui.cn.bwf.home.ui.frament.UsercenterFragment;
import panhui.cn.bwf.home.ui.frament.productFragment;
import panhui.cn.bwf.itemmall.R;

public class ItemMallActivity extends FragmentActivity implements View.OnClickListener {

    //	 private List<Fragment> fragments;
    // 记录当前显示fragment的下标
    private int currentIndex = -1;
    // 第二种方式
    private HomeFragment home;
    private CartFragment cart;
    private productFragment product;
    private UsercenterFragment usercenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_item_mall);

        findViewById(R.id.title1).setOnClickListener(this);
        findViewById(R.id.title2).setOnClickListener(this);
        findViewById(R.id.title3).setOnClickListener(this);
        findViewById(R.id.title4).setOnClickListener(this);

        show(0);
    }

    private void show(int index) {
        if (currentIndex == index) {
            return;
        }
        FragmentTransaction mTrans = getSupportFragmentManager().beginTransaction();
        switch (index) {
            case 0:
                if (home == null) {
                    home = new HomeFragment();
                    mTrans.add(R.id.home_framelayout, home);
                }
                mTrans.show(home);
                break;

            case 1:
                if (product == null) {
                    product = new productFragment();
                    mTrans.add(R.id.home_framelayout, product);
                }
                mTrans.show(product);
                break;

            case 2:
                if (cart == null) {
                    cart = new CartFragment();
                    mTrans.add(R.id.home_framelayout, cart);
                }
                mTrans.show(cart);
                break;

            case 3:
                if (usercenter == null) {
                    usercenter = new UsercenterFragment();
                    mTrans.add(R.id.home_framelayout, usercenter);
                }
                mTrans.show(usercenter);
                break;
        }

        switch (currentIndex) {
            case 0:
                mTrans.hide(home);
                break;
            case 1:
                mTrans.hide(product);
                break;
            case 2:
                mTrans.hide(cart);
                break;
            case 3:
                mTrans.hide(usercenter);
                break;
        }

        mTrans.commit();
        currentIndex = index;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title1:
                show(0);
                break;
            case R.id.title2:
                show(1);
                break;
            case R.id.title3:
                show(2);
                break;
            case R.id.title4:
                show(3);
                break;
        }
    }
}
