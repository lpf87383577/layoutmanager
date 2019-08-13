package com.shinhoandroid.layoutmanager;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import java.util.Random;

/**
 * @author Liupengfei
 * @describe TODO
 * @date on 2019/8/9 17:04
 */
public class Item {
    public Drawable color;
    public String des;

    public Item(String des) {
        this.des = des;
        color = getBgColor();
    }

    private Drawable getBgColor() {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(4);
        drawable.setColor(Color.rgb(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255)));
        return drawable;
    }
}
