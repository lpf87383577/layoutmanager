package com.shinhoandroid.layoutmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.shinhoandroid.layoutmanager.layoutmanager.Adapter;
import com.shinhoandroid.layoutmanager.layoutmanager.CustomLinearLayoutManager;
import com.shinhoandroid.layoutmanager.layoutmanager.GalleryLayoutManager;
import com.shinhoandroid.layoutmanager.layoutmanager.Transformer;

import java.util.List;

public class GalleryLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_layout);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv);
        recyclerView.addItemDecoration(new SpaceItemDecoration(10));
        GalleryLayoutManager manager = new GalleryLayoutManager(GalleryLayoutManager.VERTICAL);
        manager.attach(recyclerView);
        //设置滑动缩放效果
        manager.setItemTransformer(new Transformer());
        recyclerView.setLayoutManager(manager);
        List<Item> list = DataUtils.getList();
        recyclerView.setAdapter(new Adapter(this,list));
    }
}
