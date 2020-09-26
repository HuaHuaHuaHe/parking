package com.swpu.hth.entity;

public class FingerDO {
    //ap的唯一mac地址
    private String mac;
    //信号强度
    private int rssi;
    //位置信息
    private float posX;
    private float posY;

    public String getMac() {
        return mac;
    }

    public FingerDO setMac(String mac) {
        this.mac = mac;
        return this;
    }

    public int getRssi() {
        return rssi;
    }

    public FingerDO setRssi(int rssi) {
        this.rssi = rssi;
        return this;
    }

    public float getPosX() {
        return posX;
    }

    public FingerDO setPosX(float posX) {
        this.posX = posX;
        return this;
    }

    public float getPosY() {
        return posY;
    }

    public FingerDO setPosY(float poxY) {
        this.posY = posY;
        return this;
    }
 }
