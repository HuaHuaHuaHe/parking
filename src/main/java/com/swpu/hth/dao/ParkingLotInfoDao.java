package com.swpu.hth.dao;

import com.swpu.hth.entity.ParkingLotInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper  //Mybatis的注解，在Spring中，Mybatis找到对应的mapper，在编译的时候动态生成代理类，实现数据的查询功能，所以我们需要在接口上添加@mapper注解
@Repository  //Spring的注解，用于声明一个bean
public interface ParkingLotInfoDao {

    /*
    * 获取停车场的相应数据，从而计算每个用户的最优停车场栈
    * */
    @Select("Select * from parking_lot_info ")
    ArrayList<ParkingLotInfoDO> queryParkingLotInfo();
}
