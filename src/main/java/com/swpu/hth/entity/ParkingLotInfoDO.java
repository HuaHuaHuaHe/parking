package com.swpu.hth.entity;

public class ParkingLotInfoDO {
    /*停车场名字*/
    private String parking_name;
    /*经纬度*/
    private double lat;
    private double lng;
    /*费率*/
    private float parking_fee;
    /*车位总数*/
    private int n_total;
    /*占用车位数*/
    private int n_occupy;

    private float rate_parking;

    public float getRate_parking() {
        return rate_parking;
    }

    public ParkingLotInfoDO setRate_parking(float rate_parking) {
        this.rate_parking = rate_parking;
        return this;
    }

    public String getParking_name() {
        return parking_name;
    }

    public ParkingLotInfoDO setParking_name(String parking_name) {
        this.parking_name = parking_name;
        return this;
    }

    public double getLat() {
        return lat;
    }

    public ParkingLotInfoDO setLat(double lat) {
        this.lat = lat;
        return this;
    }

    public double getLng() {
        return lng;
    }

    public ParkingLotInfoDO setLng(double lng) {
        this.lng = lng;
        return this;
    }

    public float getParking_fee() {
        return parking_fee;
    }

    public ParkingLotInfoDO setParking_fee(float parking_fee) {
        this.parking_fee = parking_fee;
        return this;
    }

    public int getN_total() {
        return n_total;
    }

    public ParkingLotInfoDO setN_total(int n_total) {
        this.n_total = n_total;
        return this;
    }

    public int getN_occupy() {
        return n_occupy;
    }

    public ParkingLotInfoDO setN_occupy(int n_occupy) {
        this.n_occupy = n_occupy;
        return this;
    }
}
