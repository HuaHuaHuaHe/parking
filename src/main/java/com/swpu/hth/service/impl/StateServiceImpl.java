package com.swpu.hth.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.swpu.hth.dao.StateDao;
import com.swpu.hth.entity.StateDO;
import com.swpu.hth.service.StateService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class StateServiceImpl implements StateService {

    @Autowired
    private StateDao stateDao;


    @Autowired
    @Qualifier("stateServiceSwpuImpl")
    private StateService stateServiceSwpu;

    @Override
    @DS(value = "all-ds")
    @GlobalTransactional
    public synchronized void updateState(StateDO stateDO) throws Exception{
        stateDao.update(stateDO); //更新总数据库里的state_swpu
        stateServiceSwpu.updateState(stateDO);
    }

    @Override
    public StateDO querystate(int spot_id) throws Exception{
        return stateDao.query(spot_id);
    }
}