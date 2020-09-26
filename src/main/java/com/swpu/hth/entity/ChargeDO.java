package com.swpu.hth.entity;

public class ChargeDO {
    private String username;
    private float charge;

    public String getUsername() {
        return username;
    }

    public ChargeDO setUsername(String username) {
        this.username = username;
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
