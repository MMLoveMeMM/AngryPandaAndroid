/**
  * Copyright 2017 bejson.com 
  */
package com.panda.org.highwrapper.bean.news;
import java.util.List;

/**
 * Auto-generated: 2017-12-21 19:38:2
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Result {

    private String stat;
    private List<Data> data;
    public void setStat(String stat) {
         this.stat = stat;
     }
     public String getStat() {
         return stat;
     }

    public void setData(List<Data> data) {
         this.data = data;
     }
     public List<Data> getData() {
         return data;
     }

}