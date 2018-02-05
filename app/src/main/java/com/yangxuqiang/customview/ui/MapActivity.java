package com.yangxuqiang.customview.ui;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.baidu.mapapi.cloud.CloudListener;
import com.baidu.mapapi.cloud.CloudManager;
import com.baidu.mapapi.cloud.CloudRgcResult;
import com.baidu.mapapi.cloud.CloudSearchResult;
import com.baidu.mapapi.cloud.DetailSearchResult;
import com.baidu.mapapi.cloud.LocalSearchInfo;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.yangxuqiang.customview.R;
import com.yangxuqiang.customview.base.BaseActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by yangxuqiang on 2018/2/2.
 */

public class MapActivity extends Activity {

    @SuppressWarnings("unused")
    private static final String LTAG = MapActivity.class.getSimpleName();
    private MapView mMapView;
    FrameLayout layout;
    private boolean mEnableCustomStyle = true;
    private static final int OPEN_ID = 0;
    private static final int CLOSE_ID = 1;
    //用于设置个性化地图的样式文件
    // 精简为1套样式模板:
    // "custom_config_dark.json"
    private static String PATH = "custom_config_dark.json";
    private CloudManager mCloudManager;
    private GeoCoder geoCoder;
    private long startTime;
    private boolean end;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapStatus.Builder builder = new MapStatus.Builder();
        LatLng center = new LatLng(31, 121); // 默认 天安门
        float zoom = 11.0f; // 默认 11级

        builder.target(center).zoom(zoom);

        startTime = System.currentTimeMillis();


        BaiduMapOptions baiduMapOptions = new BaiduMapOptions();
        mMapView = new MapView(this,baiduMapOptions );
        initView(this);
        setContentView(layout);

        BaiduMap map = mMapView.getMap();
        map.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

        MapView.setMapCustomEnable(true);

//        TextView textView = new TextView(this);
//        textView.setText("北京北京北京北京北京");
//        textView.destroyDrawingCache();
//        textView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.UNSPECIFIED);
//        textView.layout(0, 0, textView.getMeasuredWidth(), textView.getMeasuredHeight());
//        textView.setDrawingCacheEnabled(true);
//        textView.setBackgroundResource(R.drawable.right);

//        Bitmap drawingCache = textView.getDrawingCache();

//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(center);
//        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(drawingCache));
//
//        map.addOverlay(markerOptions);

//        search();
        ActivityManager systemService = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        int largeMemoryClass = systemService.getLargeMemoryClass();
        new Thread(){
            @Override
            public void run() {
                super.run();
                for (int i=0;i<1000;i++){
                    addressConvertID("德邦总部"+i,"上海市青浦区徐泾镇明珠路"+(1018+i*50)+"号","上海市");
                    if(i==99){
                        end=true;
                    }
                }
            }
        }.start();


    }

    private void addressConvertID(final String title, String address, String city){
        geoCoder = GeoCoder.newInstance();
        OnGetGeoCoderResultListener listener=new OnGetGeoCoderResultListener(){

            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
                synchronized (this){
                    String address = geoCodeResult.getAddress();
                    LatLng location = geoCodeResult.getLocation();


                    TextView textView = new TextView(MapActivity.this);
                    textView.setText(title);
                    textView.setTextColor(Color.parseColor("#ff0000"));
                    textView.destroyDrawingCache();
                    textView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.UNSPECIFIED);
                    textView.layout(0, 0, textView.getMeasuredWidth(), textView.getMeasuredHeight());
                    textView.setDrawingCacheEnabled(true);
                    textView.setBackgroundColor(Color.parseColor("#ffffff"));

                    Bitmap drawingCache = textView.getDrawingCache();

                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(location);
                    markerOptions.icon(BitmapDescriptorFactory.fromBitmap(drawingCache));
                    BaiduMap map = mMapView.getMap();
                    map.addOverlay(markerOptions);


                    if(end){
                        Log.i("标记时间:******",(System.currentTimeMillis()-startTime)+" ");
                    }
                }

            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

                LatLng location = reverseGeoCodeResult.getLocation();
                List<PoiInfo> poiList = reverseGeoCodeResult.getPoiList();
            }
        };
        geoCoder.setOnGetGeoCodeResultListener(listener);
        geoCoder.geocode(new GeoCodeOption()
                .address(address)
                .city(city));

    }

    // 初始化View
    private void initView(Context context) {
        layout = new FrameLayout(this);
        layout.addView(mMapView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // activity 暂停时同时暂停地图控件
        mMapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // activity 恢复时同时恢复地图控件
        mMapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mCloudManager!=null){
            mCloudManager.unregisterListener();
            mCloudManager=null;
        }
        if(geoCoder!=null){
            geoCoder.destroy();
        }
        // activity 销毁时同时销毁地图控件
        MapView.setMapCustomEnable(false);
        mMapView.onDestroy();

    }

}
