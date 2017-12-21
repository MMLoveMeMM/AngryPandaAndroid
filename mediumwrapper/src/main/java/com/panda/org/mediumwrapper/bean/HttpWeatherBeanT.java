package com.panda.org.mediumwrapper.bean;

/**
 * Created by rd0348 on 2017/12/20 0020.
 */

public class HttpWeatherBeanT<T> {

    private String resultcode;
    private String reason;
    private T result;
    private int error_code;
    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }
    public String getResultcode() {
        return resultcode;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getReason() {
        return reason;
    }

    public void setResult(T result) {
        this.result = result;
    }
    public T getResult() {
        return result;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }
    public int getError_code() {
        return error_code;
    }

}
