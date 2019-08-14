package com.shinhoandroid.layoutmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;

import com.shinhoandroid.layoutmanager.R;
import com.shinhoandroid.layoutmanager.layoutmanager.Adapter;
import com.shinhoandroid.layoutmanager.layoutmanager.CustomLayoutManager;
import com.shinhoandroid.layoutmanager.layoutmanager.Gallery2LayoutManager;
import com.shinhoandroid.layoutmanager.layoutmanager.Transformer2;

import java.util.ArrayList;
import java.util.List;

public class CustomLayoutManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_layout_manager);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv);
        recyclerView.addItemDecoration(new SpaceItemDecoration(10));

        recyclerView.setLayoutManager(new CustomLayoutManager());
        List<Item> list = DataUtils.getList();
        recyclerView.setAdapter(new Adapter(this,list));
    }



}
