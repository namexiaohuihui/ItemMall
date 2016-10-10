package panhui.cn.bwf.home.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import panhui.cn.bwf.home.bean.UnameAndUpwdBean;
import panhui.cn.bwf.home.server.QueryGd;
import panhui.cn.bwf.itemmall.R;
import panhui.cn.bwf.itmmall.confing.base.CallBack;
import panhui.cn.bwf.itmmall.confing.xutil.CostomViewUtil;

// WelComeActivity.this.finish();
public class UserActivity extends Activity {
    private EditText user_pass,user_name,user_pass2;
    private TextView user_text1,user_text2,user_text3,user_title,lin4;
    private TextView xinmima1,xinmima2,xinmima3;
    private LinearLayout lin2,lin3;
    private QueryGd sevler;
    private Gson gson;
    private List<UnameAndUpwdBean> list_name_pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_user_activity);
//获取意图对象
        Intent intent = getIntent();
        String string = intent.getAction();
        sevler = new QueryGd();
        gson = new Gson();
//        实例化对象
        initvisi();
        getUi(string);
    }

    private void initvisi() {
        user_pass2 = (EditText) findViewById(R.id.user_pass2);
        user_pass = (EditText) findViewById(R.id.user_pass);
        user_name = (EditText) findViewById(R.id.user_name);
        user_text1 = (TextView) findViewById(R.id.user_text1);
        user_text2 = (TextView) findViewById(R.id.user_text2);
        user_text3 = (TextView) findViewById(R.id.user_text3);
        user_title = (TextView) findViewById(R.id.user_title);

        xinmima1 = (TextView) findViewById(R.id.xinmima1);
        xinmima2 = (TextView) findViewById(R.id.xinmima2);
        xinmima3 = (TextView) findViewById(R.id.xinmima3);

        lin2 = (LinearLayout) findViewById(R.id.lin2);
        lin3 = (LinearLayout) findViewById(R.id.lin3);
        lin4 = (TextView) findViewById(R.id.lin4);
    }


    private void getUi(String string){
//      如果是修改昵称
        if (string.equals("nicheng") && string.length() >0 &&string!=null){
//            隐藏输入框
            lin2.setVisibility(View.GONE);
            lin3.setVisibility(View.GONE);
            user_text2.setVisibility(View.GONE);
//            标题
            user_title.setText("修改昵称");
            user_text1.setText("昵");
            user_text3.setText("称");
            user_name.setSelection(user_name.length());
            lin4.setText("提交");
            lin4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserActivity.this.finish();
                }
            });
        }
//        如果是修改密码
        if (string.equals("mima") && string.length() >0 &&string!=null){
//            显示输入框
            lin2.setVisibility(View.VISIBLE);
            lin3.setVisibility(View.GONE);
            user_text2.setVisibility(View.VISIBLE);
//            标题
            user_title.setText("修改密码");
            user_text1.setText("原");
            user_text3.setText("码");
            user_pass.setSelection(user_pass.length());
            user_name.setSelection(user_name.length());
            lin4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserActivity.this.finish();
                }
            });
        }
        //        如果是注册
        if (string.equals("zhuce") && string.length() >0 &&string!=null){
            zhuce(string);
            lin4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nickName = user_name.getText().toString();
                    String uname = user_pass.getText().toString();
                    String upwd = user_pass2.getText().toString();
                    final UnameAndUpwdBean bean = new UnameAndUpwdBean();
                    bean.setNickName(nickName);
                    bean.setUname(uname);
                    bean.setUpwd(upwd);
                    sevler.queryAddUser(bean, new CallBack() {
                        @Override
                        public void onResponse(String response) {
                            new CostomViewUtil().showToast(getApplicationContext(),"ID号"+ response);
                            if (response!=null&&response.length()>0){
                                int userId = Integer.parseInt(response);
                                bean.setUserId(userId);
                            }
//                            当注册成功之后就关闭activity
                            UserActivity.this.finish();
                        }
                    });
                }
            });
        }
        //        如果登陆
        if (string.equals("denglu") && string.length() >0 &&string!=null){
            denglu(string);
            lin4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String uname = user_name.getText().toString();
                    String upwd = user_pass2.getText().toString();
                    UnameAndUpwdBean bean = new UnameAndUpwdBean();
                    bean.setUname(uname);
                    bean.setUpwd(upwd);
                    Log.e("1.", " 11"+uname);
                    Log.e("2.", " 22"+upwd);
//                    上传信息给服务器
                    sevler.queryUnameAndUpwd(bean, new CallBack() {
                        @Override
                        public void onResponse(String response) {
//                            从服务器读取数据
                            list_name_pwd = gson.fromJson(response,
                                    new TypeToken<List<UnameAndUpwdBean>>(){}.getType());
                         /*   Log.e("账户id", "onResponse: " + list_name_pwd.get(0).getUserId() + "");
                            Log.e("账户昵称", "onResponse: " + list_name_pwd.get(0).getUname());
                            Log.e("账户yonghuming", "onResponse: " + list_name_pwd.get(0).getNickName());
                            Log.e("账户mima", "onResponse: " + list_name_pwd.get(0).getUpwd());*/
                            if (response!=null){
                                Log.e("昵称", "onResponse: " + response.toString() );
                            }else{
                                Log.e("id", "onResponse: "  + "kongyo");
                            }
                        }
                    });
                }
            });
        }
    }

    private void zhuce(String string){
        user_title.setText("注册账号");
        user_text2.setVisibility(View.GONE);
        lin3.setVisibility(View.VISIBLE);
        user_name.setSelection(user_name.length());
        user_pass.setSelection(user_pass.length());
        user_pass2.setSelection(user_pass.length());
        xinmima1.setText("用");
        xinmima2.setText("户");
        xinmima3.setText("名");
        user_text1.setText("昵");
        user_text3.setText("称");
        lin4.setText("注册");

    }

    private void denglu(String string){
        user_title.setText("登陆账号");
        lin3.setVisibility(View.VISIBLE);
        lin2.setVisibility(View.GONE);
        user_text1.setText("用");
        user_text2.setText("户");
        user_text3.setText("名");
        user_pass.setSelection(user_pass.length());
        user_pass2.setSelection(user_pass2.length());
        user_name.setSelection(user_name.length());
        lin4.setText("登陆");
    }
}
