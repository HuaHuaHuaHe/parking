package com.swpu.hth.service.impl;

import com.swpu.hth.dao.DisDao;
import com.swpu.hth.entity.DistributedDO;
import com.swpu.hth.service.DisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DisServiceImpl implements DisService {

    @Autowired
    DisDao disDao;

    @Override
    public DistributedDO queryByName(String username,String license) throws Exception {
        return disDao.queryByName(username,license);
    }
}
