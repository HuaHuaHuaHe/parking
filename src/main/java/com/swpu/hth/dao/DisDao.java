package com.swpu.hth.dao;


import com.swpu.hth.entity.DistributedDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DisDao {

    /**
     *  查询已经分配的信息
     */
    @Select("SELECT * FROM distributed where username =#{username} AND license=#{license}")
    DistributedDO queryByName(String username,String license);
}
