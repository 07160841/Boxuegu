package cn.edu.gdmec.android.boxuegu.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.activity.ExercisesDetailActivity;
import cn.edu.gdmec.android.boxuegu.bean.ExercisesBean;

/**
 * Created by lenovo on 2018/3/22.
 */

public class ExercisesAdapter extends BaseAdapter {
    private Context mContext;
    private List<ExercisesBean> ebl;
    public ExercisesAdapter(Context context){
        this.mContext=context;
    }
    //设置数据更新界面
    public void setData(List<ExercisesBean> ebl){
        this.ebl=ebl;
        notifyDataSetChanged();
    }
    //获取Item的总数
    @Override
    public int getCount() {
        return ebl==null ? 0 : ebl.size();
    }
//根据position得到对应Item的id
    @Override
    public ExercisesBean getItem(int position) {
        return ebl==null ? null : ebl.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        //复用convertView
        if (convertView==null){
            vh=new ViewHolder();
            convertView= LayoutInflater.from(mContext).inflate(
                    R.layout.exercises_list_item,null);
            vh.title=convertView.findViewById(R.id.tv_title);
            vh.content=convertView.findViewById(R.id.tv_content);
            vh.order=convertView.findViewById(R.id.tv_order);
            convertView.setTag(vh);
        }else{
            vh= (ViewHolder) convertView.getTag();
        }
        //获取position对应的Item的数据对象
        final ExercisesBean bean=getItem(position);
        if (bean!=null){
            vh.order.setText(position + 1 + "");
            vh.title.setText(bean.title);
            vh.content.setText(bean.content);
            vh.order.setBackgroundResource(bean.background);
        }
        //每个Item的点击事件
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bean == null) {
                    return;
                }
                //跳转到习题详情界面
                Intent intent=new Intent(mContext, ExercisesDetailActivity.class);
                //把章节ID传递到习题详情界面
                intent.putExtra("id",bean.id);
                //标题0
                intent.putExtra("title",bean.title);
                //((Activity)mContext.startActivityForResult(intent,000);
                mContext.startActivity(intent);


            }
        });
        return convertView;
    }
    class ViewHolder{
        public TextView title,content;
        public TextView order;
    }
}
