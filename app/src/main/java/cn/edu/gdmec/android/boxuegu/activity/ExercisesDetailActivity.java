package cn.edu.gdmec.android.boxuegu.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.adapter.ExercisesDetailListItemAdapter;
import cn.edu.gdmec.android.boxuegu.bean.ExercisesBean;
import cn.edu.gdmec.android.boxuegu.utils.AnalysisUtils;

public class ExercisesDetailActivity extends AppCompatActivity {


    private TextView tv_back;
    private TextView tv_main_title;
    private RelativeLayout rl_title_bar;
    private RecyclerView rl_list;
    private String title;
    private int id;
    private TextView tv_dibu;
    private List<ExercisesBean> ebl;
    private ExercisesDetailListItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_detail);

        id=getIntent().getIntExtra("id",0);
        title=getIntent().getStringExtra("title");
        ebl=new ArrayList<ExercisesBean>();
        initData();
        init();
    }

    private void initData() {
        try {

            //从xml文件中获取习题数据
            InputStream is=getResources().getAssets().open("chapter" + id + ".xml");
            ebl= AnalysisUtils.getExercisesInfos(is);
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));

        tv_dibu=findViewById(R.id.tv_dibu);
        tv_main_title.setText(title);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExercisesDetailActivity.this.finish();
            }
        });
        adapter=new ExercisesDetailListItemAdapter(ExercisesDetailActivity.this, new ExercisesDetailListItemAdapter.OnSelectListener() {
            @Override
            public void onSelectA(int position, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d) {
                //判断如果答案不是1即A选项
                if (ebl.get(position).answer!=1){
                    ebl.get(position).answer=1;
                }else{
                    ebl.get(position).select=0;
                }
                switch (ebl.get(position).answer){
                    case 1:
                        iv_a.setImageResource(R.drawable.exercises_right_icon);

                        break;
                    case 2:
                        iv_b.setImageResource(R.drawable.exercises_right_icon);
                        iv_a.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 3:
                        iv_a.setImageResource(R.drawable.exercises_error_icon);
                        iv_c.setImageResource(R.drawable.exercises_right_icon);

                        break;
                    case 4:
                        iv_a.setImageResource(R.drawable.exercises_error_icon);
                        iv_d.setImageResource(R.drawable.exercises_right_icon);

                        break;
                }
                AnalysisUtils.setABCDEnable(false,iv_a,iv_b,iv_c,iv_d);
            }

            @Override
            public void onSelectB(int position, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d) {
                //判断如果答案不是2即B选项
                if (ebl.get(position).answer!=2){
                    ebl.get(position).answer=2;
                }else{
                    ebl.get(position).select=0;
                }
                switch (ebl.get(position).answer){
                    case 1:
                        iv_a.setImageResource(R.drawable.exercises_right_icon);
                        iv_b.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 2:
                        iv_b.setImageResource(R.drawable.exercises_right_icon);

                        break;
                    case 3:
                        iv_c.setImageResource(R.drawable.exercises_right_icon);
                        iv_b.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 4:
                        iv_b.setImageResource(R.drawable.exercises_error_icon);
                        iv_d.setImageResource(R.drawable.exercises_right_icon);

                        break;
                }
                AnalysisUtils.setABCDEnable(false,iv_a,iv_b,iv_c,iv_d);
            }

            @Override
            public void onSelectC(int position, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d) {
                //判断如果答案不是3即C选项
                if (ebl.get(position).answer!=3){
                    ebl.get(position).answer=3;
                }else{
                    ebl.get(position).select=0;
                }
                switch (ebl.get(position).answer){
                    case 1:
                        iv_a.setImageResource(R.drawable.exercises_right_icon);
                        iv_c.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 2:
                        iv_b.setImageResource(R.drawable.exercises_right_icon);
                        iv_c.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 3:
                        iv_c.setImageResource(R.drawable.exercises_right_icon);

                        break;
                    case 4:
                        iv_c.setImageResource(R.drawable.exercises_error_icon);
                        iv_d.setImageResource(R.drawable.exercises_right_icon);

                        break;
                }
                AnalysisUtils.setABCDEnable(false,iv_a,iv_b,iv_c,iv_d);
            }

            @Override
            public void onSelectD(int position, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d) {
                //判断如果答案不是4即D选项
                if (ebl.get(position).answer!=4){
                    ebl.get(position).answer=4;
                }else{
                    ebl.get(position).select=0;
                }
                switch (ebl.get(position).answer){
                    case 1:
                        iv_a.setImageResource(R.drawable.exercises_right_icon);
                        iv_b.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 2:
                        iv_b.setImageResource(R.drawable.exercises_right_icon);
                        iv_d.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 3:
                        iv_c.setImageResource(R.drawable.exercises_right_icon);
                        iv_d.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 4:
                        iv_d.setImageResource(R.drawable.exercises_right_icon);

                        break;
                }
                AnalysisUtils.setABCDEnable(false,iv_a,iv_b,iv_c,iv_d);
            }
        });
       adapter.setOnOnItemListener(new ExercisesDetailListItemAdapter.OnItemListener() {
           @Override
           public void onItem(View view, int position) {
               int s=position+1;
                tv_dibu.setText("第"+s+"题完成，共5题");
           }
       });
        adapter.setData(ebl);
        rl_list = (RecyclerView) findViewById(R.id.rl_list);
        rl_list.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        rl_list.setAdapter(adapter);
    }


}
