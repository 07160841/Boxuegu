package cn.edu.gdmec.android.boxuegu.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.bean.UserBean;
import cn.edu.gdmec.android.boxuegu.utils.AnalysisUtils;
import cn.edu.gdmec.android.boxuegu.utils.DBUtils;

public class UserInfoActivity extends AppCompatActivity
        implements View.OnClickListener{
    private TextView tv_back;
    private TextView tv_main_title;
    private TextView tv_nickName,tv_signature,tv_user_name,tv_sex,tv_QQ;
    private RelativeLayout rl_nickName,rl_sex,rl_signature,rl_title_bar,rl_QQ;
    private String spUserName;
    private static final int CHANGE_NICKNAME=1;//修改昵称的自定义常量
    private static final int CHANGE_SIGNATURE=2;//修改签名 的自定义常量
    private static final int CHANGE_QQ=3;//修改QQ的自定义常量
    private String new_info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        spUserName= AnalysisUtils.readLoginUserName(this);
        init();
        initData();
        settListener();
    }
//设置控件的点击监听事件
    private void settListener() {
        tv_back.setOnClickListener(this);
        rl_nickName.setOnClickListener(this);
        rl_sex.setOnClickListener(this);
        rl_signature.setOnClickListener(this);
        rl_QQ.setOnClickListener(this);
    }

    private void initData() {
        UserBean bean=null;
        bean = DBUtils.getInstance(this).getUserInfo(spUserName);
        //首先判断一下数据库是否有数据
        if(bean==null){
            bean=new UserBean();
            bean.userName=spUserName;
            bean.nickName="程序猿";
            bean.sex="男";
            bean.signature="我爱写代码";
            bean.QQ="未添加";
            DBUtils.getInstance(this).saveUserInfo(bean);
        }
        setValue(bean);
    }
    //为界面控件设置值
    private void setValue(UserBean bean) {
        tv_nickName.setText(bean.nickName);
        tv_user_name.setText(bean.userName);
        tv_sex.setText(bean.sex);
        tv_signature.setText(bean.signature);
        tv_QQ.setText(bean.QQ);
    }

    private void init() {
        tv_back= (TextView) findViewById(R.id.tv_back);
        tv_main_title= (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("个人资料");
        rl_title_bar= (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        rl_nickName= (RelativeLayout) findViewById(R.id.rl_nickName);
        rl_sex= (RelativeLayout) findViewById(R.id.rl_sex);
        rl_signature= (RelativeLayout) findViewById(R.id.rl_signature);
        tv_nickName= (TextView) findViewById(R.id.tv_nickName);
        tv_user_name= (TextView) findViewById(R.id.tv_user_name);
        tv_sex= (TextView) findViewById(R.id.tv_sex);
        tv_signature= (TextView) findViewById(R.id.tv_signature);
        rl_QQ= (RelativeLayout) findViewById(R.id.rl_QQ);
        tv_QQ= (TextView) findViewById(R.id.tv_QQ);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_back:
                this.finish();
                break;
            case R.id.rl_nickName:
                String name=tv_nickName.getText().toString();
                Bundle bdName=new Bundle();
                bdName.putString("content",name);
                bdName.putString("title","昵称");
                bdName.putInt("flag",1);
                enterActivityForResult(ChangeUserInfoActivity.class,CHANGE_NICKNAME,bdName);
                break;
            case R.id.rl_sex:
                String sex=tv_sex.getText().toString();
                sexDialog(sex);
                break;
            case R.id.rl_signature:
                String signature=tv_signature.getText().toString();
                Bundle bdSignature = new Bundle();
                bdSignature.putString("content",signature);
                bdSignature.putString("title","签名");
                bdSignature.putInt("flag",2);
                enterActivityForResult(ChangeUserInfoActivity.class,CHANGE_SIGNATURE,bdSignature);
                break;

            case R.id.rl_QQ:
                String QQ1=tv_QQ.getText().toString();
                Bundle QQ = new Bundle();
                QQ.putString("content",QQ1);
                QQ.putString("title","QQ号");
                QQ.putInt("flag",3);
                enterActivityForResult(ChangeUserInfoActivity.class,CHANGE_QQ,QQ);
                break;
            default:
                break;
        }
    }
//设置性别弹出框
    private void sexDialog(String sex) {
        int sexFlag=0;
        if("男".equals(sex)){
            sexFlag=0;
        }else if ("女".equals(sex)){
            sexFlag=1;
        }
        final String items[]={"男","女"};
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("性别");
        builder.setSingleChoiceItems(items, sexFlag,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(UserInfoActivity.this,items[which],
                                Toast.LENGTH_SHORT).show();
                        setSex(items[which]);
                    }
                });
        builder.create().show();
    }

    private void setSex(String sex) {
        tv_sex.setText(sex);
        //更新 数据库中的性别字段
        DBUtils.getInstance(UserInfoActivity.this).updataUserInfo("sex",sex,spUserName);
    }
    //获取回传数据时需要使用的跳转方法，to->表示需要跳转的界面
    //requestCode 请求码 ， b 跳转时传递的数据
    public void enterActivityForResult(Class<?> to,int requestCode,Bundle b){
        Intent i=new Intent(this,to);
        i.putExtras(b);
        startActivityForResult(i,requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case CHANGE_NICKNAME://个人资料修改界面回传过来的昵称数据
                if (data!=null){
                    new_info=data.getStringExtra("nickName");
                    if (TextUtils.isEmpty(new_info)){
                        return;
                    }
                    tv_nickName.setText(new_info);
                    //更新数据库中的昵称字段
                    DBUtils.getInstance(UserInfoActivity.this).updataUserInfo("nickName",new_info,spUserName);
                }
                break;
            case CHANGE_SIGNATURE://个人资料修改界面回传过来的签名数据
                if (data!=null){
                new_info=data.getStringExtra("signature");
                if (TextUtils.isEmpty(new_info)){
                    return;
                }
                tv_signature.setText(new_info);
                //更新数据库中的签名字段
                    DBUtils.getInstance(UserInfoActivity.this).updataUserInfo(
                            "signature",new_info,spUserName);
                }
                break;
            case CHANGE_QQ://个人资料修改界面回传过来的QQ数据
                if (data!=null){
                    new_info=data.getStringExtra("QQ");
                    if (TextUtils.isEmpty(new_info)){
                        return;
                    }
                    tv_QQ.setText(new_info);
                    //更新数据库中的QQ字段
                    DBUtils.getInstance(UserInfoActivity.this).updataUserInfo(
                            "QQ",new_info,spUserName);
                }
                break;
        }
    }
}
