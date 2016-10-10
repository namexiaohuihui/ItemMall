package panhui.cn.bwf.itmmall.confing.xutil;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import panhui.cn.bwf.itemmall.R;

/**创建对象时可以可以使用非静态的方法通过时间和位置来控制
 * 不创建对象时使用静态的方法直接调用
 * Created by Administrator on 2016/6/16.
 */
public class CostomViewUtil {

    //    上一次吐司时间
    private  String lastToast = "";
    //    上一次吐司结束时间
    private  long loatToastTime;

    private  Context context;
    private  String message;
    private  int icon_static= R.mipmap.xiaolian;


    /**
     * 传入上下文和信息，选择长时间显示
     * @param context
     * @param message
     */
    public  void showToast(Context context,String message){
        showToast(context,message,5,icon_static,Gravity.CENTER);
    }

    /**
     * 传入上下文和信息，选择短时间显示
     * @param context
     * @param message
     */
    public  void showToastShort(Context context,String message){
        showToast(context,message,50,icon_static,Gravity.TOP);
    }


     /*自定义和包装Toast
     * @param context  上下文
     * @param message  信息
     * @param duration  时间
     * @param icon  图片
     * @param gravity  位置
     */
    public  void showToast(Context context, String message,
                                 int duration, int icon, int gravity) {

//        判断时间是否为空,equalsIgnoreCase区分大小写
        if (message != null && !message.equalsIgnoreCase("")) {
//            记录当前时间
            long time = System.currentTimeMillis();

//            判断上一次吐司时间离这一次时间是否大于2秒
//            并且信息不能重复
//             Math.abs判断两个数的绝对值
            if (!message.equalsIgnoreCase(lastToast) ||
                    Math.abs(time - loatToastTime) > 2000) {

                View view = LayoutInflater.from(context).inflate(R.layout.view_toast, null);
//                  设置字体信息
                ((TextView) view.findViewById(R.id.tosat_icon_tv)).setText(message);
//                  判断是否需要显示图片
                if (icon != 0) {
                    ((ImageView) view.findViewById(R.id.tosat_icon_ib)).setImageResource(icon);
                    ((ImageView) view.findViewById(R.id.tosat_icon_ib)).setVisibility(View.VISIBLE);
                }
//                将布局放入吐司里面
                Toast toast = new Toast(context);
                toast.setView(view);

//                控制吐司的位置
//                 toast.setGravity(gravity,i,j);
                //                    第一参数为位置
//                i 表示与父布局的横向距离
//                j 表示与父布局的纵向距离
//                 Gravity.CENTER居中
                if (gravity == Gravity.CENTER){
                    toast.setGravity(gravity,0,0);
                }else{
                    toast.setGravity(gravity,0,35);
                }
//                吐司持续时间
                toast.setDuration(duration);

//                将吐司展现出来
                toast.show();
//                将本次时间和信息存为上次
                lastToast = message;
//                展示完成之后的时间
                loatToastTime = System.currentTimeMillis();
            }
        }
    }
}
