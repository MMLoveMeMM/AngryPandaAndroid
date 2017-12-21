package com.panda.org.angrypandaandroidstructure.retrofit;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.panda.org.angrypandaandroidstructure.R;
import com.panda.org.angrypandaandroidstructure.retrofit.bean.ApiInfo;
import com.panda.org.angrypandaandroidstructure.retrofit.run.Okhttp1Thread;
import com.panda.org.angrypandaandroidstructure.retrofit.run.Okhttp2Thread;
import com.panda.org.angrypandaandroidstructure.retrofit.run.Okhttp3Thread;
import com.panda.org.angrypandaandroidstructure.retrofit.run.Okhttp4Thread;
import com.panda.org.angrypandaandroidstructure.retrofit.run.OkhttpThread;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitActivity extends Activity {

    private Button mBtn;
    private Button mTaoBaoBtn;
    private Button mBeanBtn;
    private Button mJuHeBtn;
    private Button mOkhttpBtn;
    private Button mOkhttpExBtn;

    private Call<ResponseBody> call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        mOkhttpExBtn=(Button)findViewById(R.id.okhttpex);
        mOkhttpExBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Okhttp4Thread(RetrofitActivity.this).start();
            }
        });

        mOkhttpBtn=(Button)findViewById(R.id.okhttp);
        mOkhttpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Okhttp3Thread().start();
            }
        });

        mJuHeBtn=(Button)findViewById(R.id.juhe);
        mJuHeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Retrofit retrofit = new Retrofit.Builder()
                                //这里建议：- Base URL: 总是以/结尾；- @Url: 不要以/开头
                                .baseUrl("http://v.juhe.cn/")
                                .build();
                        JuHeService apiStores = retrofit.create(JuHeService.class);
                        // 自动urlencode
                        call = apiStores.getWeather("2", "苏州","json","3a8c66214031c01135f509434ae44b84");

                        try {
                            Response<ResponseBody> bodyResponse = call.execute();
                            String body = bodyResponse.body().string();//获取返回体的字符串
                            Log.i("wxl", "body=" + body);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        });

        mBeanBtn=(Button)findViewById(R.id.bean);
        mBeanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Retrofit retrofit = new Retrofit.Builder()
                                //这里建议：- Base URL: 总是以/结尾；- @Url: 不要以/开头
                                .baseUrl("http://WuXiaolong.me/")
                                .build();
                        BeanService apiStores = retrofit.create(BeanService.class);
                        ApiInfo apiInfo=new ApiInfo();
                        ApiInfo.ApiInfoBean infoBean=apiInfo.new ApiInfoBean();
                        infoBean.setApiKey("666");
                        infoBean.setApiName("sdfjldfs");
                        apiInfo.setApiInfo(infoBean);
                        call = apiStores.getCarType(apiInfo);

                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                String body = null;//获取返回体的字符串
                                try {
                                    body = response.body().string();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                Log.i("wxl", "get=" + body);
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                            }

                        });
                    }
                }).start();



            }
        });

        mTaoBaoBtn=(Button)findViewById(R.id.taobao);
        mTaoBaoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Retrofit retrofit = new Retrofit.Builder()
                                //这里建议：- Base URL: 总是以/结尾；- @Url: 不要以/开头
                                .baseUrl("http://ip.taobao.com/")
                                .build();
                        PandaService apiStores = retrofit.create(PandaService.class);
                        call = apiStores.getWeather("202.202.33.33");

                        try {
                            Response<ResponseBody> bodyResponse = call.execute();
                            String body = bodyResponse.body().string();//获取返回体的字符串
                            Log.i("wxl", "body=" + body);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();

            }
        });

        mBtn=(Button)findViewById(R.id.button);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Response<ResponseBody> bodyResponse = call.execute();
                            String body = bodyResponse.body().string();//获取返回体的字符串
                            Log.i("wxl", "body=" + body);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();*/

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                try {
                                    Log.i("wxl", "response=" + response.body().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Log.i("wxl", "onFailure=" + t.getMessage());
                            }

                        });

                    }
                }).start();
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                //这里建议：- Base URL: 总是以/结尾；- @Url: 不要以/开头
                .baseUrl("http://www.weather.com.cn/")
                .build();
        PandaService apiStores = retrofit.create(PandaService.class);
        call = apiStores.getWeather("101010100");

    }
}
