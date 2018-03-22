package cn.edu.gdmec.android.boxuegu.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

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
        initData(ebl);
        lv_list.setAdapter(adapter);
    }

    private void initData(List<ExercisesBean> ebl) {
    }
}
