package com.shinhoandroid.layoutmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.shinhoandroid.layoutmanager.layoutmanager.Adapter;
import com.shinhoandroid.layoutmanager.layoutmanager.CustomLayoutManager;
import com.shinhoandroid.layoutmanager.layoutmanager.CustomLinearLayoutManager;
import com.shinhoandroid.layoutmanager.layoutmanager.Gallery2LayoutManager;
import com.shinhoandroid.layoutmanager.layoutmanager.GalleryLayoutManager;
import com.shinhoandroid.layoutmanager.layoutmanager.Transformer;
import com.shinhoandroid.layoutmanager.layoutmanager.Transformer2;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void bt1(View v){

        Intent intent = new Intent(this,CustomLinearLayoutActivity.class);

        startActivity(intent);
    }

    public void bt2(View v){

        Intent intent = new Intent(this,GalleryLayoutActivity.class);

        startActivity(intent);
    }

    public void bt3(View v){

        Intent intent = new Intent(this,CustomLayoutManagerActivity.class);

        startActivity(intent);
    }

    public void bt4(View v){

        Intent intent = new Intent(this,Gallery2LayoutActivity.class);

        startActivity(intent);
    }

    public void bt5(View v){

        Intent intent = new Intent(this,RenRenActivity.class);

        startActivity(intent);
    }

    public void bt6(View v){

        Intent intent = new Intent(this,TantanActivity.class);

        startActivity(intent);
    }
}
