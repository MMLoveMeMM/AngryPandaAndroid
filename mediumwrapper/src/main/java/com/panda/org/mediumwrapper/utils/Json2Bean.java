package com.panda.org.mediumwrapper.utils;

import com.google.gson.Gson;
import com.panda.org.mediumwrapper.bean.WeatherBean;

/**
 * Created by rd0348 on 2017/12/20 0020.
 */

public class Json2Bean<T,F> extends Json2BeanAbstract<WeatherBean,String>{
    private static Gson gson = new Gson();

    @Override
    public WeatherBean GetJsonToWeatherBean(String json) {
        WeatherBean personBeanArrayList =
                gson.fromJson(json, WeatherBean.class);
        return personBeanArrayList;
    }

    public static WeatherBean GetJsonToPersonBean(String jsonString){
        WeatherBean personBeanArrayList =
                gson.fromJson(jsonString, WeatherBean.class);
        return personBeanArrayList;
    }

    public T getJsonFromBean(F json){
        T personBeanArrayList =
                (T) gson.fromJson(json.toString(), WeatherBean.class);
        return personBeanArrayList;
    }

}
