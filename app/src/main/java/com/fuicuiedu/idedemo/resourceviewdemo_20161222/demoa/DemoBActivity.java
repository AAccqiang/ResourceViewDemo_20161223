package com.fuicuiedu.idedemo.resourceviewdemo_20161222.demoa;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.fuicuiedu.idedemo.resourceviewdemo_20161222.R;
import com.fuicuiedu.idedemo.resourceviewdemo_20161222.SimpleAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/23 0023.
 */

public class DemoBActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener{


    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    SimpleAdapter simpleAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_b);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        simpleAdapter = new SimpleAdapter();
        recyclerView.setAdapter(simpleAdapter);



        swipeRefreshLayout.setOnRefreshListener(this);


    }

    @Override
    public void onRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                simpleAdapter.clear();
                for (int i = 0; i < 20; i++) {
                    simpleAdapter.addItem("我是第" + i + "条数据");
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        simpleAdapter.notifyDataSetChanged();

                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }
}
