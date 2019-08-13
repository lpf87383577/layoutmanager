package com.shinhoandroid.layoutmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

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
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv);
        recyclerView.addItemDecoration(new SpaceItemDecoration(10));
//        GalleryLayoutManager manager = new GalleryLayoutManager(GalleryLayoutManager.VERTICAL);
//        manager.attach(recyclerView);
//        //设置滑动缩放效果
//        manager.setItemTransformer(new Transformer());
//        recyclerView.setLayoutManager(manager);
        Gallery2LayoutManager manager = new Gallery2LayoutManager();
        manager.setItemTransformer(new Transformer2());
        recyclerView.setLayoutManager(manager);

        new LinearSnapHelper().attachToRecyclerView(recyclerView);
        List<Item> list = getItems();
        recyclerView.setAdapter(new Adapter(this,list));
    }

    private List<Item> getItems() {
        List<Item> showItems = new ArrayList<>();
        showItems.add(new Item("北京"));
        showItems.add(new Item("天津"));
        showItems.add(new Item("上海"));
        showItems.add(new Item("重庆"));
        showItems.add(new Item("广州"));
        showItems.add(new Item("深圳"));
        showItems.add(new Item("成都"));
        showItems.add(new Item("武汉"));
        showItems.add(new Item("南京"));
        showItems.add(new Item("大连"));
        showItems.add(new Item("青岛"));
        showItems.add(new Item("厦门"));
        showItems.add(new Item("南宁"));
        showItems.add(new Item("柳州"));
        showItems.add(new Item("桂林"));
        showItems.add(new Item("包头"));
        showItems.add(new Item("呼和浩特"));
        showItems.add(new Item("绵阳"));
        showItems.add(new Item("遂宁"));
        showItems.add(new Item("宜宾"));
        showItems.add(new Item("牡丹江"));
        showItems.add(new Item("五大连池"));
        showItems.add(new Item("张家界"));
        showItems.add(new Item("福州"));
        showItems.add(new Item("三亚"));
        showItems.add(new Item("海口"));
        showItems.add(new Item("鸡西"));
        showItems.add(new Item("沈阳"));
        showItems.add(new Item("长春"));
        showItems.add(new Item("唐山"));
        showItems.add(new Item("五台山"));
        showItems.add(new Item("哈尔滨"));
        showItems.add(new Item("大庆"));
        showItems.add(new Item("齐齐哈尔"));
        showItems.add(new Item("鞍山"));
        showItems.add(new Item("抚顺"));
        showItems.add(new Item("淄博"));
        showItems.add(new Item("德州"));
        showItems.add(new Item("烟台"));
        showItems.add(new Item("潍坊"));
        return showItems;
    }
}
