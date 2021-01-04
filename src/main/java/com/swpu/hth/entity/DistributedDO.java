package com.swpu.hth.entity;

public class DistributedDO {
    private String username;
    private String license;
    private String parking_name;
    private double lat;
    private double lng;

    public String getUsername() {
        return username;
    }

    public DistributedDO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getLicense() {
        return license;
    }

    public DistributedDO setLicense(String license) {
        this.license = license;
        return this;
    }

    public String getParking_name() {
        return parking_name;
    }

    public DistributedDO setParking_name(String parking_name) {
        this.parking_name = parking_name;
        return this;
    }

    public double getLat() {
        return lat;
    }

    public DistributedDO setLat(double lat) {
        this.lat = lat;
        return this;
    }

    public double getLng() {
        return lng;
    }

    public DistributedDO setLng(double lng) {
        this.lng = lng;
        return this;
    }
}
