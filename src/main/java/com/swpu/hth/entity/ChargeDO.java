package com.swpu.hth.entity;

import java.util.Date;

public class ChargeDO {
    private String username;
    private String license;
    private Integer spot_id;
    private Date time_re;
    private Date time_out;
    private Float charge;

    public String getUsername() {
        return username;
    }

    public ChargeDO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getLicense() {
        return license;
    }

    public ChargeDO setLicense(String license) {
        this.license = license;
        return this;
    }

    public int getSpot_id() {
        return spot_id;
    }

    public ChargeDO setSpot_id(int spot_id) {
        this.spot_id = spot_id;
        return this;
    }

    public Date getTime_re() {
        return time_re;
    }

    public ChargeDO setTime_re(Date time_re) {
        this.time_re = time_re;
        return this;
    }

    public Date getTime_out() {
        return time_out;
    }

    public ChargeDO setTime_out(Date time_out) {
        this.time_out = time_out;
        return this;
    }

    public float getCharge() {
        return charge;
    }

    public ChargeDO setCharge(float charge) {
        this.charge = charge;
        return this;
    }
}
