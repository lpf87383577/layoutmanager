package com.shinhoandroid.layoutmanager.layoutmanager;

import android.view.View;

import com.shinhoandroid.layoutmanager.L;

/**
 * @author Liupengfei
 * @describe TODO
 * @date on 2019/8/13 15:03
 */
public class Transformer2 implements Gallery2LayoutManager.ItemTransformer {
    @Override
    public void transformItem(Gallery2LayoutManager layoutManager, View item, float fraction) {
        L.e("fraction--"+item.toString()+"--"+fraction);
        //以圆心进行缩放
        item.setPivotX(item.getWidth() / 2.0f);
        item.setPivotY(item.getHeight() / 2.0f);
        float scale = 1 - 0.3f * Math.abs(fraction);
        item.setScaleX(scale);
        item.setScaleY(scale);
    }
}
