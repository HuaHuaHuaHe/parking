package com.swpu.hth.service;


import com.swpu.hth.entity.StateDO;

public interface StateService {

    /**
     *
     * @param stateDO 车位数据
     * @throws Exception
     */
    void updateState(StateDO stateDO) throws Exception;

    StateDO querystate(int spot_id) throws Exception;
}
