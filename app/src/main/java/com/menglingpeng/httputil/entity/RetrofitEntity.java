package com.menglingpeng.httputil.entity;

import java.util.List;

/**
 * 直接请求返回数据格式
 * Created by mengdroid on 2018/3/29.
 */

public class RetrofitEntity {

    private int ret;
    private String msg;
    private List<SubjectResulte> data;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<SubjectResulte> getData() {
        return data;
    }

    public void setData(List<SubjectResulte> data) {
        this.data = data;
    }
}
