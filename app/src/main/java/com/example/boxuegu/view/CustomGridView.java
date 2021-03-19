package com.example.boxuegu.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.IBinder;
import android.os.MessageQueue;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.viewpager.widget.ViewPager;

import com.example.boxuegu.R;
import com.example.boxuegu.aidl.ITestManager;
import com.example.boxuegu.utils.LogUtils;
import com.facebook.react.bridge.queue.MessageQueueThread;

import java.util.logging.Handler;

public class CustomGridView extends ViewGroup {
    public CustomGridView(Context context) {
        super(context);
    }

    public CustomGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        int count = attrs.getAttributeCount();
//        for (int i = 0; i < count; i++) {
//            LogUtils.i("属性： " + attrs.getAttributeName(i) + " zhi: " + attrs.getAttributeValue(i));
//        }

//        TypedArray ta =context.obtainStyledAttributes(attrs,R.styleable.CustomGridView);
//        ta.getInt(R.styleable.CustomGridView_layout_position,0);
//        ta.recycle();

    }

    public CustomGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int layoutWidth = 0, layoutHeight = 0, count = getChildCount();
        if (widthMode == MeasureSpec.EXACTLY) {
            layoutWidth = widthSize;
        } else {
            for (int i = 0; i < count; i++) {
                View v = getChildAt(i);
                int mWidth = v.getMeasuredWidth();
                int mHeight = v.getMeasuredHeight();
                if (mWidth > layoutWidth) layoutWidth = mWidth;
                if (mHeight > layoutHeight) layoutHeight = mHeight;
            }
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            layoutHeight = heightSize;
        }

        LogUtils.i("widthspec,heightspec", widthMode==MeasureSpec.AT_MOST, heightMode, layoutWidth, layoutHeight,widthSize,heightSize);
        setMeasuredDimension(layoutWidth, layoutHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();
        int childMeasuredWidth = 0;
        int childMeasuredHeight = 0;
        int distanceToLeft = 0;
        int distanceToTop = 0;
        int maxChildHeight = 0;
        int position = 0;
        LogUtils.i("数量", count);

        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
//            如果是GONE的话就不应该进行下面的处理
            if (child != null && child.getVisibility() != GONE) {

            }
            //此处不能使用getWidth和getHeight，这两个方法必须在onLayout执行完，才能正确获取宽高
            childMeasuredWidth = child.getMeasuredWidth();
            childMeasuredHeight = child.getMeasuredHeight();

            LogUtils.i("子元素宽高", childMeasuredWidth, childMeasuredHeight, getWidth(), getHeight(),child.getTop());
            position = ((CustomGridView.LayoutParams) child.getLayoutParams()).position;
            switch (position) {
                case LayoutParams.POSITION_CENTER:    // 中间
                    l = (getWidth() - childMeasuredWidth) / 2;
                    t = (getHeight() - childMeasuredHeight) / 2;
                    break;
                case LayoutParams.POSITION_LEFT_TOP:      // 左上方
                    l = 0;
                    t = 0;
                    break;
                case LayoutParams.POSITION_TOP:      // 上方
                    l = (getWidth() - childMeasuredWidth) / 2;
                    t = 0;
                    break;
                case LayoutParams.POSITION_RIGHT_TOP:     // 右上方
                    l = getWidth() - childMeasuredWidth;
                    t = 0;
                    break;
                case LayoutParams.POSITION_LEFT:      // 左方
                    l = 0;
                    t = (getHeight() - childMeasuredHeight) / 2;
                    break;
                case LayoutParams.POSITION_RIGHT:      // 右方
                    l = getWidth() - childMeasuredWidth;
                    t = (getHeight() - childMeasuredHeight) / 2;
                    break;
                case LayoutParams.POSITION_LEFT_BOTTOM:    // 左下角
                    l = 0;
                    t = getHeight() - childMeasuredHeight;
                    break;
                case LayoutParams.POSITION_RIGHT_BOTTOM:// 右下角
                    l = getWidth() - childMeasuredWidth;
                    t = getHeight() - childMeasuredHeight;
                    break;
                default:
                    break;
            }


//            if (distanceToLeft + childMeasuredWidth >= getWidth()) {
//                distanceToLeft = 0;
//                distanceToTop += maxChildHeight;
//                maxChildHeight = 0;
//            }
//            l = distanceToLeft;
//            t = distanceToTop;
//            r = l + childMeasuredWidth;
//            b = t + childMeasuredHeight;
//            distanceToLeft += childMeasuredWidth;
//            if (childMeasuredHeight > maxChildHeight) maxChildHeight = childMeasuredHeight + 100;

            child.layout(l, t, l + childMeasuredWidth, t + childMeasuredHeight);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public static final int POSITION_CENTER = 0; // 中间
        public static final int POSITION_LEFT_TOP = 1; // 左上方
        public static final int POSITION_RIGHT_TOP = 2; // 右上方
        public static final int POSITION_LEFT = 3; // 左方
        public static final int POSITION_RIGHT = 4; // 右方
        public static final int POSITION_LEFT_BOTTOM = 5; // 左下方
        public static final int POSITION_RIGHT_BOTTOM = 6; // 右下方
        public static final int POSITION_TOP = 7; // 上方
        public static final int POSITION_BOTTOM = 8; // 下角

        public int position = POSITION_LEFT_TOP;  // 默认我们的位置就是左上角

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.CustomGridView_Layout);
            //获取设置在子控件上的位置属性
            position = a.getInt(R.styleable.CustomGridView_Layout_layout_position, POSITION_LEFT_TOP);
            a.recycle();
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }


    }
}
