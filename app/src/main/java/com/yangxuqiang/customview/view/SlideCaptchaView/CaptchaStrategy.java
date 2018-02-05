package com.yangxuqiang.customview.view.SlideCaptchaView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by yangxuqiang on 2018/2/2.
 */

public abstract class CaptchaStrategy {

    private Context context;

    public CaptchaStrategy(Context context) {
        this.context=context;
    }
    protected Context getContext(){
        return context;
    }
    public abstract Path getBlockShape(int blockSize);
    public abstract PictureVertifyView.PositionInfo getBlockPostionInfo(int width, int height, int blockSize);

    public PictureVertifyView.PositionInfo getPositionInfoForSwipeBlock(int width, int height, int blockSize){
        return getBlockPostionInfo(width,height,blockSize);
    }

    /**
     * 获得缺块阴影的Paint
     */
    public abstract Paint getBlockShadowPaint();

    /**
     * 获得滑块图片的Paint
     */
    public abstract Paint getBlockBitmapPaint();

    /**
     * 装饰滑块图片，在绘制图片后执行，即绘制滑块前景
     */
    public void decoreateSwipeBlockBitmap(Canvas canvas, Path shape) {

    }
}
