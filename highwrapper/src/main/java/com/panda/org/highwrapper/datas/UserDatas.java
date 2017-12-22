package com.panda.org.highwrapper.datas;

/**
 * Created by rd0348 on 2017/12/22 0022.
 * 数据载体
 */

public class UserDatas {

    private String httpresponse;

    public UserDatas(String http){
        httpresponse=http;
    }

    public String getHttpresponse() {
        return httpresponse;
    }

    public void setHttpresponse(String httpresponse) {
        this.httpresponse = httpresponse;
    }
}
