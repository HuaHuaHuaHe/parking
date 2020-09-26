package com.swpu.hth.dao;

import com.swpu.hth.entity.FingerDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FingerDao {

    @Select("select * from finger")
    List<FingerDO> queryAll();
}
