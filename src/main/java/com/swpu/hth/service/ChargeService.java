package com.swpu.hth.service;

import com.swpu.hth.entity.ChargeDO;

import java.util.List;

public interface ChargeService {

    List<ChargeDO> queryAll() throws Exception;

    /**
     *
     * @param username
     * @return
     * @throws Exception
     */
    List<ChargeDO> queryByName(String  username) throws Exception;
}
