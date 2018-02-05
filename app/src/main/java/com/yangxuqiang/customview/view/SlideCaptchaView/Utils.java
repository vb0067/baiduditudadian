package com.yangxuqiang.customview.view.SlideCaptchaView;

import android.content.Context;

/**
 * Created by yangxuqiang on 2018/2/2.
 */

public class Utils {
    public static int dp2px(Context ctx, float dip) {
        float density = ctx.getResources().getDisplayMetrics().density;
        return (int) (dip * density);
    }
}
