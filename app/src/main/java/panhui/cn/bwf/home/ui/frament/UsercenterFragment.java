package panhui.cn.bwf.home.ui.frament;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import panhui.cn.bwf.home.adapter.DialogAdapter;
import panhui.cn.bwf.home.adapter.UsercenterAdapter;
import panhui.cn.bwf.home.server.DialogData;
import panhui.cn.bwf.home.server.UserData;
import panhui.cn.bwf.home.ui.ReceivingActivity;
import panhui.cn.bwf.home.ui.UserActivity;
import panhui.cn.bwf.itemmall.R;

/**
 * 列表的写法：
 * 1. 定义布局对象，准备列表对象
 * 2.  准备数据源
 * 3. 准备适配器
 * 4. 装载适配器
 * Created by Administrator on 2016/6/13.
 */
public class UsercenterFragment extends Fragment {

    //数据源
    List<Map<String, Object>> list;
    //    适配器对象
    UsercenterAdapter usercenterAdapter;
    //    列表对象
    private ListView user_num;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.usercenter, container, false);
        user_num = (ListView) view.findViewById(R.id.user_num);

        list = new UserData().getDate();
        usercenterAdapter = new UsercenterAdapter(getActivity(), list);
        user_num.setAdapter(usercenterAdapter);
//列表的按钮
        user_num.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        //              如果点击的是信息管理就弹出对话框让其选择修改密码还是昵称
                        getInformation(position);
                        break;
                    case 1:
                        getconsignee();
                        break;
                }
            }
        });
//登陆和注册的按钮
        view.findViewById(R.id.user_xxxxxx).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
//                    对话框创建，把自定义控件加入
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    View view_dialog = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_layout, null);
                    builder.setView(view_dialog);
//                    对话框的列表
                    ListView dialog_lv = (ListView) view_dialog.findViewById(R.id.dialog_lv);
//                    对话框标题
                    ((TextView) view_dialog.findViewById(R.id.dialog_name)).setText("选择");
//                    数据源
                    List<Map<String, Object>> list = new DialogData().getDialog(4);
//                    适配器
                   DialogAdapter adapter = new DialogAdapter(getActivity(), list);
//                    设置适配器
                    dialog_lv.setAdapter(adapter);
//                    显示对话框
                    final AlertDialog dialog = builder.show();

                dialog_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                   关闭对话框
                        dialog.dismiss();
//                  判断选择是注册还是登陆
                        if (position == 0){
//                        意图跳转
                            Intent intent = new Intent(getActivity(),UserActivity.class);
                            intent.setAction("zhuce");
                            startActivity(intent);
                        }else{
//                        意图跳转
                            Intent intent = new Intent(getActivity(),UserActivity.class);
                            intent.setAction("denglu");
                            startActivity(intent);
                        }
                    }
                });
            }
        });
        return view;
    }

//              如果点击的是信息管理就弹出对话框让其选择修改密码还是昵称
    private void getInformation(int position) {
        //                    如果点击的是信息管理就弹出对话框让其选择修改密码还是昵称
//                    对话框创建，把自定义控件加入
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            View view_dialog = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_layout, null);
            builder.setView(view_dialog);
//                    对话框的列表
            ListView dialog_lv = (ListView) view_dialog.findViewById(R.id.dialog_lv);
//                    对话框标题
            ((TextView) view_dialog.findViewById(R.id.dialog_name)).setText("信息选择");
//                    数据源
            List<Map<String, Object>> list = new DialogData().getDialog(2);
//                    适配器
            DialogAdapter adapter = new DialogAdapter(getActivity(), list);
//                    设置适配器
            dialog_lv.setAdapter(adapter);
//                    显示对话框
            final AlertDialog dialog = builder.show();

            dialog_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                   关闭对话框
                    dialog.dismiss();
//                  判断选择是昵称还是密码
                    if (position == 0){
//                        意图跳转
                        Intent intent = new Intent(getActivity(),UserActivity.class);
                        intent.setAction("nicheng");
                        startActivity(intent);
                    }else{
//                        意图跳转
                        Intent intent = new Intent(getActivity(),UserActivity.class);
                        intent.setAction("mima");
                        startActivity(intent);
                    }
                }
            });
    }
//        收货人信息
    private void getconsignee() {
        //                        意图跳转
        Intent intent = new Intent(getActivity(),ReceivingActivity.class);
        intent.setAction("fff");
        startActivity(intent);
    }
}
