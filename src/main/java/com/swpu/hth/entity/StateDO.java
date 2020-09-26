package com.swpu.hth.entity;

public class StateDO {
    //车位号
    private int spot_id;

    //状态
    private int state;

    public int getSpot_id() {
        return spot_id;
    }

    public StateDO setSpot_id(int spot_id) {
        this.spot_id = spot_id;
        return this;
    }

    public int getState() {
        return state;
    }

    public StateDO setState(int state) {
        this.state = state;
        return this;
    }
}
