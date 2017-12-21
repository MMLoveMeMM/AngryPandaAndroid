package com.panda.org.mediumwrapper.utils;

import com.panda.org.mediumwrapper.bean.WeatherBean;

/**
 * Created by rd0348 on 2017/12/20 0020.
 */

public abstract class Json2BeanAbstract<T,F> {

    public abstract T GetJsonToWeatherBean(F json);

}
