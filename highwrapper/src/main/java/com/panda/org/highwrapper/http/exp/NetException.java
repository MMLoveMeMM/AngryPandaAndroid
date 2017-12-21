package com.panda.org.highwrapper.http.exp;

/**
 * Created by rd0348 on 2017/12/21 0021.
 * 這個自定義的異常應用于請求response結果分離,
 * 請求失敗的時候拋出的http請求錯誤信息
 */

public class NetException extends Exception {
    public int code;
    public String message;

    public NetException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}