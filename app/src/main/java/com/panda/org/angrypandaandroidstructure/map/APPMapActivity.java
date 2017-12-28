package com.panda.org.angrypandaandroidstructure.map;

import android.app.Activity;
import android.os.Bundle;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.panda.org.angrypandaandroidstructure.R;

public class APPMapActivity extends Activity {

    // 百度地图控件
    private MapView mMapView = null;
    // 百度地图对象
    private BaiduMap mBaiduMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_app_map);

        mMapView = (MapView) findViewById(R.id.bmapview);
        mBaiduMap = mMapView.getMap();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    /*
    * 切换地图
    * */
    private void setSwitchMapType()
    {
        //普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        //卫星地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
    }
    /*
    * 开启实时交通图
    * */
    private void enableTraffic()
    {
        //开启交通图
        mBaiduMap.setTrafficEnabled(true);
    }
    /*
    * 显示热力图
    * */
    private void enableHotMap()
    {
        //开启热力图
        mBaiduMap.setBaiduHeatMapEnabled(true);
    }

}
