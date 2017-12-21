package com.panda.org.angrypandaandroidstructure.retrofit.bean;

/**
 * Created by rd0348 on 2017/12/18 0018.
 */

public class ApiInfo {

    private ApiInfoBean apiInfo;
    public ApiInfoBean getApiInfo() {
        return apiInfo;
    }

    public void setApiInfo(ApiInfoBean apiInfo) {
        this.apiInfo = apiInfo;
    }

    public class ApiInfoBean {
        private String apiName;
        private String apiKey;
        //省略get和set方法


        public String getApiName() {
            return apiName;
        }

        public void setApiName(String apiName) {
            this.apiName = apiName;
        }

        public String getApiKey() {
            return apiKey;
        }

        public void setApiKey(String apiKey) {
            this.apiKey = apiKey;
        }
    }

}
