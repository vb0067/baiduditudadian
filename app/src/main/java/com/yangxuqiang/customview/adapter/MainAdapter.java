package com.yangxuqiang.customview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yangxuqiang.customview.R;
import com.yangxuqiang.customview.ui.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yangxuqiang on 2018/2/1.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {
    private String[] data;
    private Context context;

    public MainAdapter(Context context,String[] data) {
        this.context=context;
        this.data=data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_main, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.childAt.setText(data[position]);

        holder.childAt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)context).itemClick(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return data.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.text)
        TextView childAt;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
