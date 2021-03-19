package com.example.boxuegu.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.boxuegu.utils.LogUtils;

public class CustomButton extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        LogUtils.i("测量子View");
        setMeasuredDimension(300, 300);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(5);

        float[] pts = {50, 100, 100, 200, 200, 300, 300, 400};
        paint.setColor(Color.RED);
        canvas.drawLines(pts, paint);

        paint.setColor(Color.BLUE);
        canvas.drawLines(pts, 1, 4, paint);
    }

    public CustomButton(Context context) {
        super(context);
        LogUtils.i("创建子View");


    }

    public CustomButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LogUtils.i("创建子View11");


    }

    public CustomButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LogUtils.i("创建子View22");

    }

    public CustomButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        LogUtils.i("创建子View333");

    }
}
