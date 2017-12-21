package com.panda.org.angrypandaandroidstructure.retrofit.run;

import android.util.Log;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import com.panda.org.angrypandaandroidstructure.retrofit.ref.QueryAPI;
/**
 * Created by rd0348 on 2017/12/18 0018.
 * 异步发起http请求
 */

public class SyncThread extends Thread {

    private final static String TAG="SyncThread";

    @Override
    public void run() {
        super.run();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://v.juhe.cn/").build();
        QueryAPI api = retrofit.create(QueryAPI.class);

        Call<ResponseBody> call = api.getWeather("2","深圳","json","3a8c66214031c01135f509434ae44b84");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //处理请求成功
                Log.i(TAG,"body : "+response.body().toString());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //处理请求失败
                Log.i(TAG,"http response failure !");
            }
        });

        call.cancel();


    }
}
