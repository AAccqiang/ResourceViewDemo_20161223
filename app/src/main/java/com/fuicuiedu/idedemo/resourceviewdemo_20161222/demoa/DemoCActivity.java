package com.fuicuiedu.idedemo.resourceviewdemo_20161222.demoa;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.fuicuiedu.idedemo.resourceviewdemo_20161222.R;
import com.fuicuiedu.idedemo.resourceviewdemo_20161222.SimpleAdapter;
import com.mugen.Mugen;
import com.mugen.MugenCallbacks;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DemoCActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,MugenCallbacks{

    @BindView(R.id.recyclerview)RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)SwipeRefreshLayout swipeRefreshLayout;
    SimpleAdapter simpleAdapter;

    @BindView(R.id.progressBar)ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_c);
        ButterKnife.bind(this);

        simpleAdapter = new SimpleAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(simpleAdapter);
        swipeRefreshLayout.setOnRefreshListener(this);

        Mugen.with(recyclerView,this).start();
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
                for(int i = 0;i < 20;i++){
                    simpleAdapter.addItem("下拉刷新第" + i +"项");
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

    @Override
    public void onLoadMore() {
        progressBar.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for(int i = 0;i < 10;i++){
                    simpleAdapter.addItem("上拉加载第" + i +"项");
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        simpleAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }
        }).start();
    }

    @Override
    public boolean isLoading() {
        return progressBar.getVisibility() == View.VISIBLE;
    }

    @Override
    public boolean hasLoadedAllItems() {
        return false;
    }
}
