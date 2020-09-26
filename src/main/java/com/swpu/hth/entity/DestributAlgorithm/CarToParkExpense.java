package com.swpu.hth.entity.DestributAlgorithm;

public class CarToParkExpense {
    private String parkingLotName;
    private float expense;

    public String getParkingLotName() {
        return parkingLotName;
    }

    public CarToParkExpense setParkingLotName(String parkingLotName) {
        this.parkingLotName = parkingLotName;
        return this;
    }

    public float getExpense() {
        return expense;
    }

    public CarToParkExpense setExpense(float expense) {
        this.expense = expense;
        return this;
    }
}
