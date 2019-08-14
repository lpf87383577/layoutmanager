package com.shinhoandroid.layoutmanager.layoutmanager;

import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * @author Liupengfei
 * @describe 自定义竖向布局
 * @date on 2019/8/12 14:58
 */
public class CustomLinearLayoutManager extends RecyclerView.LayoutManager{

    int offestH = 0;

    int verticalScrollOffset = 0;


    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    //模拟线性布局竖直方向叠加
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        //getItemCount是数据的个数
        //跳过preLayout，preLayout主要用于支持动画
        if (getItemCount()==0 || state.isPreLayout()){
            return;
        }
        //将布局移除放在缓存中
        detachAndScrapAttachedViews(recycler);
        offestH = getPaddingTop();
        for(int i = 0;i<getItemCount();i++){

            View view = recycler.getViewForPosition(i);
            //将View加入到RecyclerView中
            addView(view); // 因为detach过所以重新添加
            //测量子布局
            measureChildWithMargins(view, 0, 0);
            //获取子布局的宽高
            int dHeight = getDecoratedMeasuredHeight(view);
            int dWidth = getDecoratedMeasuredWidth(view);
            //子布局定位
            layoutDecoratedWithMargins(view,0,offestH,dWidth,offestH+dHeight);

            offestH+=dHeight;

        }
    }

    //竖直方向可以滑动
    @Override
    public boolean canScrollVertically() {
        return true;
    }

    //滑动操作
    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {

        //滑动到顶端的时候
        if (verticalScrollOffset + dy<0){
            dy = -verticalScrollOffset;
        }
        //滑动到底端
        if (verticalScrollOffset + dy>offestH-getVerticalSpace()){

            dy = offestH - getVerticalSpace() -verticalScrollOffset;
        }
        //将滑动的距离叠加起来
        verticalScrollOffset += dy;

        // 调用该方法通知view在y方向上移动指定距离
        offsetChildrenVertical(-dy);

        return dy;
    }
    //计算RecyclerView的可用高度，除去上下Padding值
    private int getVerticalSpace() {
        return getHeight() - getPaddingBottom() - getPaddingTop();
    }
}
