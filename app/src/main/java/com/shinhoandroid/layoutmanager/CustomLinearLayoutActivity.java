package com.shinhoandroid.layoutmanager;

import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.shinhoandroid.layoutmanager.layoutmanager.Adapter;
import com.shinhoandroid.layoutmanager.layoutmanager.CustomLinearLayoutManager;
import com.shinhoandroid.layoutmanager.layoutmanager.Gallery2LayoutManager;
import com.shinhoandroid.layoutmanager.layoutmanager.Transformer2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomLinearLayoutActivity extends AppCompatActivity {
    List<Item> list;
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_linear_layout);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv);
        recyclerView.addItemDecoration(new SpaceItemDecoration(10));

        recyclerView.setLayoutManager(new CustomLinearLayoutManager());
        list = DataUtils.getList();
        adapter = new Adapter(this, list);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.SimpleCallback mCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT,
                ItemTouchHelper.START | ItemTouchHelper.END) {

            /**
             *        拖拽到新位置时候的回调方法
             * @param viewHolder  拖动的ViewHolder
             * @param target   目标位置的ViewHolder
             */
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int fromPosition = viewHolder.getAdapterPosition();
                int toPosition = target.getAdapterPosition();
                L.e("lpf--onMove--"+fromPosition+"--"+toPosition);
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        //交换元素位置
                        Collections.swap(list, i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(list, i, i - 1);
                    }
                }
                adapter.notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            /**
             *        将items滑出屏幕时的回调方法
             * @param viewHolder 滑动的ViewHolder
             * @param direction  滑动的方向
             */
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                L.e("lpf--onSwiped");
                int position = viewHolder.getAdapterPosition();
                list.remove(position);

                adapter.notifyItemRemoved(position);
            }


            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                    float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                //设置删除时items透明
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    final float alpha = 1 - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
                    viewHolder.itemView.setAlpha(alpha);
                    //viewHolder.itemView.setTranslationX(dX);
                }
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }


}
