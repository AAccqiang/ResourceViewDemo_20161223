package com.fuicuiedu.idedemo.resourceviewdemo_20161222;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.fuicuiedu.idedemo.resourceviewdemo_20161222.demoa.DemoAActivity;
import com.fuicuiedu.idedemo.resourceviewdemo_20161222.demoa.DemoBActivity;
import com.fuicuiedu.idedemo.resourceviewdemo_20161222.demoa.DemoCActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    @BindView(R.id.listview)ListView listView;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }

    private void initData(){
        String[] datas = {
                "RecyclerView基本运用",
                "下拉刷新",
                "上拉加载",
                "视图封装"
        };

        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datas);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = null;
        switch (position){
            //"RecyclerView基本运用",
            case 0:
                intent = new Intent(this, DemoAActivity.class);
                startActivity(intent);
                break;
            //"下拉刷新"
            case 1:
                intent = new Intent(this, DemoBActivity.class);
                startActivity(intent);
                break;
            //"上拉加载"
            case 2:
                intent = new Intent(this, DemoCActivity.class);
                startActivity(intent);
                break;
            //"视图封装"
            case 3:
                Toast.makeText(this, "视图封装", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
