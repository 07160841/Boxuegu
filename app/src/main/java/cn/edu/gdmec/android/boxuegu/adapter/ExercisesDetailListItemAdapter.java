package cn.edu.gdmec.android.boxuegu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.bean.ExercisesBean;
import cn.edu.gdmec.android.boxuegu.utils.AnalysisUtils;

/**
 * Created by lenovo on 2018/3/29.
 */

public class ExercisesDetailListItemAdapter extends RecyclerView.Adapter<ExercisesDetailListItemAdapter.ViewHolder> {
   private ArrayList<String> selectedPosition=new ArrayList<String>();
    private List<ExercisesBean> objects=new ArrayList<ExercisesBean>();
    private OnSelectListener onSelectListener;
    private OnItemListener onItemListener;
    private TextView tv_bottom;
    private Context context;
    private LayoutInflater layoutInflater;

    public ExercisesDetailListItemAdapter(Context context, OnSelectListener onSelectListener){
        this.context=context;
        this.onSelectListener=onSelectListener;
        this.layoutInflater=LayoutInflater.from(context);
    }
    public void setOnOnItemListener(OnItemListener onItemListener){
        this.onItemListener=onItemListener;
    }

    public void setData(List<ExercisesBean> objects){
        this.objects=objects;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.exercises_details_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

            initializeViews(objects.get(position),holder,position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }
private void initializeViews(ExercisesBean object,final ViewHolder vh,final  int position){
        ExercisesBean bean=object;
        if(bean!=null){
            vh.subject.setText(bean.subject);
            vh.tv_a.setText(bean.a);
            vh.tv_b.setText(bean.b);
            vh.tv_c.setText(bean.c);
            vh.tv_d.setText(bean.d);
        }
    if (!selectedPosition.contains("" + position)) {
        vh.iv_a.setImageResource(R.drawable.exercises_a);
        vh.iv_b.setImageResource(R.drawable.exercises_b);
        vh.iv_c.setImageResource(R.drawable.exercises_c);
        vh.iv_d.setImageResource(R.drawable.exercises_d);
        AnalysisUtils.setABCDEnable(true,vh.iv_a,vh.iv_b,vh.iv_c,vh.iv_d);

    }else{
        AnalysisUtils.setABCDEnable(false,vh.iv_a,vh.iv_b,vh.iv_c,vh.iv_d);

        switch(bean.select) {
            case 0:
                //用户所选项是正确的
                if (bean.answer == 1) {
                    vh.iv_a.setImageResource(R.drawable.exercises_right_icon);
                    vh.iv_b.setImageResource(R.drawable.exercises_b);
                    vh.iv_c.setImageResource(R.drawable.exercises_c);
                    vh.iv_d.setImageResource(R.drawable.exercises_d);
                } else if (bean.answer == 2) {
                    vh.iv_a.setImageResource(R.drawable.exercises_a);
                    vh.iv_b.setImageResource(R.drawable.exercises_right_icon);
                    vh.iv_c.setImageResource(R.drawable.exercises_c);
                    vh.iv_d.setImageResource(R.drawable.exercises_d);
                } else if (bean.answer == 3) {
                    vh.iv_a.setImageResource(R.drawable.exercises_a);
                    vh.iv_b.setImageResource(R.drawable.exercises_b);
                    vh.iv_c.setImageResource(R.drawable.exercises_right_icon);
                    vh.iv_d.setImageResource(R.drawable.exercises_d);
                } else if (bean.answer == 4) {
                    vh.iv_a.setImageResource(R.drawable.exercises_a);
                    vh.iv_b.setImageResource(R.drawable.exercises_b);
                    vh.iv_c.setImageResource(R.drawable.exercises_c);
                    vh.iv_d.setImageResource(R.drawable.exercises_right_icon);
                }
                break;
            case 1:
                //用户所选项A是错的
                vh.iv_a.setImageResource(R.drawable.exercises_error_icon);
                if (bean.answer == 2) {
                    vh.iv_b.setImageResource(R.drawable.exercises_right_icon);
                    vh.iv_c.setImageResource(R.drawable.exercises_c);
                    vh.iv_d.setImageResource(R.drawable.exercises_d);
                } else if (bean.answer == 3) {
                    vh.iv_b.setImageResource(R.drawable.exercises_b);
                    vh.iv_c.setImageResource(R.drawable.exercises_right_icon);
                    vh.iv_d.setImageResource(R.drawable.exercises_d);
                } else if (bean.answer == 4) {
                    vh.iv_b.setImageResource(R.drawable.exercises_b);
                    vh.iv_c.setImageResource(R.drawable.exercises_c);
                    vh.iv_d.setImageResource(R.drawable.exercises_right_icon);
                }
                break;
            case 2:
                //用户所选项B是错误的
                vh.iv_b.setImageResource(R.drawable.exercises_error_icon);
                if (bean.answer == 1) {
                    vh.iv_a.setImageResource(R.drawable.exercises_right_icon);
                    vh.iv_c.setImageResource(R.drawable.exercises_c);
                    vh.iv_d.setImageResource(R.drawable.exercises_d);
                } else if (bean.answer == 3) {
                    vh.iv_a.setImageResource(R.drawable.exercises_a);
                    vh.iv_c.setImageResource(R.drawable.exercises_right_icon);
                    vh.iv_d.setImageResource(R.drawable.exercises_d);
                } else if (bean.answer == 4) {
                    vh.iv_a.setImageResource(R.drawable.exercises_a);
                    vh.iv_c.setImageResource(R.drawable.exercises_c);
                    vh.iv_d.setImageResource(R.drawable.exercises_right_icon);
                }
                break;
            //c错误
            case 3:

                vh.iv_c.setImageResource(R.drawable.exercises_error_icon);
                if (bean.answer == 1) {
                    vh.iv_a.setImageResource(R.drawable.exercises_right_icon);
                    vh.iv_b.setImageResource(R.drawable.exercises_b);
                    vh.iv_d.setImageResource(R.drawable.exercises_d);
                } else if (bean.answer == 2) {
                    vh.iv_a.setImageResource(R.drawable.exercises_a);
                    vh.iv_b.setImageResource(R.drawable.exercises_right_icon);
                    vh.iv_d.setImageResource(R.drawable.exercises_d);
                } else if (bean.answer == 4) {
                    vh.iv_a.setImageResource(R.drawable.exercises_a);
                    vh.iv_b.setImageResource(R.drawable.exercises_b);
                    vh.iv_d.setImageResource(R.drawable.exercises_right_icon);
                }
                break;
            case 4:
                //用户所选项B是错误的
                vh.iv_d.setImageResource(R.drawable.exercises_error_icon);
                if (bean.answer == 1) {
                    vh.iv_a.setImageResource(R.drawable.exercises_right_icon);
                    vh.iv_b.setImageResource(R.drawable.exercises_b);
                    vh.iv_c.setImageResource(R.drawable.exercises_c);
                } else if (bean.answer == 2) {
                    vh.iv_a.setImageResource(R.drawable.exercises_a);
                    vh.iv_b.setImageResource(R.drawable.exercises_right_icon);
                    vh.iv_c.setImageResource(R.drawable.exercises_c);
                } else if (bean.answer == 3) {
                    vh.iv_a.setImageResource(R.drawable.exercises_a);
                    vh.iv_b.setImageResource(R.drawable.exercises_b);
                    vh.iv_c.setImageResource(R.drawable.exercises_right_icon);
                }
                break;

    }
}

        //当点击A选项的点击事件
        vh.iv_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemListener.onItem(view,position);
                //判断SelectedPosition中是否包含此时点击的position
                if (selectedPosition.contains(""+position)){
                    selectedPosition.remove(""+position);
                }else{
                    selectedPosition.add(position+"");
                }
                onSelectListener.onSelectA(position,vh.iv_a,vh.iv_b,vh.iv_c,vh.iv_d);
            }
        });
        //当点击B选项的点击事件
        vh.iv_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemListener.onItem(view,position);
                //判断SelectedPosition中是否包含此时点击的position
                if (selectedPosition.contains(""+position)){
                    selectedPosition.remove(""+position);
                }else{
                    selectedPosition.add(position+"");
                }
                onSelectListener.onSelectB(position,vh.iv_a,vh.iv_b,vh.iv_c,vh.iv_d);
            }
        });
        //当点击c选项的点击事件
        vh.iv_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemListener.onItem(view,position);
                //判断SelectedPosition中是否包含此时点击的position
                if (selectedPosition.contains(""+position)){
                    selectedPosition.remove(""+position);
                }else{
                    selectedPosition.add(position+"");
                }
                onSelectListener.onSelectC(position,vh.iv_a,vh.iv_b,vh.iv_c,vh.iv_d);
            }
        });
        //当点击D选项的点击事件
        vh.iv_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemListener.onItem(view,position);
                //判断SelectedPosition中是否包含此时点击的position
                if (selectedPosition.contains(""+position)){
                    selectedPosition.remove(""+position);
                }else{
                    selectedPosition.add(position+"");
                }
                onSelectListener.onSelectD(position,vh.iv_a,vh.iv_b,vh.iv_c,vh.iv_d);
            }
        });

    }

    protected  class ViewHolder extends RecyclerView.ViewHolder {
        public TextView subject, tv_a,tv_b,tv_c,tv_d;
        public ImageView iv_a,iv_b,iv_c,iv_d;
        public ViewHolder(View view){
            super(view);
            subject= (TextView) view.findViewById(R.id.tv_subject);
            iv_a=view.findViewById(R.id.iv_a);
            iv_b=view.findViewById(R.id.iv_b);
            iv_c=view.findViewById(R.id.iv_c);
            iv_d=view.findViewById(R.id.iv_d);
            tv_a=view.findViewById(R.id.tv_a);
            tv_b=view.findViewById(R.id.tv_b);
            tv_c=view.findViewById(R.id.tv_c);
            tv_d=view.findViewById(R.id.tv_d);


        }
    }
    public interface OnSelectListener{
        void onSelectA(int position ,ImageView iv_a,ImageView iv_b,ImageView iv_c,ImageView iv_d);
        void onSelectB(int position , ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d);
        void onSelectC(int position ,ImageView iv_a,ImageView iv_b,ImageView iv_c,ImageView iv_d);
        void onSelectD(int position ,ImageView iv_a,ImageView iv_b,ImageView iv_c,ImageView iv_d);
    }

    public interface OnItemListener{
    void onItem(View view,int position);

    }
}
