package com.swpu.hth.service;

import com.swpu.hth.entity.DistributedDO;

public interface DisService {
    DistributedDO queryByName(String username,String license) throws Exception;
}
