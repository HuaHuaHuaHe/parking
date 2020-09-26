package com.swpu.hth.dao;

import com.swpu.hth.entity.ChargeDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ChargeDao {

    @Select("SELECT *FROM charge_history")
    List<ChargeDO> queryAll();

    @Select("SELECT *FROM charge_history where username = #{username}")
    List<ChargeDO> queryByName (String username);
}
