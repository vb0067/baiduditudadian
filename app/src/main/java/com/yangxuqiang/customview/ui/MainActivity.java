package com.yangxuqiang.customview.ui;

import android.content.Intent;
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
                jump(Test1Activity.class);
                break;
            case 1://百度地图
                jump(MapActivity.class);
                break;
            case 2://添加包名
                jump(AddPackage.class);
                break;
        }
    }

    private void jump(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }


}
