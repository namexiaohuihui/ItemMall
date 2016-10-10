package panhui.cn.bwf.itmmall.confing.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import panhui.cn.bwf.home.bean.ProductBean;
import panhui.cn.bwf.itmmall.confing.xutil.CostomViewUtil;
import panhui.cn.bwf.itmmall.confing.xutil.getVersion;

/**
 * Created by Administrator on 2016/6/16.
 */
public class SQLHolder {

    //吐司类对象
    private CostomViewUtil tusi = new CostomViewUtil();

    public SQLHolder(Context context) {
        tusi.showToastShort(context, "购物车点击了...");
    }

    public SQLHolder() {

    }
//    获取app版本号

    //        传入上下文和版本号
    public void addCard(Context context, ProductBean bean) {
        //获取数据库基类对象
        SQLHelp help = new SQLHelp(context, new getVersion().getVersionCode(context));
//        获取数据库写的权限
        SQLiteDatabase sDatabase = help.getWritableDatabase();
//        读取数据库，创建游标
        Cursor cursor = sDatabase.query("card", new String[]{"productId", "count"}, null
                , null, null, null, null);
//        判断是否存在准备添加的productId
        boolean isExist = false;
        //添加到购物车的数量
        int count = 0;
        try {
            //如果有数据,游标往下走
            while (cursor.moveToNext()) {
//                cursor.getInt(0) : 查询条件是int，查的值是productId
//                cursor.getInt(1) : 查询条件是int，查的值是count
//                根据一个数id是否存在来读取是否被选入购物车
                if (bean.getProductId() == cursor.getInt(0)) {
                    isExist = true;
                    count = cursor.getInt(1);
                    count++;
                }
            }
        } catch (Exception e) {

        }
//        如果数据存在就更新数据
        if (isExist) {
            //更新数据库
            upDataCard(context, bean.getProductId(), count);
        }
//        不存在就添加数据
        else {
//            添加数据库的动作
            ContentValues values = new ContentValues();
            //添加列的参数
            values.put("productId", bean.getProductId());
            values.put("productImage", bean.getProductImage());
            values.put("productName", bean.getProductName());
            values.put("productPrice1", bean.getProductPrice1());
            values.put("producPrice2", bean.getProductPrice2());
            values.put("count", bean.getCount());
//            通过权限插入数据库
            long add = sDatabase.insert("card", null, values);
//            数据假如成功后返回行号，失败返回-1
            if (add != -1) {
                tusi.showToast(context, "成功添加购物车");
            } else {
                tusi.showToast(context, "添加购物车失败");
            }
        }
//        一定要关闭数据库权限和游标
        cursor.close();
        sDatabase.close();
    }

    /**
     * 更新数据库里面的数据
     *
     * @param context
     * @param productId
     * @param count
     */
    public void upDataCard(Context context, int productId, int count) {
//        基类对象
        SQLHelp help = new SQLHelp(context, new getVersion().getVersionCode(context));
//        获取权限
        SQLiteDatabase sDatabase = help.getWritableDatabase();
//        添加数据库的动作
        ContentValues values = new ContentValues();
//        添加列的参数
        values.put("count", count);
//         更新数据库
        long updata = sDatabase.update("card", values, "productId=" + productId, null);
        if (updata != 0) {
            tusi.showToast(context, "数据更新了");
        } else {
            tusi.showToast(context, "数据失败了");
        }
//        关闭权限
        sDatabase.close();
    }

    /**
     * 删除数据库
     *
     * @param context
     * @param productId
     */
    public void deleteCard(Context context, int productId) {
        SQLHelp help = new SQLHelp(context, new getVersion().getVersionCode(context));
//        获取数据写的权限
        SQLiteDatabase sDatabase = help.getWritableDatabase();
//        删除失败返回0，成功则返回删除的条数
        long delete = sDatabase.delete("card", "productId=" + productId, null);
        if (delete != 0) {
            tusi.showToast(context, "删除物品成功");
        } else {
            tusi.showToast(context, "删除物品失败");
        }
        sDatabase.close();
    }


    /**
     * 查询数据库
     *
     * @param context
     * @return 查询的结果
     */
    public List<ProductBean> getCard(Context context) {
//          基类
        SQLHelp help = new SQLHelp(context, new getVersion().getVersionCode(context));
//          权限
        SQLiteDatabase sDatabase = help.getWritableDatabase();
        List<ProductBean> list = new ArrayList<>();

        Cursor cursor = sDatabase.query("card", new String[]{"productId", "productImage",
                "productName", "productPrice1", "producPrice2", "count"}, null, null, null, null, null);

        try {
            while (cursor.moveToNext()) {
                ProductBean product = new ProductBean();
                product.setProductId(cursor.getInt(0));
                product.setProductImage(cursor.getString(1));
                product.setProductName(cursor.getString(2));
                product.setProductPrice1(cursor.getFloat(3));
                product.setProductPrice2(cursor.getFloat(4));
                product.setCount(cursor.getInt(5));
                list.add(product);
            }
        } catch (Exception e) {
        }
        cursor.close();
        sDatabase.close();
        return list;
    }

}
