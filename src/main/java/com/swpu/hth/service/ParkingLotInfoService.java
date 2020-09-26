package com.swpu.hth.service;

import com.swpu.hth.entity.ParkingLotInfoDO;

import java.util.ArrayList;

public interface ParkingLotInfoService {
    /**
     *
     * @return 区域内所有停车场的信息，包括经纬度，停车费率，剩余车位 总车位
     */
    ArrayList<ParkingLotInfoDO> queryParkingLotInfo()throws Exception;
}
