package com.shinhoandroid.layoutmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;

import com.shinhoandroid.layoutmanager.layoutmanager.Adapter;
import com.shinhoandroid.layoutmanager.layoutmanager.Gallery2LayoutManager;
import com.shinhoandroid.layoutmanager.layoutmanager.GalleryLayoutManager;
import com.shinhoandroid.layoutmanager.layoutmanager.Transformer2;

import java.util.List;

public class Gallery2LayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_layout);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv);
        recyclerView.addItemDecoration(new SpaceItemDecoration(10));

        Gallery2LayoutManager manager = new Gallery2LayoutManager();
        manager.setItemTransformer(new Transformer2());
        new LinearSnapHelper().attachToRecyclerView(recyclerView);

        recyclerView.setLayoutManager(manager);
        List<Item> list = DataUtils.getList();
        recyclerView.setAdapter(new Adapter(this,list));
    }
}
