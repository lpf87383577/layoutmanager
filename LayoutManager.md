## recyclerView布局管理器
Android自带三个：

LinearLayoutManager 线性管理器（支持横竖方向）

GridLayoutManager  网格管理器

StaggeredGridLayoutManager 瀑布流管理器

#### 自定义布局管理器

##### 1.extends RecyclerView.LayoutManager
```python
public class CustomLinearLayoutManager extends RecyclerView.LayoutManager{
```

##### 2.重写generateDefaultLayoutParams方法，设置子布局的LayoutParams，一般设置WRAP_CONTENT就可以。
```python
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,RecyclerView.LayoutParams.WRAP_CONTENT);
    }
```
##### 3.重写onLayoutChildren 对子布局重新布局
```python

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
```

##### 4.设置可以滑动以及对滑动的限制
```python
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
```
### 辅助类
##### SnapHelper
用于辅助RecyclerView在滚动结束时将Item对齐到某个位置

>子类LinearSnapHelper

可以让RecyclerView滚动停止时相应的项目停留中间位置

>子类PagerSnapHelper

可以使RecyclerView像ViewPager一样的效果，一次只能滑一页，而且居中显示。

```python

```
```python

```