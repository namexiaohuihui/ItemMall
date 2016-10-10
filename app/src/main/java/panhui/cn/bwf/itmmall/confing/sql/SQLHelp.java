package panhui.cn.bwf.itmmall.confing.sql;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/6/16.
 */
public class SQLHelp extends SQLiteOpenHelper {


    private String cartTable = "create table if not exists card(id int auto_increment," +
            "productId int,productImage varchar(20),productName varchar(20)," +
            "productPrice1 float,producPrice2 float,count int,primary key('id'))";

    public SQLHelp(Context context,int VERSION_CODE) {
//        第二个参数是表名、第四个参数是版本号
        super(context, "card.db", null, VERSION_CODE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        创建数据库
        db.execSQL(cartTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//          更新数据库
        onCreate(db);
    }
}
