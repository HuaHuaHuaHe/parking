package com.swpu.hth.entity.DestributAlgorithm;

public class LatLng {
    public final double latitude;
    public final double longitude;
    public final double latitudeE6;
    public final double longitudeE6;

    public LatLng(double latitude, double longitude, double latitudeE6, double longitudeE6) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.latitudeE6 = latitudeE6;
        this.longitudeE6 = longitudeE6;
    }

    public LatLng(double var1, double var3) {
        if (!Double.isNaN(var1) && !Double.isNaN(var3) && !Double.isInfinite(var1) && !Double.isInfinite(var3)) {
            double var5 = var1 * 1000000.0D;
            double var7 = var3 * 1000000.0D;
            this.latitudeE6 = var5;
            this.longitudeE6 = var7;
            this.latitude = var5 / 1000000.0D;
            this.longitude = var7 / 1000000.0D;
        } else {
            this.latitudeE6 = 0.0D;
            this.longitudeE6 = 0.0D;
            this.latitude = 0.0D;
            this.longitude = 0.0D;
        }
    }

    public String toString(){
        String varl = new String("latitude:");
        varl = varl + this.latitude;
        varl = varl + ", longitude:";
        varl = varl + this.longitude;
        return varl;
    }

}
