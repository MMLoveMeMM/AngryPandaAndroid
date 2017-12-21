package com.panda.org.mediumwrapper;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.panda.org.mediumwrapper.bean.WeatherBean;
import com.panda.org.mediumwrapper.utils.Json2Bean;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.Subscriber;

public class MediumActivity extends Activity {

    private final static String TAG="okhttp";
    private Button mBtn;
    private Button mBeanBtn;
    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium);

        mText=(TextView)findViewById(R.id.text);

        mBeanBtn=(Button)findViewById(R.id.bean);
        mBeanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        RetrofitClients.getInstance(MediumActivity.this,"http://v.juhe.cn/")
                                .getBeanWeather(new Subscriber<WeatherBean>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(WeatherBean weatherBean) {

                                Log.i(TAG,"response weatherBean : "+weatherBean.getResult().getToday().getCity());
                                mText.setText(weatherBean.getResult().getToday().getCity());

                            }
                        },"2", "深圳", "json", "3a8c66214031c01135f509434ae44b84");

                    }
                }).start();

            }
        });

        mBtn=(Button)findViewById(R.id.button);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        RetrofitClients
                                .getInstance(MediumActivity.this, "http://v.juhe.cn/")
                                .getWeather(new Subscriber<ResponseBody>() {
                                    @Override
                                    public void onCompleted() {

                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onNext(ResponseBody responseBody) {

                                        String body= null;
                                        try {
                                            body = responseBody.string();
                                            Log.i(TAG,"response : "+body);

                                            WeatherBean bean=Json2Bean.GetJsonToPersonBean(body);
                                            Log.i(TAG,"bean code : "+bean.getResultcode()+" city : "+bean.getResult().getToday().getCity());

                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }, "2", "深圳", "json", "3a8c66214031c01135f509434ae44b84");

                    }
                }).start();
            }

        });
    }

}
