package cn.edu.gdmec.android.boxuegu.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.utils.AnalysisUtils;
import cn.edu.gdmec.android.boxuegu.utils.MD5Utils;

public class FindPswActivity extends AppCompatActivity {
    private EditText et_validate_name,et_user_name;
    private TextView tv_main_title;
    private Button btn_validate;
    private TextView tv_back;
    private String from;
    private TextView tv_user_name;
    private TextView tv_newpsw;
    private EditText et_newpsw;
    private Button btn_set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_psw);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        from=getIntent().getStringExtra("from");
        init();
    }

    private void init() {
        tv_main_title= (TextView) findViewById(R.id.tv_main_title);
        tv_back= (TextView) findViewById(R.id.tv_back);
        et_validate_name= (EditText) findViewById(R.id.et_validate_name);
        btn_validate= (Button) findViewById(R.id.btn_validate);
        btn_set= (Button) findViewById(R.id.btn_set);
        et_user_name= (EditText) findViewById(R.id.et_user_name);
        tv_user_name= (TextView) findViewById(R.id.tv_user_name);
        tv_newpsw= (TextView) findViewById(R.id.tv_newpsw);
        et_newpsw= (EditText) findViewById(R.id.et_newpsw);
        if("security".equals(from)){
            tv_main_title.setText("设置密保");
            btn_validate.setVisibility(View.VISIBLE);
            btn_validate.setText("设置");
        }else{
            tv_main_title.setText("找回密码");
            tv_user_name.setVisibility(View.VISIBLE);
            et_user_name.setVisibility(View.VISIBLE);
            btn_validate.setVisibility(View.VISIBLE);
        }
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FindPswActivity.this.finish();
            }
        });
        btn_validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String validateName=et_validate_name.getText().toString().trim();
                if("security".equals(from)){
                    if(TextUtils.isEmpty(validateName)){
                        Toast.makeText(FindPswActivity.this,"请输入要验证的姓名",Toast.LENGTH_SHORT).show();
                        return;
                    }else{
                        Toast.makeText(FindPswActivity.this,"密保设置成功",Toast.LENGTH_SHORT).show();
                        saveSecurity(validateName);
                        FindPswActivity.this.finish();
                    }
                }else{
                    String userName=et_user_name.getText().toString().trim();
                    String sp_security=readSecurity(userName);
                    if (TextUtils.isEmpty(userName)){
                        Toast.makeText(FindPswActivity.this,"请输入您的用户名",Toast.LENGTH_SHORT).show();
                        return;
                    }else if (!isExistUserName(userName)){
                        Toast.makeText(FindPswActivity.this,"您输入的用户名不存在",Toast.LENGTH_SHORT).show();
                        return;

                    }else if (TextUtils.isEmpty(validateName)){
                        Toast.makeText(FindPswActivity.this,"请输入要验证的姓名",Toast.LENGTH_SHORT).show();
                        return;
                    }else if(!validateName.equals(sp_security)){
                        Toast.makeText(FindPswActivity.this,"输入的密保不正确",Toast.LENGTH_SHORT).show();
                        return;
                    }else{
                        tv_newpsw.setVisibility(View.VISIBLE);
                        et_newpsw.setVisibility(View.VISIBLE);
                        btn_validate.setVisibility(View.GONE);
                        btn_set.setVisibility(View.VISIBLE);

                        btn_set.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String newpsw=et_newpsw.getText().toString();
                                if (TextUtils.isEmpty(newpsw)){
                                    Toast.makeText(FindPswActivity.this,"请输入新密码",Toast.LENGTH_SHORT).show();
                                    return;
                                }else{
                                    Toast.makeText(FindPswActivity.this,"新密码设置成功",Toast.LENGTH_SHORT).show();
                                    String userName=et_user_name.getText().toString().trim();
                                    savePsw(userName,newpsw);
                                    FindPswActivity.this.finish();
                                }
                            }
                        });

                    }
                }
            }
        });


    }
//保存初始化密码
    private void savePsw(String userName,String newpsw) {
        String md5Psw= MD5Utils.md5(newpsw);
        SharedPreferences sp=getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor =sp.edit();
        editor.putString(userName,md5Psw);
        editor.commit();
    }
//保存密码到SharedPrefences中
    private boolean isExistUserName(String userName) {
        boolean hasUserName=false;
        SharedPreferences sp=getSharedPreferences("loginInfo",MODE_PRIVATE);
        String spPsw=sp.getString(userName,"");
        if (!TextUtils.isEmpty(spPsw)) {
            hasUserName=true;
        }
        return hasUserName;
    }

    private String readSecurity(String userName) {
        SharedPreferences sp=getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        String security=sp.getString(userName+"_security","");
        return security;
    }

    private void saveSecurity(String validateName) {
        SharedPreferences sp=getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString(AnalysisUtils.readLoginUserName(this)+"_security",validateName);
        editor.commit();
    }
}
