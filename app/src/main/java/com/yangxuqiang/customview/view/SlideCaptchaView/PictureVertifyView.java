package com.yangxuqiang.customview.view.SlideCaptchaView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by yangxuqiang on 2018/2/2.
 */

public class PictureVertifyView extends AppCompatImageView {

    private Paint shadowPaint;
    private Paint bitmapPaint;
    private DefaultCaptchaStrategy defaultCaptchaStrategy;
    private int blockSize=50;
    private PositionInfo positionInfo;
    private int mMode;

    public PictureVertifyView(Context context) {
        super(context);
    }

    public PictureVertifyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PictureVertifyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        defaultCaptchaStrategy = new DefaultCaptchaStrategy(context);
        shadowPaint = defaultCaptchaStrategy.getBlockShadowPaint();
        bitmapPaint = defaultCaptchaStrategy.getBlockBitmapPaint();
        setLayerType(View.LAYER_TYPE_SOFTWARE, bitmapPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(positionInfo==null){
            positionInfo = defaultCaptchaStrategy.getBlockPostionInfo(getWidth(), getHeight(), blockSize);
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }

    public static class PositionInfo {

        int left;
        int top;

        public PositionInfo(int left, int top) {
            this.left = left;
            this.top = top;
        }
    }
}
