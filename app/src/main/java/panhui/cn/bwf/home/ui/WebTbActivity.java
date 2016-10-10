package panhui.cn.bwf.home.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import panhui.cn.bwf.home.bean.ProductBean;
import panhui.cn.bwf.itemmall.R;
import panhui.cn.bwf.itmmall.confing.MyConfig;
import panhui.cn.bwf.itmmall.confing.sql.SQLHolder;

public class WebTbActivity extends Activity implements OnClickListener {

    private ImageView web_tb_iamge;

    private TextView web_tb_bh, web_tb_bt, web_tb_xj, web_tb_yj, web_tb_kc;

    private Button web_tb_gm, web_tb_che;

    ProductBean mProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_web_tb);

        init();

        mProduct = (ProductBean) getIntent().getSerializableExtra("duixiang");

        Log.e("mProduct", mProduct.toString());

        initto();
    }

    private void initto() {

        //传入网址和图片就可以显示图片了
        ImageLoader.getInstance().displayImage(MyConfig.imageurl + mProduct.getProductImage(),
                web_tb_iamge);

        web_tb_bh.setText(mProduct.getProductId() + "");

        web_tb_bt.setText(mProduct.getProductName());

        web_tb_xj.setText(mProduct.getProductPrice2() + "");

        web_tb_yj.setText(mProduct.getProductPrice1() + "");

        web_tb_kc.setText(mProduct.getProductAmount() + "");
    }

    private void init() {

        web_tb_iamge = (ImageView) findViewById(R.id.web_tb_iamge);

        web_tb_bh = (TextView) findViewById(R.id.web_tb_bh);

        web_tb_bt = (TextView) findViewById(R.id.web_tb_bt);

        web_tb_xj = (TextView) findViewById(R.id.web_tb_xj);

        web_tb_yj = (TextView) findViewById(R.id.web_tb_yj);

        web_tb_kc = (TextView) findViewById(R.id.web_tb_kc);

        web_tb_gm = (Button) findViewById(R.id.web_tb_gm);

        web_tb_che = (Button) findViewById(R.id.web_tb_che);

        web_tb_gm.setOnClickListener(this);
        web_tb_che.setOnClickListener(this);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            点击购买按钮
            case R.id.web_tb_gm:


                break;
            case R.id.web_tb_che:
//              点击购物车按钮
//              mProduct该对象是分类点击时传送过来的对象,
//              将该对象传入购物车里面展示出来
               new SQLHolder().addCard(WebTbActivity.this,mProduct);

                break;
        }
    }
}
