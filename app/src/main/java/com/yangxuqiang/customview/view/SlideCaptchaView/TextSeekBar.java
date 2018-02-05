package com.yangxuqiang.customview.view.SlideCaptchaView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;

/**
 * Created by yangxuqiang on 2018/2/2.
 */

public class TextSeekBar extends AppCompatSeekBar {

    private Paint paint;

    public TextSeekBar(Context context) {
        super(context);
    }

    public TextSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(Utils.dp2px(context,14));
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#545454"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.drawText("向右滑动滑块完成拼图",getWidth()/2,getHeight()/2+paint.getTextSize()/2-4,paint);
        canvas.restore();
    }
}
