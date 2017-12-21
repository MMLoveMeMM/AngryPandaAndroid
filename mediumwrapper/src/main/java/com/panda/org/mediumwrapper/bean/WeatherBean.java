/**
  * Copyright 2017 bejson.com 
  */
package com.panda.org.mediumwrapper.bean;

/**
 * Auto-generated: 2017-12-20 10:52:1
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class WeatherBean {

    private String resultcode;
    private String reason;
    private Result result;
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

    public void setResult(Result result) {
         this.result = result;
     }
     public Result getResult() {
         return result;
     }

    public void setError_code(int error_code) {
         this.error_code = error_code;
     }
     public int getError_code() {
         return error_code;
     }

}