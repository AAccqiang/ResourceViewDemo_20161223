package com.fuicuiedu.idedemo.resourceviewdemo_20161222.demoa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.fuicuiedu.idedemo.resourceviewdemo_20161222.R;
import com.fuicuiedu.idedemo.resourceviewdemo_20161222.SimpleAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.data;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class DemoAActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview)RecyclerView recyclerView;

    private SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_a);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        simpleAdapter = new SimpleAdapter();
        recyclerView.setAdapter(simpleAdapter);

        for (int i = 0; i < 100; i++) {
            simpleAdapter.addItem("我是第" + i + "条数据");
        }
        simpleAdapter.notifyDataSetChanged();


        simpleAdapter.setOnItemClickListener(new SimpleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Toast.makeText(DemoAActivity.this, "点击：" + postion, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int postion) {
                Toast.makeText(DemoAActivity.this, "长按：" + postion, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
