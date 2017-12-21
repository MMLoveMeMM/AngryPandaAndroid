package com.panda.org.highwrapper.bean.news;

/**
 * Created by rd0348 on 2017/12/21 0021.
 */

public class BaseNewsBean<T> {
    private String reason;
    private T result;
    private int error_code;
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
