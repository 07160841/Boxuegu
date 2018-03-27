package cn.edu.gdmec.android.boxuegu.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import cn.edu.gdmec.android.boxuegu.bean.UserBean;
import cn.edu.gdmec.android.boxuegu.sqlite.SQLiteHelper;

import static android.content.ContentValues.TAG;

/**
 * Created by lenovo on 2018/3/22.
 */

public class DBUtils {

    private static DBUtils instance = null;
    private static SQLiteHelper helper;
    private static SQLiteDatabase db;

    public DBUtils(Context context) {
        helper = new SQLiteHelper(context);
        db = helper.getWritableDatabase();
    }
//静态方法是使用公共内存空间的，就是说所有对象都可以直接引用
    public static DBUtils getInstance(Context context) {
        if (instance == null) {
            instance = new DBUtils(context);
        }
        return instance;
    }

    //保存个人资料
    public void saveUserInfo(UserBean bean) {
        //ContentValues 和HashTable类似都是一种存储的机制，但是两者最大的区别就在于，contenvalues只能存储基本类型的数据，像string，int之类的，不能存储对象这种东西，
        // 而HashTable却可以存储对象。
        ContentValues cv = new ContentValues();
        cv.put("userName", bean.userName);
        cv.put("nickName", bean.nickName);
        cv.put("sex", bean.sex);
        cv.put("signature", bean.signature);
        db.insert(SQLiteHelper.U_USERINFO, null, cv);
    }

    //获取个人资料
    public UserBean getUserInfo(String userName) {
        String sql = "SELECT * FROM " + SQLiteHelper.U_USERINFO + " WHERE userName=? ";
       // Log.i(TAG, "getUserInfo: "+sql);
        Cursor cursor = db.rawQuery(sql, new String[]{userName});
        UserBean bean = null;
        while (cursor.moveToNext()) {
            bean = new UserBean();
            bean.userName = cursor.getString(cursor.getColumnIndex("userName"));
            bean.nickName = cursor.getString(cursor.getColumnIndex("nickName"));
            bean.sex = cursor.getString(cursor.getColumnIndex("sex"));
            bean.signature = cursor.getString(cursor.getColumnIndex("signature"));
        }
        cursor.close();
        return bean;
    }

    //修改个人资料
    public void updataUserInfo(String key,String value,String userName){
        ContentValues vs=new ContentValues();
        vs.put(key,value);
        db.update(SQLiteHelper.U_USERINFO,vs,"userName=?",new String[]{userName});
    }
}