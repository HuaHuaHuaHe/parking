package com.swpu.hth.entity;

import java.util.Date;

public class OrderReDO {
    //订单编号
    private int id;
    //用户名
    private String username;
    //车牌号
    private String license;
    //预约时间
    private Date time_re;
    private float r1;
    private float tr;
    private double lat;
    private double lng;

    public int getId() {
        return id;
    }

    public OrderReDO setId(int id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public OrderReDO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getLicense() {
        return license;
    }

    public OrderReDO setLicense(String license) {
        this.license = license;
        return this;
    }

    public Date getTime_re() {
        return time_re;
    }

    public OrderReDO setTime_re(Date time_re) {
        this.time_re = time_re;
        return this;
    }

    public float getR1() {
        return r1;
    }

    public OrderReDO setR1(float r1) {
        this.r1 = r1;
        return this;
    }

    public float getTr() {
        return tr;
    }

    public OrderReDO setTr(float tr) {
        this.tr = tr;
        return this;
    }

    public double getLat() {
        return lat;
    }

    public OrderReDO setLat(double lat) {
        this.lat = lat;
        return this;
    }

    public double getLng() {
        return lng;
    }

    public OrderReDO setLng(double lng) {
        this.lng = lng;
        return this;
    }
}
