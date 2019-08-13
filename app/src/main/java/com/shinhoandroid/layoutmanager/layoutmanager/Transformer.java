package com.shinhoandroid.layoutmanager.layoutmanager;

import android.view.View;

import com.shinhoandroid.layoutmanager.L;


public class Transformer implements GalleryLayoutManager.ItemTransformer {
    @Override
    public void transformItem(GalleryLayoutManager layoutManager, View item, float fraction) {
        L.e("fraction--"+item.toString()+"--"+fraction);
        //以圆心进行缩放
        item.setPivotX(item.getWidth() / 2.0f);
        item.setPivotY(item.getHeight() / 2.0f);
        float scale = 1 - 0.3f * Math.abs(fraction);
        item.setScaleX(scale);
        item.setScaleY(scale);
    }
}
