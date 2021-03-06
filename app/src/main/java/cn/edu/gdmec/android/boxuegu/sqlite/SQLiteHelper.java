package cn.edu.gdmec.android.boxuegu.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lenovo on 2018/3/22.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION=2;
    public static String DB_NAME="bxg.db";
    public static final String U_USERINFO="userinfo";
    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + U_USERINFO +"("+"_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                +"userName VARCHAR, "
                +"nickName VARCHAR, "
                 +"sex VARCHAR, "
                  +"signature VARCHA,"
                  +"QQ VARCHAR"
                +")"
                );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+U_USERINFO);
        onCreate(db);
    }
}
