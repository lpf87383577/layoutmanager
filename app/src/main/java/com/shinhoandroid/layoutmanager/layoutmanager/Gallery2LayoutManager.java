package com.shinhoandroid.layoutmanager.layoutmanager;

import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.shinhoandroid.layoutmanager.BuildConfig;

/**
 * @author Liupengfei
 * @describe TODO
 * @date on 2019/8/13 14:07
 */
public class Gallery2LayoutManager extends RecyclerView.LayoutManager{

    int offestH = 0;

    int verticalScrollOffset = 0;

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,RecyclerView.LayoutParams.WRAP_CONTENT);
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
        View view0 = recycler.getViewForPosition(0);

        //测量子布局
        measureChildWithMargins(view0, 0, 0);
        int height = getDecoratedMeasuredHeight(view0);

        offestH = (getHeight()-height)/2;
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
        fillCover( -0);

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
        if (verticalScrollOffset + dy<-(getVerticalSpace()/2)){
            dy = -verticalScrollOffset-(getVerticalSpace()/2);
        }
        //滑动到底端
        if (verticalScrollOffset + dy>offestH-(getVerticalSpace()/2)){

            dy = offestH -verticalScrollOffset - (getVerticalSpace()/2);
        }
        //将滑动的距离叠加起来
        verticalScrollOffset += dy;

        // 调用该方法通知view在y方向上移动指定距离
        offsetChildrenVertical(-dy);

        fillCover( -dy);

        return dy;
    }

    private void fillCover( int scrollDelta) {

        if (mItemTransformer != null) {
            View child;
            //将距离中心点远的全部缩放，中心点的不变
            for (int i = 0; i < getChildCount(); i++) {
                child = getChildAt(i);
                //距离越远返回1或者-1，中心点返回0 ，mItemTransformer根据这一数值进行操作
                mItemTransformer.transformItem(this, child, calculateToCenterFraction(child, scrollDelta));
            }
        }

    }

    private float calculateToCenterFraction(View child, float pendingOffset) {
        //计算每个child距离中心点的距离
        int distance = (int) (child.getHeight() / 2 - pendingOffset + child.getTop() - getVerticalSpace()/2);
        int childLength = child.getHeight();
        return Math.max(-1.f, Math.min(1.f, distance * 1.f / childLength));
    }


    //计算RecyclerView的可用高度，除去上下Padding值
    private int getVerticalSpace() {
        return getHeight() - getPaddingBottom() - getPaddingTop();
    }


    private ItemTransformer mItemTransformer;


    public void setItemTransformer(ItemTransformer itemTransformer) {
        mItemTransformer = itemTransformer;
    }

    //每个itme的缩放
    public interface ItemTransformer {

        void transformItem(Gallery2LayoutManager layoutManager, View item, float fraction);
    }
}
