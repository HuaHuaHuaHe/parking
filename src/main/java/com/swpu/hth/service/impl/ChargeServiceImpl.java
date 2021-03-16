package com.swpu.hth.service.impl;

import com.swpu.hth.dao.ChargeDao;
import com.swpu.hth.entity.ChargeDO;
import com.swpu.hth.service.ChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChargeServiceImpl implements ChargeService {

    @Autowired
    private ChargeDao chargeDao;

    @Override
    public List<ChargeDO> queryAll() throws Exception {
        return chargeDao.queryAll();
    }

    @Override
    public List<ChargeDO> queryByName(String username) throws Exception {
        List<ChargeDO> chargeDOList = chargeDao.queryByName(username);
        System.out.println(chargeDOList.get(0).getTime_re()+"--"+"date");
        System.out.println(chargeDOList.get(0).getSpot_id()+"--"+"id");
        return chargeDOList;
    }
}
