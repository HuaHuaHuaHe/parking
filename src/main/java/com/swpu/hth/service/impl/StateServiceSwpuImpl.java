package com.swpu.hth.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.swpu.hth.dao.StateDao;
import com.swpu.hth.entity.StateDO;
import com.swpu.hth.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 操作子数据库 swpu 的service实现类（PS:具体逻辑问题未分析，分析之后再说2020-05-25）
 */
@Service
public class StateServiceSwpuImpl implements StateService {

    @Autowired
    private StateDao stateDao;

    @Override
    @DS(value = "swpu-ds")
    @Transactional(propagation = Propagation.REQUIRES_NEW)  //开启新事务
    public synchronized void updateState(StateDO stateDO) throws Exception{
        int state_flag = stateDO.getState();
        if(state_flag>2 || state_flag<=0){
            throw new Exception("车位状态码错误");
        }
        stateDao.updateSwpu(stateDO);
    }

    @Override
    public StateDO querystate(int spot_id) throws Exception{
        return null;
    }
}
