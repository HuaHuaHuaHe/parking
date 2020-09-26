package com.swpu.hth.entity;

import java.util.Date;

public class OrderHisDO {
    //用户名
    private String username;
    //车牌号
    private String license;
    //预约车位
    private String spot_id;
    //预约时间
    private Date time_re;
    //进场时间
    private Date time_in;
    //离开时间
    private Date time_out;

    public String getUsername() {
        return username;
    }

    public OrderHisDO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getLicense() {
        return license;
    }

    public OrderHisDO setLicense(String license) {
        this.license = license;
        return this;
    }

    public String getSpot_id() {
        return spot_id;
    }

    public OrderHisDO setSpot_id(String spot_id) {
        this.spot_id = spot_id;
        return this;
    }

    public Date getTime_re() {
        return time_re;
    }

    public OrderHisDO setTime_re(Date time_re) {
        this.time_re = time_re;
        return this;
    }

    public Date getTime_in() {
        return time_in;
    }

    public OrderHisDO setTime_in(Date time_in) {
        this.time_in = time_in;
        return this;
    }

    public Date getTime_out() {
        return time_out;
    }

    public OrderHisDO setTime_out(Date time_out) {
        this.time_out = time_out;
        return this;
    }
}
