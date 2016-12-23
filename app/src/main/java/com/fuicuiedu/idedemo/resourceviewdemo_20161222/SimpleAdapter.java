package com.fuicuiedu.idedemo.resourceviewdemo_20161222;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.webkit.WebSettings.PluginState.ON;

/**
 * Created by Administrator on 2016/12/22 0022.
 */

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.DemoViewHolder>{

    private ArrayList<String> datas = new ArrayList<>();

    public void addItem(String data){
        datas.add(data);
    }

    public void clear(){
        datas.clear();
    }

    //创建ViewHolder
    @Override
    public DemoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text,parent,false);
        return new DemoViewHolder(view);
    }

    //数据绑定，操作
    @Override
    public void onBindViewHolder(final DemoViewHolder holder, final int position) {
        final String data = datas.get(position);
        holder.textView.setText(data);
        if (listener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(holder.itemView,position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onItemLongClick(holder.itemView,position);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class DemoViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.textView)TextView textView;

        public DemoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int postion);

        void onItemLongClick(View view,int postion);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
