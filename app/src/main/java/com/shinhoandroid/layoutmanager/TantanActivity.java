package com.shinhoandroid.layoutmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.shinhoandroid.layoutmanager.layoutmanager.Adapter2;
import com.shinhoandroid.layoutmanager.layoutmanager.OverLayCardLayoutManager;
import com.shinhoandroid.layoutmanager.layoutmanager.RenRenCallback;
import com.shinhoandroid.layoutmanager.layoutmanager.TanTanCallback;

import java.util.List;

public class TantanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_linear_layout);

        RecyclerView mRv = (RecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(new OverLayCardLayoutManager());
        List<Item> mDatas = DataUtils.getList();
        Adapter2 mAdapter = new Adapter2(this,mDatas);

        mRv.setAdapter(mAdapter);

        //初始化配置
        CardConfig.initConfig(this);
        ItemTouchHelper.Callback callback = new TanTanCallback(mRv, mAdapter, mDatas);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mRv);

    }
}
