package com.panda.org.angrypandaandroidstructure.retrofit.run;

import com.panda.org.angrypandaandroidstructure.retrofit.ref.RxAPI;

import retrofit2.Retrofit;

/**
 * Created by rd0348 on 2017/12/18 0018.
 */

public class RxThread extends Thread {

    @Override
    public void run() {
        super.run();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://v.juhe.cn/").build();
        RxAPI api = retrofit.create(RxAPI.class);

        //api.updateInfo("",);

    }
}
