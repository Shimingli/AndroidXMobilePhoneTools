package com.lsm.net.retrofit;


import com.lsm.net.entities.BaseOneBean;

public class SMResponse<T> extends BaseOneBean {


    public T result;
    public String reason;
    public int error_code;

    @Override
    public String[] uniqueKey() {
        return new String[]{""};
    }
}
