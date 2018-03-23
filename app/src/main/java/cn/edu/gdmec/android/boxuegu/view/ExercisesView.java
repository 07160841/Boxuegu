package cn.edu.gdmec.android.boxuegu.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.adapter.ExercisesAdapter;
import cn.edu.gdmec.android.boxuegu.bean.ExercisesBean;

/**
 * Created by lenovo on 2018/3/22.
 */

public class ExercisesView {
    private ListView lv_list;
    private ExercisesAdapter adapter;
    private List<ExercisesBean>  ebl;
    private Activity mContext;
    private LayoutInflater mInflater;
    private View mCurrentView;
    public ExercisesView(Activity context){
        mContext=context;
        mInflater=LayoutInflater.from(mContext);
    }
    private void createView(){
        initView();
    }

    private void initView() {
        mCurrentView=mInflater.inflate(R.layout.main_view_exercises,null);
        lv_list=mCurrentView.findViewById(R.id.lv_list);
        adapter=new ExercisesAdapter(mContext);
        initData();
        adapter.setData(ebl);
        lv_list.setAdapter(adapter);
    }
//设置数据
    private void initData() {
        ebl=new ArrayList<ExercisesBean>();
        for (int i=0;i<10;i++){
            ExercisesBean bean=new ExercisesBean();
            bean.id=(i+1);
            switch (i){
                case 0:
                    bean.title="第一章 Android基础入门";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_1);
                    break;
                case 1:
                    bean.title="第二章 AndroidUI开发";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_2);
                    break;
                case 2:
                    bean.title="第三章 Activity";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_3);
                    break;
                case 3:
                    bean.title="第四章 数据存储";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_4);
                    break;
                case 4:
                    bean.title="第五章 SQLite数据库";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_1);
                    break;
                case 5:
                    bean.title="第六章 广播接受者 ";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_2);
                    break;
                case 6:
                    bean.title="第七章 服务";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_3);
                    break;
                case 7:
                    bean.title="第八章 内容提供者";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_4);
                    break;
                case 8:
                    bean.title="第九章 网络编程";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_1);
                    break;
                case 9:
                    bean.title="第十章 高级编程";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_2);
                    break;
                    default:
                        break;
            }
            ebl.add(bean);
        }
    }
    //获取当前在导航栏上方显示对应的View
    public View getView(){
        if (mCurrentView==null){
            createView();
        }
        return mCurrentView;
    }
    //显示当前导航栏上方所对应的view界面
    public void showView(){
        if(mCurrentView==null){
            createView();
        }
        mCurrentView.setVisibility(View.VISIBLE);
    }
}
