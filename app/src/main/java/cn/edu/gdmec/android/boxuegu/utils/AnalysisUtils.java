package cn.edu.gdmec.android.boxuegu.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by lenovo on 2018/3/20.
 */

public class AnalysisUtils {

    public static String readLoginUserName(Context context){
        SharedPreferences sp=context.getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
                String userName=sp.getString("loginUserName","");//获取用户名
        return userName;
    }
}
