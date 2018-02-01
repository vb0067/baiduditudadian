package com.yangxuqiang.customview.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yangxuqiang.customview.R;
import com.yangxuqiang.customview.adapter.MainAdapter;
import com.yangxuqiang.customview.base.BaseActivity;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;


    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        String[] datas = getResources().getStringArray(R.array.click_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MainAdapter(this,datas));
    }



    public void itemClick(int position) {
        switch (position){
            case 0://滑动解锁

                break;
        }
    }


}
