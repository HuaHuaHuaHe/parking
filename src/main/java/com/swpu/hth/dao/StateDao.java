package com.swpu.hth.dao;

import com.swpu.hth.entity.StateDO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StateDao {

    /**
     *更新总的 总数据里是 state_swpu
     *
     */
    @Update("update state_swpu set state = #{state} where spot_id = #{spot_id}")
    void update(StateDO stateDO);

    /**
     *更新底层 子数据库里是 state
     *
     */
    @Update("update state set state = #{state} where spot_id =  #{spot_id}")
    void updateSwpu(StateDO stateDO);

    /**
     *查询单个车位方法
     *
     */
    @Select("select * from state_swpu  where spot_id = #{spot_id}")
    StateDO query(int spot_id);

    /**
     *查询所有车位方法
     * parking_lot_name:停车场名称，做区分
     *
     */
    @Select("select * from state_"+"#{parking_lot_name}")
    List<StateDO> queryAll(String parking_lot_name);
}
