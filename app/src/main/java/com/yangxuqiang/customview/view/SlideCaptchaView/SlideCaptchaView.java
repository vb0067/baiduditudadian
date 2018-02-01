package com.yangxuqiang.customview.view.SlideCaptchaView;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * author:yangxuqiang
 * userCode 328789
 * @date:2018/2/1
 * desc ：滑动拼图验证码
 */

public class SlideCaptchaView extends LinearLayout {
    public SlideCaptchaView(Context context) {
        super(context);
    }

    public SlideCaptchaView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SlideCaptchaView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        init();
    }

    private void init() {

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
