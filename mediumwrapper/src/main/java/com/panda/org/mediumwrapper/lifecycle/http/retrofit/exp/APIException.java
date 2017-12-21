package com.panda.org.mediumwrapper.lifecycle.http.retrofit.exp;

/**
 * Created by rd0348 on 2017/12/20 0020.
 */

public class APIException extends Exception {
    public int code;
    public String message;

    public APIException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
