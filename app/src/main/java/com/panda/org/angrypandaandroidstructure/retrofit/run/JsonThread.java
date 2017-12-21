package com.panda.org.angrypandaandroidstructure.retrofit.run;

import android.util.Log;

import com.panda.org.angrypandaandroidstructure.retrofit.bean.JsonInfo;
import com.panda.org.angrypandaandroidstructure.retrofit.ref.JsonAPI;
import com.panda.org.angrypandaandroidstructure.retrofit.ref.QueryAPI;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by rd0348 on 2017/12/18 0018.
 */

public class JsonThread extends Thread {

    private final static String TAG="JsonThread";
    @Override
    public void run() {
        super.run();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://v.juhe.cn/").build();
        JsonAPI api = retrofit.create(JsonAPI.class);

        JsonInfo jsonInfo=new JsonInfo();
        jsonInfo.setId("24654613546543");
        jsonInfo.setKey("sdejnmlfgolsd8834j2kf");
        Call<ResponseBody> call = api.getUserInfo(jsonInfo);
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
