package com.panda.org.angrypandaandroidstructure.retrofit.run;

import android.util.Log;

import com.panda.org.angrypandaandroidstructure.retrofit.ref.QueryAPI;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by rd0348 on 2017/12/18 0018.
 * 多次请求
 * 下面请求如果失败,重新发送请求,超过三次失败以后不再发送请求
 * Call正常只能够调用一次
 */

public class MultiAsyncGetThread extends Thread {

    private final static String TAG="MultiAsyncGetThread";

    private boolean looping=false;
    @Override
    public void run() {
        super.run();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://v.juhe.cn/").build();
        QueryAPI api = retrofit.create(QueryAPI.class);
        Call<ResponseBody> call = api.getWeather("2","深圳","json","3a8c66214031c01135f509434ae44b84");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //请求成功
                looping=false;
                Log.i(TAG,"body : "+response.body().toString());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //请求失败
                looping=true;
            }
        });

        int count=0;
        while(looping && count<3){

            /*
            * 通过克隆的方式再次调用
            * */
            call.clone().enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    //请求成功
                    looping=false;
                    Log.i(TAG,"body : "+response.body().toString());
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    //请求失败
                    looping=true;
                }
            });
            count++;
        }


    }
}
