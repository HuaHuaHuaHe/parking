package com.swpu.hth.entity.DestributAlgorithm;

import java.util.Stack;

public class CarToPark {
    /*车主姓名*/
    private String username;
    /*车牌号*/
    private String license;
    /*撤销对CaToParkExpenseStack做出的随机排序*/
    private int stackTemp;
    /*用户的最优停车场为排序*/
    public Stack<CarToParkExpense> carToParkExpenseStack = new Stack<>();


    public int getStackTemp() {
        return stackTemp;
    }

    public CarToPark setStackTemp(int stackTemp) {
        this.stackTemp = stackTemp;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public CarToPark setUsername(String usaername) {
        this.username = usaername;
        return this;
    }

    public String getLicense() {
        return license;
    }

    public CarToPark setLicense(String license) {
        this.license = license;
        return this;
    }

    public Stack<CarToParkExpense> getCarToParkExpenseStack() {
        return carToParkExpenseStack;
    }
}
