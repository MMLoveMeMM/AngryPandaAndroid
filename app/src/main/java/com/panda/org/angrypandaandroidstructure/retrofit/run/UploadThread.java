package com.panda.org.angrypandaandroidstructure.retrofit.run;

import android.util.Log;

import com.panda.org.angrypandaandroidstructure.retrofit.ref.UploadAPI;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by rd0348 on 2017/12/18 0018.
 */

public class UploadThread extends Thread {

    private final static String TAG="UploadThread";
    @Override
    public void run() {
        super.run();

        File file = new File("");
        // 创建 RequestBody，用于封装构建RequestBody
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // MultipartBody.Part  和后端约定好Key，这里的partName是用image
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);

        // 添加描述
        String descriptionString = "上传文件信息描述";
        RequestBody description =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), descriptionString);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://v.juhe.cn/").build();
        UploadAPI api = retrofit.create(UploadAPI.class);
        // 执行请求
        Call<ResponseBody> call = api.upload(description, body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                Log.v(TAG, "success");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

}
