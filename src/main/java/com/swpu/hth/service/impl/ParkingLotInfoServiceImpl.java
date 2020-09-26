package com.swpu.hth.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.swpu.hth.dao.ParkingLotInfoDao;
import com.swpu.hth.entity.ParkingLotInfoDO;
import com.swpu.hth.service.ParkingLotInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ParkingLotInfoServiceImpl implements ParkingLotInfoService {

    @Autowired
    private ParkingLotInfoDao parkingLotInfoDao;


    @DS(value = "all-ds")
    @Override
    public ArrayList<ParkingLotInfoDO> queryParkingLotInfo() {
        return parkingLotInfoDao.queryParkingLotInfo();
    }
}
