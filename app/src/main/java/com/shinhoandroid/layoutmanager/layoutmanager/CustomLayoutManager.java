package com.shinhoandroid.layoutmanager.layoutmanager;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.shinhoandroid.layoutmanager.L;

/**
 * @author Liupengfei
 * @describe TODO
 * @date on 2019/8/9 17:02
 */
public class CustomLayoutManager  extends RecyclerView.LayoutManager {

    //竖直方向累计滑动距离
    private int verticalScrollOffset;
    //布局定位用到的高度，最后得到的是recyclerView的高度
    private int offsetH = 0;
    private int leftMargin, rightMargin;

    //就是RecyclerView 子 item 的 LayoutParameters，一般包裹内容即可：
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        //getItemCount是数据的个数
        //getChildCount是子view的个数，一般数据较少或者没有使用缓存的话getItemCount = getChildCount
        if (getItemCount() == 0) {
            //把子布局detach掉
            detachAndScrapAttachedViews(recycler);
            return;
        }
        //跳过preLayout，preLayout主要用于支持动画
        if (state.isPreLayout()) {
            return;
        }
        //布局之前，先把所有item从RecyclerView中detach,，放入到Scrap缓存中
        detachAndScrapAttachedViews(recycler);

        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)
                recycler.getViewForPosition(0).getLayoutParams();

        leftMargin = params.leftMargin;
        rightMargin = params.rightMargin;

        layoutItem(recycler);
    }

    private void layoutItem(RecyclerView.Recycler recycler) {
        offsetH = getPaddingTop();

        //每一等份的宽度
        int width = (Resources.getSystem().getDisplayMetrics().widthPixels
                - getRightDecorationWidth(recycler.getViewForPosition(0))
                - getLeftDecorationWidth(recycler.getViewForPosition(0))
                - leftMargin - rightMargin) / 3;
        for (int i = 0; i < getItemCount(); i++) {
            //这里就是从缓存里面取出
            View view = recycler.getViewForPosition(i);
            //将View加入到RecyclerView中
            addView(view); // 因为detach过所以重新添加
            measureChildWithMargins(view, 0, 0);
            int height = getDecoratedMeasuredHeight(view) + getBottomDecorationHeight(view);

            switch (i % 6) {
                case 0:
                    //给子布局设置位置
                    layoutDecoratedWithMargins(view, 0, offsetH, 2 * width, offsetH + height);
                    break;
                case 1:
                    layoutDecoratedWithMargins(view, 2 * width, offsetH,
                            3 * width, offsetH + height / 2);
                    break;
                case 2:
                    layoutDecoratedWithMargins(view, 2 * width, offsetH + height / 2,
                            3 * width, offsetH + height);
                    offsetH = offsetH + height;
                    break;
                case 3:
                    layoutDecoratedWithMargins(view, 0, offsetH,
                            width, offsetH + height / 2);
                    break;
                case 4:
                    layoutDecoratedWithMargins(view, 0, offsetH + height / 2,
                            width, offsetH + height);
                    break;
                case 5:
                    layoutDecoratedWithMargins(view, width, offsetH,
                            3 * width, offsetH + height);
                    offsetH = offsetH + height;
                    break;
                default:
                    layoutDecoratedWithMargins(view, 0, offsetH, 3 * width,
                            offsetH + height);
                    offsetH = offsetH + height;
                    break;
            }
        }
    }

    //竖直方向是否可以滑动
    @Override
    public boolean canScrollVertically() {
        return true;
    }


    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        //列表向下滚动dy为正，列表向上滚动dy为负，这点与Android坐标系保持一致。
        //实际要滑动的距离
        L.e("offsetH--"+offsetH +"--" +getVerticalSpace());
        L.e("verticalScrollOffset--"+verticalScrollOffset+"--"+dy);
        int travel = dy;
        //如果滑动到最顶部verticalScrollOffset（累计移动的距离）dy每次移动距离（上滑为正，下滑为负）
        if (verticalScrollOffset + dy < 0) {
            travel = -verticalScrollOffset;
        } else if (verticalScrollOffset + dy > offsetH - getVerticalSpace()) {//如果滑动到最底部
            travel = offsetH - getVerticalSpace() - verticalScrollOffset;
        }
        //将竖直方向的偏移量+travel
        verticalScrollOffset += travel;
        // 调用该方法通知view在y方向上移动指定距离
        offsetChildrenVertical(-travel);

        return travel;
    }

    private int getVerticalSpace() {
        //计算RecyclerView的可用高度，除去上下Padding值
        return getHeight() - getPaddingBottom() - getPaddingTop();
    }
}
